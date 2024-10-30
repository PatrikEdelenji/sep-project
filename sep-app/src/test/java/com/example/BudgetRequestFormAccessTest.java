package com.example;

import java.util.ArrayList;
import java.util.List;

public class BudgetRequestFormAccessTest {

    public static void main(String[] args) {
        BudgetRequestFormAccessTest test = new BudgetRequestFormAccessTest();
        test.testFormAccessAndSubmission();
    }

    public void testFormAccessAndSubmission() {
        // Mock data simulating user roles and budget requests
        String rolePm = "pm";
        String roleSm = "sm";
        String invalidRole = "cso"; // This role should not access the form

        // Test for PM and SM roles
        boolean pmAccessResult = isFormAccessible(rolePm);
        boolean smAccessResult = isFormAccessible(roleSm);

        // Test for an invalid role
        boolean invalidAccessResult = isFormAccessible(invalidRole);

        // Test form submission (Mock data)
        String projectName = "Project Alpha";
        String currentBudget = "50000";
        String requestedAmount = "10000";
        String justification = "Need additional resources for Q4";
        boolean submissionResult = submitBudgetRequest(rolePm, projectName, currentBudget, requestedAmount, justification);

        // Verification
        boolean isAccessAndSubmissionCorrect = pmAccessResult && smAccessResult && !invalidAccessResult && submissionResult;

        System.out.println("Test Form Access and Submission: " + (isAccessAndSubmissionCorrect ? "PASSED" : "FAILED"));
    }

    private boolean isFormAccessible(String role) {
        // Only PM and SM roles should have access
        return "pm".equals(role) || "sm".equals(role);
    }

    private boolean submitBudgetRequest(String role, String projectName, String currentBudget, String requestedAmount, String justification) {
        // Simulate form submission: Only SM and PM roles can submit
        if (!isFormAccessible(role)) {
            return false;
        }

        // Simulate writing to the "budgetRequest.csv" (mock this step in reality)
        List<String> submittedData = new ArrayList<>();
        submittedData.add(projectName);
        submittedData.add(currentBudget);
        submittedData.add(requestedAmount);
        submittedData.add(justification);

        // Check if data was "submitted"
        return !submittedData.isEmpty();
    }
}
