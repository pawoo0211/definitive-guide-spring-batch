package com.example.SpringBatchTutorial.helloworld;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;

public class JobLoggerListener {
    private static String START_MESSAGE = "%s is beginning execution";
    private static String END_MESSAGE = "%s has completed with the status";

    @BeforeJob
    public void beforeJob(JobExecution execution) {
        System.out.println(String.format(START_MESSAGE,
                execution.getJobInstance()
                        .getJobName()));
    }

    @AfterJob
    public void afterJob(JobExecution execution) {
        System.out.println(String.format(END_MESSAGE,
                execution.getJobInstance()
                        .getJobName()));
    }
}