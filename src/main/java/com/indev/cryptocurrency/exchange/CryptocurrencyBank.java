package com.indev.cryptocurrency.exchange;

import java.util.ArrayList;
import java.util.List;

public class CryptocurrencyBank {
    private List<String> supportedCryptoCurrencies=new ArrayList<>();
    private Customer sellerCustomer;
    private int customersNumber=0;

    public void addSupportedCryptoCurrency(String bitcoin) {
        supportedCryptoCurrencies.add(bitcoin);
    }

    public int requestTransaction(Customer buyerCustomer, int sold, String bitcoin) {
        if(!isSellerExist() || !sellerCustomer.isSameCryptoCurrency(bitcoin)) return 0;
        increaseCustomersNumber();

        sellerCustomer.sellCrypto(sold, metcalfeLaw());

        buyerCustomer.withCryptocurrency(bitcoin, sold);
        buyerCustomer.decreaseBalance(sold, metcalfeLaw());
        return sold;
    }

    public void addSeller(Customer sellerCustomer) {
        this.sellerCustomer=sellerCustomer;
    }

    public boolean isSellerExist(){
        return sellerCustomer!=null;
    }

    private void increaseCustomersNumber(){
        customersNumber++;
    }

    public int metcalfeLaw(){
        if(customersNumber==1) return 1;
        return (customersNumber)*(customersNumber-1);
    }
}
