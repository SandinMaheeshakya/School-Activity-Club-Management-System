package com.main.clubcreation;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClubCreationReport {


    public void generateAttendanceReport(List<Map<String, String>> allAttendanceData) {

        try {
            // Load JR XML template

            String reportTemplatePath = "src/main/resources/com/main/clubCreation/Reports/Club Creation Report.jrxml";
            JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplatePath);

            // Fill the report with data
            Map<String, Object> parameters = new HashMap<>();
            // parameters
            parameters.put("ReportTitle", "Attendance Report");

            // Convert the entire attendance data list to a JRDataSource
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(allAttendanceData);

            // Create JasperPrint (filled report)
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Show the report using JasperViewer
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}