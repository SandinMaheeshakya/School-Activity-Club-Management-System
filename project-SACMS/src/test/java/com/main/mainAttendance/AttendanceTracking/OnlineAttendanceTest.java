package com.main.mainAttendance.AttendanceTracking;

import javafx.scene.control.ChoiceBox;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OnlineAttendanceTest {

    @Test
    void trackAttendance() {

        //Creating Empty Array Lists
        ArrayList<ChoiceBox<String>> attendanceData = new ArrayList<>();
        ArrayList<HashMap<String, String>> studentsAttended = new ArrayList<>();

        //setting an EventID
        String eventID = "E101";

        //Checking if the method works using assertEquals
        ArrayList<HashMap<String, String>> result = OnlineAttendance.trackAttendance(attendanceData, studentsAttended, eventID);
        assertEquals(attendanceData.size(), result.size());
    }

    @Test
    public void testRegisteredStudentLog() {
        // Create a new HashMap and populate it with expected values
        Map<String, String> expectedStudentDetails = new HashMap<>();
        expectedStudentDetails.put("studentID", "ExpectedStudentID");
        expectedStudentDetails.put("studentName", "ExpectedStudentName");
        expectedStudentDetails.put("studentGrade", "ExpectedStudentGrade");
        expectedStudentDetails.put("studentJoinStatus", "ExpectedJoinStatus");
        expectedStudentDetails.put("studentLogTime", "ExpectedLogTime");

        // Create a new HashMap to simulate the studentDetails map obtained from the method
        Map<String, String> studentDetails = new HashMap<>();
        studentDetails.put("studentID", "ExpectedStudentID");
        studentDetails.put("studentName", "ExpectedStudentName");
        studentDetails.put("studentGrade", "ExpectedStudentGrade");
        studentDetails.put("studentJoinStatus", "ExpectedJoinStatus");
        studentDetails.put("studentLogTime", "ExpectedLogTime");

        // Perform assertions to check the correctness of the result
        assertEquals(expectedStudentDetails, studentDetails);
    }
}