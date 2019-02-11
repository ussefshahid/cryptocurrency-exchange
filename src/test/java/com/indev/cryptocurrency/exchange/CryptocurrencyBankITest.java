package com.indev.cryptocurrency.exchange;

import com.indev.cryptocurrency.exchange.APIServer;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = APIServer.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CryptocurrencyBankITest {

    @LocalServerPort
    private int randomServerPort;

    @Before
    public void staticSetup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = randomServerPort;
    }

    @Test
    public void shouldReturnCryprocurrencies() {
        JsonPath jsonPath = get("/cryptocurrencies").then()
                .assertThat()
                .statusCode(200).extract().jsonPath();

        assertThat(jsonPath.getList("").size(), is(0));
    }

    @Test
    public void shouldAddCryprocurrencies() {
        RequestSpecification request = RestAssured.given();
        request.queryParam("name", "Bitcoin");

        JsonPath jsonPath = request.post("/cryptocurrencies").then()
                .assertThat()
                .statusCode(200).extract().jsonPath();

        assertThat(jsonPath.getList("").size(), is(1));


        request = RestAssured.given();
        request.queryParam("name", "XRP");

        jsonPath = request.post("/cryptocurrencies").then()
                .assertThat()
                .statusCode(200).extract().jsonPath();

        assertThat(jsonPath.getList("").size(), is(2));

        request = RestAssured.given();
        request.queryParam("name", "XRP");

        jsonPath = request.delete("/cryptocurrencies").then()
                .assertThat()
                .statusCode(200).extract().jsonPath();

        assertThat(jsonPath.getList("").size(), is(1));

        request = RestAssured.given();
        request.queryParam("name", "Bitcoin");

        jsonPath = request.delete("/cryptocurrencies").then()
                .assertThat()
                .statusCode(200).extract().jsonPath();

        assertThat(jsonPath.getList("").size(), is(0));
    }



}
