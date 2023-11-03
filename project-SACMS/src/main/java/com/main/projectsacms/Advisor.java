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

    public Advisor(String firstName, String lastName, String userName, String email, String dob, int password, String confirm_password, String advisorId, String department) {
        super(firstName, lastName, userName, email, dob, password, confirm_password, advisorId, department);
        this.advisorId = advisorId;
        this.department = department;
    }
}
