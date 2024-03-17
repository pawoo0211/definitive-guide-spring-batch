package com.example.SpringBatchTutorial.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyServiceJob {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job serviceInvokingJob() {
        return jobBuilderFactory.get("invokingServiceJob")
                .start(invokeServiceStep())
                .build();
    }

    @Bean
    public Step invokeServiceStep() {
        Tasklet invokeServiceTasklet;
        return stepBuilderFactory.get("invokeService")
                .tasklet(invokeServiceTasklet())
                .build();
    }

    @Bean
    public Tasklet invokeServiceTasklet() {
        MethodInvokingTaskletAdapter methodInvokingTaskletAdapter = new MethodInvokingTaskletAdapter();
        methodInvokingTaskletAdapter.setTargetObject(new MyService());
        methodInvokingTaskletAdapter.setTargetMethod("execution");
        return methodInvokingTaskletAdapter;
    }
}