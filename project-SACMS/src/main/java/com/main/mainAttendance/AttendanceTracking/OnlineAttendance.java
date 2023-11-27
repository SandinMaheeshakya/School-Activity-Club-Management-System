package com.main.mainAttendance.AttendanceTracking;
import com.main.mainAttendance.Database.Attendance.InputData;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OnlineAttendance extends Attendance{

    private String logTime;

    private boolean joinStatus;


    public OnlineAttendance(int groupNumber, int studentNumber, boolean joinStatus, String logTime) throws SQLException {
        super(groupNumber,studentNumber);
        this.joinStatus = joinStatus;
        this.logTime = logTime;

    }

    //Getters and Setters
    public String getLogTime() {
        return logTime;
    }



    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }


    public static ArrayList<HashMap<String,String>> trackAttendance(ArrayList<ChoiceBox<String>> attendanceData, ArrayList<HashMap<String,String>> studentsAttended,String eventID) {
        ArrayList<HashMap<String,String>> attendedStudents = new ArrayList<>();
        int count = 0;
        for (ChoiceBox<String> choice : attendanceData) {
            HashMap<String,String> studentDetailsMap = new HashMap<>();
            studentDetailsMap.put("studentID",studentsAttended.get(count).get("studentID"));
            studentDetailsMap.put("attendance",choice.getValue());
            studentDetailsMap.put("eventID",eventID);
            attendedStudents.add(studentDetailsMap);
            count++;
        }

        return attendedStudents;
    }

    public static void saveAttendance(ArrayList<HashMap<String,String>> attendanceData) throws SQLException {
        InputData.insertAttendanceDataToDatabase(attendanceData);
    }


    @Override
    public void editAttendance(Pane studentPane, Boolean attendance) {
    }

    @Override
    public Map<String,String> registeredStudentLog() {

        Map<String,String> studentDetails = new HashMap<>();
        studentDetails.put("studentID", getStudentID());
        studentDetails.put("studentName", getStudentName());
        studentDetails.put("studentGrade", getStudentGrade());
        studentDetails.put("studentJoinStatus", String.valueOf(isJoinStatus()));
        studentDetails.put("studentLogTime", getLogTime());
        return studentDetails;



    }

    public boolean isJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(boolean joinStatus) {
        this.joinStatus = joinStatus;
    }

}
