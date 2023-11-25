package com.main.projectsacms;

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
        super(advisorId, firstName, lastName, userName, dob, email, department, password);
        this.advisorId = advisorId;
        this.department = department;
    }
}