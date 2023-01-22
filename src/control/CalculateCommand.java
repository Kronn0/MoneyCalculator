
package control;

import model.Currency;
import model.Money;
import persistent.ExchangeRateLoader;
import view.MoneyDialog;
import view.MoneyDisplay;


public class CalculateCommand implements Command{
    
    private final MoneyDialog moneyDialog;
    private final MoneyDisplay moneyDisplay;
    private final ExchangeRateLoader loader;
    
    public CalculateCommand(MoneyDialog moneyDialog,MoneyDisplay moneyDisplay,ExchangeRateLoader loader){
       this.moneyDialog = moneyDialog;
       this.moneyDisplay = moneyDisplay;
       this.loader = loader;
    }

    @Override
    public String command() {
        return "Calculate";
        }

    @Override
    public void execute() {
        moneyDisplay.display(exchange(moneyDialog.get()));
    }
    
    private Money exchange(Money money){
        return new Money(money.getAmount() * rateOf(Money.getCurrency(), moneyDialog.getCurrencyTo()),moneyDialog.getCurrencyTo());
    }
    
    private double rateOf(Currency from, Currency to){
        
        System.out.print(from);
        return loader.load(from, to).getRate();
    }
    
}
