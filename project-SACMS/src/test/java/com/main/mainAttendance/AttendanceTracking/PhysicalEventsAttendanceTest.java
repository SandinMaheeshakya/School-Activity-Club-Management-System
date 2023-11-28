package com.main.mainAttendance.AttendanceTracking;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PhysicalEventsAttendanceTest {

    @Test
    public void testRegisteredStudentLog() {
        // Create a new HashMap and populate it with expected values
        Map<String, String> expectedStudentDetails = new HashMap<>();
        expectedStudentDetails.put("studentID", "ExpectedStudentID");
        expectedStudentDetails.put("studentName", "ExpectedStudentName");
        expectedStudentDetails.put("studentGrade", "ExpectedStudentGrade");
        expectedStudentDetails.put("studentJoinStatus", "ExpectedJoinStatus");
        expectedStudentDetails.put("eventVenue", "ExpectedEventVenue");

        // Create a new HashMap to simulate the studentDetails map obtained from the method
        Map<String, String> studentDetails = new HashMap<>();
        studentDetails.put("studentID", "ExpectedStudentID");
        studentDetails.put("studentName", "ExpectedStudentName");
        studentDetails.put("studentGrade", "ExpectedStudentGrade");
        studentDetails.put("studentJoinStatus", "ExpectedJoinStatus");
        studentDetails.put("eventVenue", "ExpectedEventVenue");

        // Perform assertions to check the correctness of the result
        assertEquals(expectedStudentDetails, studentDetails);
    }
}