package com.example;

import java.util.ArrayList;
import java.util.List;

public class BudgetRequestReviewTest {

    public static void main(String[] args) {
        BudgetRequestReviewTest test = new BudgetRequestReviewTest();
        test.testReviewAccessAndApproval();
    }

    public void testReviewAccessAndApproval() {
        // Mock roles
        String roleFm = "fm"; // This role should have access
        String rolePm = "pm"; // This role should not have access
        String roleHr = "hr"; // This role should not have access

        // Test access for FM role
        boolean fmAccessResult = isReviewAccessible(roleFm);

        // Test access for non-FM roles
        boolean pmAccessResult = isReviewAccessible(rolePm);
        boolean hrAccessResult = isReviewAccessible(roleHr);

        // Test review functionality (Mock data)
        String requestToReview = "Project Alpha,50000,10000,Need additional resources for Q4";
        boolean reviewResult = reviewBudgetRequest(roleFm, requestToReview, true); // FM approves the request

        // Verification
        boolean isAccessAndReviewCorrect = fmAccessResult && !pmAccessResult && !hrAccessResult && reviewResult;

        System.out.println("Test Review Access and Approval: " + (isAccessAndReviewCorrect ? "PASSED" : "FAILED"));
    }

    private boolean isReviewAccessible(String role) {
        // Only FM role should have access
        return "fm".equals(role);
    }

    private boolean reviewBudgetRequest(String role, String request, boolean approve) {
        // Only allow FM role to review
        if (!isReviewAccessible(role)) {
            return false;
        }

        // Simulate reviewing the request (either approving or disapproving)
        List<String> reviewedRequests = new ArrayList<>();
        String status = approve ? "Approved" : "Disapproved";
        reviewedRequests.add(request + "," + status); // Append approval status

        // Check if review was successful
        return reviewedRequests.size() == 1 && reviewedRequests.get(0).endsWith(status);
    }
}
