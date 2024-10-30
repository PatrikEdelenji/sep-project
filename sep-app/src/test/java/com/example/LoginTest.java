package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginTest {

    public static void main(String[] args) {
        LoginTest test = new LoginTest();
        

        test.checkUser("user1", "test123");  
        test.checkUser("admin", "admin123"); 

        // Test with non-existing or incorrect credentials
        test.checkUser("user1", "wrongPass");  
        test.checkUser("nonExistentUser", "test123"); 
    }

    public void checkUser(String username, String password) {
        String filePath = "C:\\Users\\patri\\VScodeProjects\\sep-project\\sep-app\\src\\main\\resources\\users.csv";  // Path to your CSV file
        boolean userFound = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length >= 2) {  
                    String fileUsername = userDetails[0].trim();
                    String filePassword = userDetails[1].trim();

                    if (fileUsername.equals(username) && filePassword.equals(password)) {
                        System.out.println("Test Passed: User " + username + " credentials are correct.");
                        userFound = true;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading users.csv: " + e.getMessage());
        }

        if (!userFound) {
            System.err.println("Test Failed: User " + username + " credentials are incorrect or user does not exist.");
        }
    }
}