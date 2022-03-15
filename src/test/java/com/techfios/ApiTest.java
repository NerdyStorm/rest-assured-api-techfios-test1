package com.techfios;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiTest {
    

    private Response response;
    private String baseUrl = "https://techfios.com/api-prod/api/product";

    Logger LOG = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void apiTest(){

        String authString = "demo@techfios.com" + ":" + "abc123";
        String authStringEnc = (new Base64()).encodeToString(authString.getBytes());

        

        response = RestAssured.given().header("Authorization", "Basic " + authStringEnc).get(baseUrl + "/read.php").andReturn();

        // response.getHeaders();
        // response.getBody().prettyPrint();

        System.out.println(authStringEnc);

        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        System.out.println(jsonPath.getList("records.findAll{it.description == 'The most awesome phone of 2013!'}.name"));
    }

}
