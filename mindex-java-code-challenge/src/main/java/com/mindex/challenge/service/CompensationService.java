package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

import java.io.IOException;


public interface CompensationService {
    Compensation create(String id, int salary, String date) throws IOException;
    Compensation read(String id);
}
