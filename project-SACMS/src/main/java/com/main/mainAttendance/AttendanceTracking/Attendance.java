package com.main.mainAttendance.AttendanceTracking;

import com.main.Database.Attendance.InputData;
import com.main.Database.Attendance.RetrieveData;
import javafx.scene.layout.Pane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public abstract class Attendance implements PredefinedObjects {

    private String studentID;

    private String studentGrade;
    private boolean registerStatus;
    private String studentName;
    private String eventDate;
    private String eventType;
    private String eventID;
    private static int currentEventNumber;

    private int registeredStudentsCount;

    //Set values for each attribute from the data received from database (Constructor)
    public Attendance(int groupNumber,int studentNumber) throws SQLException {

        //Real Time Refresh
        ArrayList<ArrayList<Map<String,String>>> studentData = currentEventStudentsData();

        studentID = studentData.get(groupNumber).get(studentNumber).get("student_id");
        studentName = studentData.get(groupNumber).get(studentNumber).get("studentName");
        studentGrade = studentData.get(groupNumber).get(studentNumber).get("grade");

        // Need to add register Status

    }

    public Attendance (int noOfStudents){
        this.registeredStudentsCount = noOfStudents;
    }

    //Getters and Setters
    public String getStudentID() {
        return studentID;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventType() {
        return eventType;
    }

    public String getEventID() {
        return eventID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public abstract void editAttendance(Pane studentPane, Boolean attendance);

    public abstract Map<String,String> registeredStudentLog();


    //Gathering Data from the Database
    public ArrayList<ArrayList<Map<String,String>>> currentEventStudentsData() throws SQLException {
       return RetrieveData.getStudentsData(checkClubName(currentEventNumber));
    }

    //Checking types and IDs

    public static String checkClubName(int eventNumber)  {
       return AttendanceTrackingController.getEvents().get(eventNumber).get("clubName");

    }

    public static String checkEventId(int eventNumber)  {
        return AttendanceTrackingController.getEvents().get(eventNumber).get("eventID");

    }
    public static String checkEventType(int eventNumber) {

        Map<String, String> event = AttendanceTrackingController.getEvents().get(eventNumber);

        if (event.get("eventType").equals("Online")){
            return "Online";

        }else {

            return "Physical";
        }
    }



    public int getCurrentEventNumber() {
        return currentEventNumber;
    }

    public static void setCurrentEventNumber(int currentEventNumber) {
        Attendance.currentEventNumber = currentEventNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(String studentGrade) {
        this.studentGrade = studentGrade;
    }

    public static String retrieveAttendanceData(String eventID,String studentID){
        return RetrieveData.retrieveAttendanceDataFromDatabase(eventID,studentID);
    }

    public static void clearAttendanceTable(){
        InputData.truncateAttendanceTable();
    }




}
