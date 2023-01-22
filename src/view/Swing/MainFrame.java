
package view.Swing;

import control.Command;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Currency;
import view.MoneyDialog;
import view.MoneyDisplay;


public class MainFrame extends JFrame{
    
    private List<Currency> currencies;
    private MoneyDialog moneyDialog;
    private MoneyDisplay moneyDisplay;
    private Map<String,Command> commands = new HashMap<>();
    
    public MainFrame(List<Currency> currencies){
        this.currencies = currencies;
        this.setTitle("MoneyCalculator");
        this.setSize(500,150);
        this.setLocationRelativeTo(null);
        this.add(moneyDialog(), BorderLayout.SOUTH);
        this.add(moneyDisplay(), BorderLayout.CENTER);
        this.add(toolbar(), BorderLayout.NORTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    private Component moneyDialog(){
        SwingMoneyDialog dialog = new SwingMoneyDialog(currencies);
        moneyDialog = dialog;
        return dialog;
    }
    
    private Component moneyDisplay(){
        SwingMoneyDisplay display = new SwingMoneyDisplay();
        moneyDisplay = display;
        return display;
    }
    
    private Component toolbar(){
        JPanel jpanel = new JPanel();
        jpanel.add(calculateButton());
        return jpanel;
    }
    
    private JButton calculateButton(){
        JButton button = new JButton("Calculate");
        button.addActionListener(calculate());
        return button;
    }
    
    private ActionListener calculate(){
        return new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
              commands.get("Calculate").execute();
            }
        };
    }
    
    public MoneyDialog getMoneyDialog(){
        return moneyDialog;
    }
    
    public MoneyDisplay getMoneyDisplay(){
        return moneyDisplay;
    }
    
    public void add(Command command){
        commands.put(command.command(), command);
    }
    
}
