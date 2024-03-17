package com.example.SpringBatchTutorial.conditional;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

import java.util.Random;

public class RandomDecider implements JobExecutionDecider {
    private Random random = new Random();
    private static String COMPLETE = FlowExecutionStatus.COMPLETED.getName();
    private String FAILED = FlowExecutionStatus.FAILED.getName();


    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
       if (random.nextBoolean()) {
           return new FlowExecutionStatus(COMPLETE);
       } else {
           return new FlowExecutionStatus(FAILED);
       }
    }
}