package com.example;

import java.util.ArrayList;
import java.util.List;

public class BudgetRequestFormAccessTest {

    public static void main(String[] args) {
        BudgetRequestFormAccessTest test = new BudgetRequestFormAccessTest();
        test.testFormAccessAndSubmission();
    }

    public void testFormAccessAndSubmission() {

        String rolePm = "pm";
        String roleSm = "sm";
        String invalidRole = "cso"; 


        boolean pmAccessResult = isFormAccessible(rolePm);
        boolean smAccessResult = isFormAccessible(roleSm);


        boolean invalidAccessResult = isFormAccessible(invalidRole);


        String projectName = "Project Alpha";
        String currentBudget = "50000";
        String requestedAmount = "10000";
        String justification = "Need additional resources for Q4";
        boolean submissionResult = submitBudgetRequest(rolePm, projectName, currentBudget, requestedAmount, justification);


        boolean isAccessAndSubmissionCorrect = pmAccessResult && smAccessResult && !invalidAccessResult && submissionResult;

        System.out.println("Test Form Access and Submission: " + (isAccessAndSubmissionCorrect ? "PASSED" : "FAILED"));
    }

    private boolean isFormAccessible(String role) {
        return "pm".equals(role) || "sm".equals(role);
    }

    private boolean submitBudgetRequest(String role, String projectName, String currentBudget, String requestedAmount, String justification) {

        if (!isFormAccessible(role)) {
            return false;
        }

        List<String> submittedData = new ArrayList<>();
        submittedData.add(projectName);
        submittedData.add(currentBudget);
        submittedData.add(requestedAmount);
        submittedData.add(justification);

        return !submittedData.isEmpty();
    }
}
