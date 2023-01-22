
package view.Swing;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import model.Currency;
import model.Money;
import view.MoneyDialog;


public class SwingMoneyDialog extends JPanel implements MoneyDialog{
    
    private Currency to;
    private Currency from;
    private String amount;
    private final List<Currency> currencies;
    
    public SwingMoneyDialog(List<Currency> currencies){
        this.currencies = currencies;
        this.add(setAmount());
        this.add(setCurrencyFrom());
        this.add(setCurrencyTo());
    }
    
    @Override
    public Money get(){
        return new Money(Double.parseDouble(amount),from);
    }

    @Override
    public Currency getCurrencyTo() {
        return to;
    }
    
    private Component setCurrencyTo(){
        JComboBox comboBox = new JComboBox();
         for (Currency currency : currencies) {
            comboBox.addItem(currency);
        };
        comboBox.addItemListener(CurrencyToChanged());
        to = (Currency) comboBox.getSelectedItem();
        return comboBox;
    }
    
    private Component setCurrencyFrom(){
        
        JComboBox comboBox = new JComboBox();
         for (Currency currency : currencies) {
            comboBox.addItem(currency);
        };
        comboBox.addItemListener(CurrencyFromChanged());
        from = (Currency) comboBox.getSelectedItem();
        return comboBox;
    }
    
    private ItemListener CurrencyFromChanged(){
        return new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                if(e.getStateChange() == ItemEvent.DESELECTED) return;
                from = (Currency) e.getItem();
            }
        };
    } 
    private ItemListener CurrencyToChanged(){
        return new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                if(e.getStateChange() == ItemEvent.DESELECTED) return;
                to = (Currency) e.getItem();
            }
        };    
    }
    
    private Component setAmount(){
        JTextField textField = new JTextField();
        textField.setColumns(5);
        textField.getDocument().addDocumentListener(amountChanged());
        amount = textField.getText();
        return textField;
    }
    
    private DocumentListener amountChanged(){
        return new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e){
                amountChanged(e.getDocument());
            }
            
            @Override
            public void removeUpdate(DocumentEvent e){
                amountChanged(e.getDocument());
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
            amountChanged(e.getDocument());            }


            private void amountChanged(Document document){
                try{
                    amount = document.getText(0, document.getLength());
                } catch (BadLocationException e){
                    
                }
            }
        };

    }
    

    
}
