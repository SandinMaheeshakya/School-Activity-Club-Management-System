package com.main.projectsacms.AttendanceTracking;

import javafx.scene.layout.Pane;

public class PhysicalEventsAttendance extends Attendance {

    private String venue;
    private boolean joinStatus;


    public PhysicalEventsAttendance(String studentID, int studentGrade, String studentName, String venue, boolean joinStatus) {
        super(studentID, studentGrade, studentName);
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
    public void editAttendance(Pane studentPane, Boolean attendance){

    }

    @Override
    public void logAttendance() {

    }

}
