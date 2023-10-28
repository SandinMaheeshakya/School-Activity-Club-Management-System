package com.main.projectsacms;

import java.util.ArrayList;
import java.util.List;

public class Student extends Human {
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

    private String studentId;
    private List<String> selectedClubs;

    public Student(int NIC, String name, int contactNo, String address, String email, String password, String studentId) {
        super(NIC, name, contactNo, address, email, password, studentId );
        this.studentId = studentId;
        this.selectedClubs = new ArrayList<>();
    }
}