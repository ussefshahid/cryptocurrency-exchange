package com.indev.cryptocurrency.exchange;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CryptocurrencyBankTest {

    private CryptocurrencyBank cryptocurrencyBank = new CryptocurrencyBank();


    @Before
    public void setup() {
        cryptocurrencyBank.addSupportedCryptoCurrency("Bitcoin");
        cryptocurrencyBank.addSupportedCryptoCurrency("Ethereum");
    }

    @Test
    public void shouldPrintCustomerWalletWithBitcoin() {
        Customer sellerCustomer = new Customer().withCryptocurrency("Bitcoin", 10);

        assertThat(sellerCustomer.toString(), equalTo("0:$,10:Bitcoin"));
    }


    @Test
    public void shouldPrintCustomerWalletWithEthereum() {
        Customer sellerCustomer = new Customer().withCryptocurrency("Ethereum", 10);

        assertThat(sellerCustomer.toString(), equalTo("0:$,10:Ethereum"));
    }

    @Test
    public void shouldPrintCustomerWalletWithBalance() {
        Customer sellerCustomer = new Customer().withBalance(10000).withCryptocurrency("Bitcoin", 10);

        assertThat(sellerCustomer.toString(), equalTo("10000:$,10:Bitcoin"));
    }

    @Test
    public void shouldNotBuyWhenNoSeller() {
        Customer buyerCustomer = new Customer().withBalance(100);

        int boughtQuantity = cryptocurrencyBank.requestTransaction(buyerCustomer, 3, "Bitcoin");

        assertThat(boughtQuantity, equalTo(0));
        assertThat(buyerCustomer.toString(), equalTo("100:$"));
    }

    @Test
    public void shouldBuyCryptocurrency() {
        Customer sellerCustomer = new Customer().withCryptocurrency("Bitcoin", 10);

        cryptocurrencyBank.addSeller(sellerCustomer);

        Customer buyerCustomer = new Customer().withBalance(100);
        int boughtQuantity = cryptocurrencyBank.requestTransaction(buyerCustomer, 3, "Bitcoin");

        assertThat(boughtQuantity, equalTo(3));
        assertThat(sellerCustomer.toString(), equalTo("3:$,7:Bitcoin"));
        assertThat(buyerCustomer.toString(), equalTo("97:$,3:Bitcoin"));
    }


    @Test
    public void shouldNotBuyCryptocurrencyWhenNotFound() {
        Customer sellerCustomer = new Customer().withCryptocurrency("Bitcoin", 10);

        cryptocurrencyBank.addSeller(sellerCustomer);

        Customer buyerCustomer = new Customer().withBalance(100);
        int boughtQuantity = cryptocurrencyBank.requestTransaction(buyerCustomer, 3, "Ethereum");

        assertThat(boughtQuantity, equalTo(0));
        assertThat(sellerCustomer.toString(), equalTo("0:$,10:Bitcoin"));
        assertThat(buyerCustomer.toString(), equalTo("100:$"));
    }

    @Test
    public void shouldBuyCryptocurrencyMetcalfeLaw() {
        Customer sellerCustomer = new Customer().withCryptocurrency("Bitcoin", 10);

        cryptocurrencyBank.addSeller(sellerCustomer);

        Customer firstBuyer = new Customer().withBalance(100);
        Customer secondBuyer = new Customer().withBalance(100);
        Customer thirdBuyer = new Customer().withBalance(100);
        int firstBoughtQuantity = cryptocurrencyBank.requestTransaction(firstBuyer, 3, "Bitcoin");
        int secondBoughtQuantity = cryptocurrencyBank.requestTransaction(secondBuyer, 3, "Bitcoin");
        int thirdBoughtQuantity = cryptocurrencyBank.requestTransaction(thirdBuyer, 3, "Bitcoin");

        assertThat(firstBoughtQuantity, equalTo(3));
        assertThat(secondBoughtQuantity, equalTo(3));
        assertThat(thirdBoughtQuantity, equalTo(3));

        assertThat(sellerCustomer.toString(), equalTo("27:$,1:Bitcoin"));

        assertThat(firstBuyer.toString(), equalTo("97:$,3:Bitcoin"));
        assertThat(secondBuyer.toString(), equalTo("94:$,3:Bitcoin"));
        assertThat(thirdBuyer.toString(), equalTo("82:$,3:Bitcoin"));
    }
}
