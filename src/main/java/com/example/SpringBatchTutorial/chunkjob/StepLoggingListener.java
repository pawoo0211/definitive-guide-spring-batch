package com.example.SpringBatchTutorial.chunkjob;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;

public class StepLoggingListener {
    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        System.out.println(stepExecution.getStepName() + " has begun!");
    }

    @AfterStep
    public void afterStep(StepExecution stepExecution) {
        System.out.println(stepExecution.getStepName() + " is end");
    }
}