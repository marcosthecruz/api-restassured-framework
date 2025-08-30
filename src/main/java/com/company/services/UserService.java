package com.company.services;

import com.company.models.User;
import com.company.utils.TestConfig;
import io.restassured.response.Response;

import static com.company.utils.TestConfig.getAuthenticatedRequest;

public class UserService {
    
    private static final String USERS_ENDPOINT = TestConfig.getProperty("users.endpoint");
    
    public static Response getAllUsers() {
        return getAuthenticatedRequest()
                .when()
                .get(USERS_ENDPOINT);
    }
    
    public static Response getUserById(String userId) {
        return getAuthenticatedRequest()
                .when()
                .get(USERS_ENDPOINT + "/" + userId);
    }
    
    public static Response createUser(User user) {
        return getAuthenticatedRequest()
                .body(user)
                .when()
                .post(USERS_ENDPOINT);
    }
    
    public static Response updateUser(String userId, User user) {
        return getAuthenticatedRequest()
                .body(user)
                .when()
                .put(USERS_ENDPOINT + "/" + userId);
    }
    
    public static Response deleteUser(String userId) {
        return getAuthenticatedRequest()
                .when()
                .delete(USERS_ENDPOINT + "/" + userId);
    }
    
    public static String extractUserId(Response response) {
        return response.then().extract().path("_id");
    }
}