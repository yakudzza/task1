package com.example.task1.aspect;


import com.example.task1.annotation.TrackAsyncTime;
import com.example.task1.annotation.TrackTime;
import com.example.task1.model.ExecutionTime;
import com.example.task1.repository.ExecutionTimeRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Aspect
@Component
@RequiredArgsConstructor
public class ExecutionTimeAspect {

    private final ExecutionTimeRepository executionTimeRepository;

    @Around("@annotation(com.example.task1.annotation.TrackTime)")
    public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        saveExecutionTime(joinPoint.getSignature().getName(), endTime - startTime);
        return result;
    }

    @Async
    @Around("@annotation(com.example.task1.annotation.TrackAsyncTime)")
    public void trackAsyncTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            joinPoint.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            saveExecutionTime(joinPoint.getSignature().getName(), endTime - startTime);
        }
    }

    private void saveExecutionTime(String methodName, long duration) {
        ExecutionTime executionTime = new ExecutionTime();
        executionTime.setMethodName(methodName);
        executionTime.setDuration(duration);
        executionTime.setTimestamp(new Timestamp(System.currentTimeMillis()));
        executionTimeRepository.save(executionTime);
    }
}