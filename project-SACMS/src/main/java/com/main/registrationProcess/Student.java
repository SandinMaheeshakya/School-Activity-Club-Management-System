package com.main.registrationProcess;

import java.util.ArrayList;
import java.util.List;

public class Student extends Human {
    private String studentId;
    private String grade;
    private List<String> selectedClubs;
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public List<String> getSelectedClubs() {
        return selectedClubs;
    }

    public void setSelectedClubs(List<String> selectedClubs) {
        this.selectedClubs = selectedClubs;
    }



    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Student(String studentId, String firstname, String lastname, String username, String dob, String email, int grade, String password) {
        super(firstname, lastname, username, dob, email, password);
        this.studentId = studentId;
        this.grade = String.valueOf(grade);
        this.selectedClubs = new ArrayList<>();
    }
}
