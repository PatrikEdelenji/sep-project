package com.example;

import java.util.ArrayList;
import java.util.List;

public class EventRequestApprovalTest {

    public static void main(String[] args) {
        EventRequestApprovalTest test = new EventRequestApprovalTest();
        test.testApproveRequestLogic();
    }

    public void testApproveRequestLogic() {
        // Mock data simulating contents of "event_requests.csv"
        List<String[]> allRecords = new ArrayList<>();
        allRecords.add(new String[]{"smth5","Bob Builder","This is event type, sick, right?","2024-10-01","2024-10-31","30","Yes","No","No","Yes","Yes","36000"
});
        allRecords.add(new String[]{"smth2","Bob Builder","This is event type, sick, right?","2024-10-01","2024-10-31","30","No","Yes","No","No","Yes","10000"
});

        // Mock client record to approve
        String clientRecordToApprove = "testClientRecord";

        // Simulate approval logic
        List<String[]> approvedRecords = new ArrayList<>();
        for (String[] record : allRecords) {
            if (record[0].equals(clientRecordToApprove)) {
                approvedRecords.add(record); // This would go to the approved file in real logic
                record[5] = "Approved"; // Update the status in allRecords to simulate marking as approved
            }
        }

        // Verification
        boolean isRecordApproved = approvedRecords.size() == 1 
                                    && approvedRecords.get(0)[0].equals(clientRecordToApprove) 
                                    && allRecords.stream().anyMatch(r -> r[0].equals(clientRecordToApprove) && r[5].equals("Approved"));

        System.out.println("Test Approve Request Logic: " + (isRecordApproved ? "PASSED" : "FAILED"));
    }
}

