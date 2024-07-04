package com.example.task1.service;


import com.example.task1.annotation.TrackAsyncTime;
import com.example.task1.annotation.TrackTime;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SampleService {

    @TrackTime
    public List<Integer> sortList(List<Integer> list) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list.sort(Integer::compareTo);
        return list;
    }

    @Async
    @TrackAsyncTime
    public CompletableFuture<Integer> calculateSum(List<Integer> list) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int sum = list.stream().mapToInt(Integer::intValue).sum();
        return CompletableFuture.completedFuture(sum);
    }
}