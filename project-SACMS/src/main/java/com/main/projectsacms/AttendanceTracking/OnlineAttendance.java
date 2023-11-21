package com.main.projectsacms.AttendanceTracking;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class OnlineAttendance extends Attendance{

    private String logTime;

    private boolean joinStatus;


    public OnlineAttendance(String studentID, int studentGrade, String studentName, String logTime, boolean joinStatus) {
        super(studentID, studentGrade, studentName);
        this.logTime = logTime;
        this.joinStatus = joinStatus;
    }

    //Getters and Setters
    public String getLogTime() {
        return logTime;
    }



    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }


    @Override
    public void editAttendance(Pane studentPane, Boolean attendance) {
    }

    @Override
    public void logAttendance() {

    }

    public boolean isJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(boolean joinStatus) {
        this.joinStatus = joinStatus;
    }


}
