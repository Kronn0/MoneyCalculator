
package Main;

import control.CalculateCommand;
import java.util.ArrayList;
import java.util.List;
import model.Currency;
import persistent.CurrencyLoader;
import persistent.CurrencyLoaderFromFile;
import persistent.ExchangeRateLoader;
import persistent.ExchangeRateLoaderWebService;
import view.Swing.MainFrame;


public class MoneyCalculator {


    public static void main(String[] args) {
        CurrencyLoader currencyLoader = new CurrencyLoaderFromFile("patata.txt");
        ExchangeRateLoader exchangeRateLoader = new ExchangeRateLoaderWebService();   
        MainFrame mainFrame = new MainFrame(currencyLoader.loadCurrencies());
        mainFrame.add(new CalculateCommand(mainFrame.getMoneyDialog(), mainFrame.getMoneyDisplay(), exchangeRateLoader));
    }
    
}
