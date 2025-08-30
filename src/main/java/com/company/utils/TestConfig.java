package com.company.utils;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestConfig {
    
    private static Properties properties;
    private static String authToken;
    
    static {
        loadProperties();
        setupRestAssured();
    }
    
    private static void loadProperties() {
        properties = new Properties();
        try {
            FileInputStream input = new FileInputStream("src/test/resources/config.properties");
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config properties", e);
        }
    }
    
    private static void setupRestAssured() {
        RestAssured.baseURI = properties.getProperty("base.url");
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
    
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public static String getAuthToken() {
        if (authToken == null) {
            authToken = authenticate();
        }
        return authToken;
    }
    
    private static String authenticate() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"email\": \"" + getProperty("admin.email") + "\",\n" +
                        "  \"password\": \"" + getProperty("admin.password") + "\"\n" +
                        "}")
                .when()
                .post(getProperty("auth.endpoint"))
                .then()
                .statusCode(200)
                .extract()
                .path("authorization");
    }
    
    public static RequestSpecification getAuthenticatedRequest() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", getAuthToken());
    }
}