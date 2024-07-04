package com.example.task1.service;

import com.example.task1.model.ExecutionTime;
import com.example.task1.repository.ExecutionTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class ExecutionTimeService {

    private final ExecutionTimeRepository executionTimeRepository;

    @Async
    public void saveExecutionTime(String methodName, long duration) {
        ExecutionTime executionTime = new ExecutionTime();
        executionTime.setMethodName(methodName);
        executionTime.setDuration(duration);
        executionTime.setTimestamp(new Timestamp(System.currentTimeMillis()));
        executionTimeRepository.save(executionTime);
    }
}