package com.mindex.challenge.data;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class ReportingStructure {
    private int numberOfReports = 0;
    private Employee employee;
    private static final String GET_URL = "http://localhost:8080/employee/";

    public ReportingStructure(String id) throws IOException {
        Gson gson = new Gson();
        URL url = new URL(GET_URL + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET"); //uses the http
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        Employee employee = gson.fromJson(br.readLine(), Employee.class);
        this.employee = employee; //takes the employee and associates it with this object
        this.numberOfReports = generateReports(this.employee);

    }

    /*
    This function acts recursively, searching the tree vertically and counting the total number of direct reports under the employee
    @param e The employee to generate reports for
    @return reports The number of reports under the employee, e
     */
    private int generateReports(Employee e) throws IOException {
        Gson gson = new Gson();
        List<Employee> directReports = e.getDirectReports(); //gets the list of employees directly under the current employee
        int reports = 0;
        if (directReports == null) { //if the employee has no direct reports return 0 for their number of reports
            return 0;
        }
        else{
            for (Employee report : directReports){ //for each employee from the reports
                URL url = new URL(GET_URL + report.getEmployeeId());
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET"); //uses the http
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                Employee employee = gson.fromJson(br.readLine(), Employee.class);
                reports = reports + 1 + generateReports(employee); //add one to the count of reports, and then recursively add numberOfReports for that employee
            }
        }
        return reports;
    }


    public Employee getEmployee() {
        return this.employee; //simple getter for the employee attribute
    }

    public int getNumberOfReports() {
        return this.numberOfReports; //simple getter for the numberOfReports attribute
    }
}
