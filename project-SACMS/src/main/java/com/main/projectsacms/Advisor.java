package com.main.projectsacms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.main.projectsacms.DatabaseConnection.getConnection;

public class Advisor extends Human {
    private String advisorId;
    private String department;

    public String getAdvisorId() {
        return advisorId;
    }

    public void setAdvisorId(String advisorId) {
        this.advisorId = advisorId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Advisor(String advisorId, String firstName, String lastName, String userName, String dob, String email, String department, String password) {
        super(firstName, lastName, userName, dob, email,password);
        this.advisorId = advisorId;
        this.department = department;
    }





}