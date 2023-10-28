package com.main.projectsacms;

public class Advisor extends Human {
    private String advisorId;
    private String department;

    public Advisor(String name, String email, String password, String advisorId, String department) {
        super(name, email, password);
        this.advisorId = advisorId;
        this.department = department;
    }
}
