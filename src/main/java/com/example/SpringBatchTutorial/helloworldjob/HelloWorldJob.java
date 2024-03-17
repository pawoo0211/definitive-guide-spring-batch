package com.example.SpringBatchTutorial.helloworldjob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.listener.JobListenerFactoryBean;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class HelloWorldJob {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("basicJob-1")                // 잡 파라미터
                .start(step1())
                .validator(validator())
                .incrementer(new DailyJobTimeStamper())
                .listener(JobListenerFactoryBean.getListener(
                        new JobLoggerListener()
                ))
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(helloWorldTasklet(null, null))
                .build();
    }

    @StepScope
    @Bean
    public Tasklet helloWorldTasklet(
            @Value("#{jobParameters['name']}") String name,
            @Value("#{jobParameters['fileName']}") String fileName) {
    return ((stepContribution, chunkContext) -> {
        System.out.println(
                String.format("Hello, %s!", name));
        System.out.println(
                String.format("fileName=%s", fileName));
        return RepeatStatus.FINISHED;
        });
    }

    @Bean
    public CompositeJobParametersValidator validator() {
        // Instantiate CompositeJobParametersValidator
        CompositeJobParametersValidator validator = new CompositeJobParametersValidator();

        // Instantiate DefaultJobParametersValidator
        DefaultJobParametersValidator defaultValidator = new DefaultJobParametersValidator();
        defaultValidator.setRequiredKeys(new String[] {"fileName"});
        defaultValidator.setOptionalKeys(new String[] {"name", "currentDate"});
        defaultValidator.afterPropertiesSet();

        // Inject into CompositeJobParametersValidator
        validator.setValidators(Arrays.asList(new ParameterValidator(), defaultValidator));
        return validator;
    }
}