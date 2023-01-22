package model;

public class Money {

    private final double amount;
    private static Currency currency;

    public Money(double amount, Currency currency) {
        this.amount = amount;
        Money.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public static Currency getCurrency() {
        return currency;
    }

}
