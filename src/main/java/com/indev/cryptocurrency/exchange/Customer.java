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

    public void addBalance(int balance, int _x){
        this.balance+=balance*_x;
    }

    public void decreaseBalance(int balance, int _x){
        this.balance-=balance;
    }

    public void addSold(int sold, int _x){
        this.sold+=sold*_x;
    }

    public void decreaseSold(int sold){
        this.sold-=sold;
    }

    public void sellCrypto(int sold, int _x){
        addBalance(sold, _x);
        decreaseSold(sold);
    }

    public boolean isSameCryptoCurrency(String cyptoCurrency){
        return this.cryptoCurrency.equals(cyptoCurrency);
    }

    @Override
    public String toString() {
        String print=+balance+":$";
        return print+=(sold>0)? ","+sold+":"+cryptoCurrency: "";
    }
}
