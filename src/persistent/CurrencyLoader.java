package persistent;


import java.util.List;
import model.Currency;

public interface CurrencyLoader {
    public List<Currency> loadCurrencies();
}
