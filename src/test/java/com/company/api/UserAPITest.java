package com.company.api;

import com.company.models.User;
import com.company.services.UserService;
import com.company.utils.TestConfig;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
@Epic("User Management API")
@Feature("CRUD Operations for Users")
public class UserAPITest {
    
    private static String createdUserId;
    private static User testUser;
    
    @BeforeAll
    static void setup() {
        testUser = new User(
            "Test User API",
            "testuser.api@email.com",
            "password123",
            "true"
        );
    }
    
    @Test
    @Order(1)
    @Story("Create User")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test creating a new user with valid data")
    @DisplayName("Create User - Success")
    void testCreateUserSuccess() {
        Response response = UserService.createUser(testUser);
        
        assertEquals(201, response.getStatusCode());
        assertEquals("Cadastro realizado com sucesso", response.jsonPath().getString("message"));
        
        createdUserId = UserService.extractUserId(response);
        assertNotNull(createdUserId, "User ID should not be null");
    }
    
    @Test
    @Order(2)
    @Story("Get User")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test retrieving a specific user by ID")
    @DisplayName("Get User by ID - Success")
    void testGetUserByIdSuccess() {
        Response response = UserService.getUserById(createdUserId);
        
        assertEquals(200, response.getStatusCode());
        
        User user = response.as(User.class);
        assertEquals(testUser.getNome(), user.getNome());
        assertEquals(testUser.getEmail(), user.getEmail());
        assertEquals(testUser.getAdministrador(), user.getAdministrador());
    }
    
    @Test
    @Order(3)
    @Story("Get All Users")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test retrieving all users")
    @DisplayName("Get All Users - Success")
    void testGetAllUsersSuccess() {
        Response response = UserService.getAllUsers();
        
        assertEquals(200, response.getStatusCode());
        assertTrue(response.jsonPath().getList("usuarios").size() > 0,
                "Should return at least one user");
    }
    
    @Test
    @Order(4)
    @Story("Update User")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test updating user information")
    @DisplayName("Update User - Success")
    void testUpdateUserSuccess() {
        User updatedUser = new User(
            "Updated User Name",
            "updated.email@test.com",
            "newpassword123",
            "false"
        );
        
        Response response = UserService.updateUser(createdUserId, updatedUser);
        
        assertEquals(200, response.getStatusCode());
        assertEquals("Registro alterado com sucesso", response.jsonPath().getString("message"));
        
        // Verify the update
        Response getResponse = UserService.getUserById(createdUserId);
        User user = getResponse.as(User.class);
        
        assertEquals(updatedUser.getNome(), user.getNome());
        assertEquals(updatedUser.getEmail(), user.getEmail());
        assertEquals(updatedUser.getAdministrador(), user.getAdministrador());
    }
    
    @Test
    @Order(5)
    @Story("Delete User")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test deleting a user")
    @DisplayName("Delete User - Success")
    void testDeleteUserSuccess() {
        Response response = UserService.deleteUser(createdUserId);
        
        assertEquals(200, response.getStatusCode());
        assertEquals("Registro excluído com sucesso", response.jsonPath().getString("message"));
        
        // Verify user is deleted
        Response getResponse = UserService.getUserById(createdUserId);
        assertEquals(400, getResponse.getStatusCode());
    }
    
    @Test
    @Order(6)
    @Story("Create User")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test creating user with missing required fields")
    @DisplayName("Create User - Missing Required Fields")
    void testCreateUserMissingFields() {
        User invalidUser = new User(null, "test@email.com", null, "true");
        
        Response response = UserService.createUser(invalidUser);
        
        assertEquals(400, response.getStatusCode());
        assertTrue(response.getBody().asString().contains("é obrigatório"));
    }
    
    @Test
    @Order(7)
    @Story("Get User")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test retrieving non-existent user")
    @DisplayName("Get User - Not Found")
    void testGetUserNotFound() {
        Response response = UserService.getUserById("nonexistent123");
        
        assertEquals(400, response.getStatusCode());
    }
}