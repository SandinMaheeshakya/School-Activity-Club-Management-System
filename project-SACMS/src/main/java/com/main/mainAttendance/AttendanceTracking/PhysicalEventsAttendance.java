package com.main.mainAttendance.AttendanceTracking;

import javafx.scene.layout.Pane;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PhysicalEventsAttendance extends Attendance {

    private String venue;


    public PhysicalEventsAttendance(int groupNumber, int studentNumber, String venue) throws SQLException {
        super(groupNumber,studentNumber);
        this.venue = venue;
    }

    //Getters and Setters
    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }


    @Override
    public Map<String,String> registeredStudentLog() {

        Map<String,String> studentDetails = new HashMap<>();
        studentDetails.put("studentID", getStudentID());
        studentDetails.put("studentName", getStudentName());
        studentDetails.put("studentGrade", getStudentGrade());
        studentDetails.put("eventVenue", getVenue());

        return studentDetails;


    }

}
