package com.indev.cryptocurrency.exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@SpringBootConfiguration
public class APIServer {

    public static void main(String[] args) {
        SpringApplication.run(APIServer.class, args);
    }

}

@RestController
class CustomerController {
    @Autowired
    private CryptocurrencyBank cryptocurrencyBank;

    @GetMapping(value = "/cryptocurrencies")
    public List<String> getCryptocurrencies() {
        return cryptocurrencyBank.getSupportedCryptoCurrencies();
    }

    @PostMapping(value = "/cryptocurrencies")
    public List<String> postCryptocurrencies(@RequestParam("name") String cryptoCurrencyName) {
        cryptocurrencyBank.addSupportedCryptoCurrency(cryptoCurrencyName);

        return cryptocurrencyBank.getSupportedCryptoCurrencies();
    }

    @DeleteMapping(value = "/cryptocurrencies")
    public List<String> deleteCryptocurrencies(@RequestParam("name") String cryptoCurrencyName) {
        return cryptocurrencyBank.deleteCryptoCurrency(cryptoCurrencyName);
    }
}
