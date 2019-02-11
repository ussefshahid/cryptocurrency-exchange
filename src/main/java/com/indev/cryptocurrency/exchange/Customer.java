package com.indev.cryptocurrency.exchange;

public class Customer {
    private String cryptoCurrency;
    private int balance;
    private int sold;

    public Customer withCryptocurrency(String cryptoCurrency, int sold) {
        this.cryptoCurrency=cryptoCurrency;
        this.sold=sold;
        return this;
    }

    public Customer withBalance(int balance) {
        this.balance=balance;
        return this;
    }

    public void addBalance(int balance, int metcalfeLaw){
        this.balance+=balance*metcalfeLaw;
    }

    public void decreaseBalance(int balance, int metcalfeLaw){
        this.balance-=balance*metcalfeLaw;
    }

    public void decreaseSold(int sold){
        this.sold-=sold;
    }

    public void sellCrypto(int sold, int metcalfeLaw){
        addBalance(sold, metcalfeLaw);
        decreaseSold(sold);
    }

    public boolean isSameCryptoCurrency(String cyptoCurrency){
        return this.cryptoCurrency.equals(cyptoCurrency);
    }

    @Override
    public String toString() {
        return balance + ":$" + ((sold > 0) ? "," + sold + ":" + cryptoCurrency : "");
    }
}
