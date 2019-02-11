package com.indev.cryptocurrency.exchange;

import java.util.ArrayList;
import java.util.List;

public class CryptocurrencyBank {
    private List<String> supportedCryptoCurrencies=new ArrayList<>();
    private List<Customer> sellerCustomers =new ArrayList<>();
    private int customersNumber=0;

    public void addSupportedCryptoCurrency(String bitcoin) {
        supportedCryptoCurrencies.add(bitcoin);
    }

    public int requestTransaction(Customer buyerCustomer, int sold, String cyptoCurrency) {
        if(!isSellerExist(cyptoCurrency) || !getSeller(cyptoCurrency).isSameCryptoCurrency(cyptoCurrency)) return 0;
        increaseCustomersNumber();

        getSeller(cyptoCurrency).sellCrypto(sold, metcalfeLaw());

        buyerCustomer.withCryptocurrency(cyptoCurrency, sold);
        buyerCustomer.decreaseBalance(sold, metcalfeLaw());
        return sold;
    }

    public void addSeller(Customer sellerCustomer) {
        sellerCustomers.add(sellerCustomer);
    }

    public boolean isSellerExist(String cyptoCurrency){
        return sellerCustomers.stream()
                .filter(customer -> customer.isSameCryptoCurrency(cyptoCurrency))
                .findFirst()
                .orElse(null) != null;
    }

    public Customer getSeller(String cyptoCurrency){
        return sellerCustomers.stream()
                .filter(customer -> customer.isSameCryptoCurrency(cyptoCurrency))
                .findFirst()
                .orElse(null);
    }

    private void increaseCustomersNumber(){
        customersNumber++;
    }

    public int metcalfeLaw(){
        if(customersNumber==1) return 1;
        return (customersNumber)*(customersNumber-1);
    }
}
