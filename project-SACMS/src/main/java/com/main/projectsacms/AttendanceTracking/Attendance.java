package com.main.projectsacms.AttendanceTracking;

import com.main.projectsacms.Database.Attendance.RetrieveData;
import javafx.scene.layout.Pane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public abstract class Attendance implements PredefinedObjects {

    private String studentID;

    private int studentGrade;

    private boolean registerStatus;
    private String studentName;
    private String eventDate;
    private String eventType;
    private String eventID;
    private int currentEventNumber;


    public Attendance(String studentID, int studentGrade, String studentName) {
        this.studentID = studentID;
        this.studentGrade = studentGrade;
        this.studentName = studentName;
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

    public abstract void logAttendance();


    //Gathering Data from the Database
    public ArrayList<ArrayList<Map<String,String>>> getStudentsData() throws SQLException {
       return RetrieveData.getStudentsData(checkEventID(currentEventNumber));
    }

    //Set values for each attribute from the data received from database
    public void setValues(int eventNumber) throws SQLException {
        ArrayList<ArrayList<Map<String,String>>> studentData = getStudentsData();
    }
    
    //Checking types and IDs

    public String checkEventID(int eventNumber) throws SQLException {
       return RetrieveData.getEventsData().get(eventNumber).get("eventID");

    }
    public static String checkEventType(int eventNumber) throws SQLException {

        ArrayList<Map<String,String>> eventDetails = RetrieveData.getEventsData();

        if (eventDetails.get(eventNumber).get("Event_Type").equals("Online")){
            return "Online";

        }else {
            return "Physical";
        }
    }


    public int getCurrentEventNumber() {
        return currentEventNumber;
    }

    public void setCurrentEventNumber(int currentEventNumber) {
        this.currentEventNumber = currentEventNumber;
    }
}
