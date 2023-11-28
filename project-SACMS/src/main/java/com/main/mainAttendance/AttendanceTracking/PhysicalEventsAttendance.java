package com.main.mainAttendance.AttendanceTracking;

import javafx.scene.layout.Pane;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PhysicalEventsAttendance extends Attendance {

    private String venue;
    private boolean joinStatus;


    public PhysicalEventsAttendance(int groupNumber, int studentNumber, String venue, boolean joinStatus) throws SQLException {
        super(groupNumber,studentNumber);
        this.venue = venue;
        this.joinStatus = joinStatus;
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
        studentDetails.put("studentJoinStatus", String.valueOf(isJoinStatus()));
        studentDetails.put("eventVenue", getVenue());

        return studentDetails;


    }

    public boolean isJoinStatus() {
        return joinStatus;
    }
}
