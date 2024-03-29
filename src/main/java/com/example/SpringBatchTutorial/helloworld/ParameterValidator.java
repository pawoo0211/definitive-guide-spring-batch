package com.example.SpringBatchTutorial.helloworld;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.util.StringUtils;

public class ParameterValidator implements JobParametersValidator {
    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
        String fileName = parameters.getString("fileName");

        if (!StringUtils.hasText(fileName)) {
            System.out.println("input fileName : " + fileName);
            throw new JobParametersInvalidException("file name is missing");
        } else if (!StringUtils.endsWithIgnoreCase(fileName,"csv")) {
            System.out.println("input fileName : " + fileName);
            throw new JobParametersInvalidException("file is not csv");
        }
    }
}
