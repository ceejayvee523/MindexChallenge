package com.mindex.challenge.data;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Compensation {
    private Employee employee;
    private int salary;
    private String effective_date;
    private static final String GET_URL = "http://localhost:8080/employee/";

    public Compensation(String id, int salary, String date) throws IOException {
        this.salary = salary;
        this.effective_date = date;
        Gson gson = new Gson();
        URL url = new URL(GET_URL + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET"); //uses the http
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        Employee employee = gson.fromJson(br.readLine(), Employee.class);
        this.employee = employee; //takes the employee and associates it with this object
    }

    public int getSalary() {
        return this.salary;
    }

    public String getEffectiveDate() {
        return this.effective_date;
    }
}
