package com.example.SpringBatchTutorial.servicejob;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    public void execution() {
        System.out.println("MyService execution method is executed");
    }
}