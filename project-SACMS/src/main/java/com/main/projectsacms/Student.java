package com.main.projectsacms;

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

    public Student(String firstName, String lastName, String userName, String email, int dob, String password, String confirm_password, String studentId) {
        super(firstName, lastName, userName, email, email, dob, password, confirm_password, studentId );
        this.studentId = studentId;

        this.selectedClubs = new ArrayList<>();
    }
}