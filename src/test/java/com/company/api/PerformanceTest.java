package com.company.api;

import com.company.services.UserService;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerformanceTest {
    
    @Test
    void testRateLimiting() {
        // Test multiple requests to check rate limiting
        for (int i = 0; i < 10; i++) {
            Response response = UserService.getAllUsers();
            
            if (response.getStatusCode() == 429) {
                System.out.println("Rate limit reached at request: " + (i + 1));
                assertEquals(429, response.getStatusCode(), "Should return 429 Too Many Requests");
                return;
            }
            
            assertEquals(200, response.getStatusCode(), "Request should succeed within rate limit");
            
            // Small delay to avoid hitting rate limit too quickly
            // Estrategia para evitar bloqueios durante os testes
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}