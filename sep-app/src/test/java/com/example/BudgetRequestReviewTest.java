package com.example;

import java.util.ArrayList;
import java.util.List;

public class BudgetRequestReviewTest {

    public static void main(String[] args) {
        BudgetRequestReviewTest test = new BudgetRequestReviewTest();
        test.testReviewAccessAndApproval();
    }

    public void testReviewAccessAndApproval() {

        String roleFm = "fm"; 
        String rolePm = "pm"; 
        String roleHr = "hr"; 


        boolean fmAccessResult = isReviewAccessible(roleFm);

   
        boolean pmAccessResult = isReviewAccessible(rolePm);
        boolean hrAccessResult = isReviewAccessible(roleHr);


        String requestToReview = "Project Alpha,50000,10000,Need additional resources for Q4";
        boolean reviewResult = reviewBudgetRequest(roleFm, requestToReview, true); 

        // Verification
        boolean isAccessAndReviewCorrect = fmAccessResult && !pmAccessResult && !hrAccessResult && reviewResult;

        System.out.println("Test Review Access and Approval: " + (isAccessAndReviewCorrect ? "PASSED" : "FAILED"));
    }

    private boolean isReviewAccessible(String role) {

        return "fm".equals(role);
    }

    private boolean reviewBudgetRequest(String role, String request, boolean approve) {

        if (!isReviewAccessible(role)) {
            return false;
        }


        List<String> reviewedRequests = new ArrayList<>();
        String status = approve ? "Approved" : "Disapproved";
        reviewedRequests.add(request + "," + status); 

 
        return reviewedRequests.size() == 1 && reviewedRequests.get(0).endsWith(status);
    }
}
