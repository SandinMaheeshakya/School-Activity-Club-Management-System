package com.main.mainAttendance.AttendanceTracking;
import com.main.Database.Attendance.InputData;
import javafx.scene.control.ChoiceBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OnlineAttendance extends Attendance{

    private String meetingID;

    private boolean joinStatus;


    public OnlineAttendance(int groupNumber, int studentNumber, String meetingID) throws SQLException {
        super(groupNumber,studentNumber);
        this.meetingID = meetingID;

    }

    //Getters and Setters


    public static ArrayList<HashMap<String,String>> trackAttendance(ArrayList<ChoiceBox<String>> attendanceData, ArrayList<HashMap<String,String>> studentsAttended,String eventID) {
        ArrayList<HashMap<String,String>> attendedStudents = new ArrayList<>();
        int count = 0;
        for (ChoiceBox<String> choice : attendanceData) {
            HashMap<String,String> studentDetailsMap = new HashMap<>();
            studentDetailsMap.put("studentID",studentsAttended.get(count).get("studentID"));
            studentDetailsMap.put("attendance",choice.getValue());
            studentDetailsMap.put("eventID", eventID);
            attendedStudents.add(studentDetailsMap);
            count++;
        }

        return attendedStudents;
    }

    public static void saveAttendance(ArrayList<HashMap<String,String>> attendanceData) throws SQLException {
        InputData.insertAttendanceDataToDatabase(attendanceData);
    }


    @Override
    public Map<String,String> registeredStudentLog() {

        Map<String,String> studentDetails = new HashMap<>();
        studentDetails.put("studentID", getStudentID());
        studentDetails.put("studentName", getStudentName());
        studentDetails.put("studentGrade", getStudentGrade());
        studentDetails.put("studentJoinStatus", String.valueOf(isJoinStatus()));
        studentDetails.put("meetingID", getMeetingID());
        return studentDetails;

    }

    public boolean isJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(boolean joinStatus) {
        this.joinStatus = joinStatus;
    }

    public String getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(String meetingID) {
        this.meetingID = meetingID;
    }
}
