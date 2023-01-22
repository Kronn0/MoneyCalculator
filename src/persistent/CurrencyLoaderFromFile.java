
package persistent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import model.Currency;
import java.util.List;


public class CurrencyLoaderFromFile implements CurrencyLoader{
    
    private final String nameFile;
    
    
    public CurrencyLoaderFromFile(String nameFile){
        this.nameFile = nameFile;
    }

    @Override
    public List<Currency> loadCurrencies() {
        List<Currency> list = new ArrayList<>();
        
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(new File(nameFile)));
            while(true){
                String line = reader.readLine();
                if (line == null) break;
                list.add(convertToCurrency(line)); 
                
            }
        }
        catch(FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
        catch(IOException exception){
            System.out.println(exception.getMessage());
        }
        
        return list;
    }
    
    private Currency convertToCurrency(String line){
        String[] split = line.split(",");
        return new Currency(split[0],split[1],split[2]);
    }

}