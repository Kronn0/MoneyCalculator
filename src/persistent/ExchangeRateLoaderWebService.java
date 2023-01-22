package persistent;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ExchangeRate;
import model.Currency;

public class ExchangeRateLoaderWebService implements ExchangeRateLoader {

    private final String web = "https://raw.githubusercontent.com/fawazahmed0/currency-api/1/latest/currencies/";
    private double rate;

    @Override
    public ExchangeRate load(Currency from, Currency to) {  
        
        
        try {
            rate = Double.parseDouble(
                    JSONtoString(read(new URL(web + from.getCode() + "/" + to.getCode() + ".json"))));
        } catch (IOException ex) {
            Logger.getLogger(ExchangeRateLoaderWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ExchangeRate(from,to,rate);

    }


            
    private String JSONtoString(String json){
            String[] split = json.split(",");
            String line = split[1];
            return line.substring(split[1].indexOf(":") + 1, split[1].indexOf("}") -1);
            }
    
        private String read(URL url) throws IOException{
        InputStream inputStream = url.openStream();
        byte[] buffer = new byte[300];
        int length = inputStream.read(buffer);

        return new String(buffer, 1, length);
        
    
    

}

        
        
        
    }
