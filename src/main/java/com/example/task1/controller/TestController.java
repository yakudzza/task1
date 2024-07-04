package com.example.task1.controller;


import com.example.task1.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final SampleService sampleService;

    @GetMapping("/test/sort")
    public List<Integer> testSortList() {
        List<Integer> list = Arrays.asList(5, 3, 8, 1, 2);
        return sampleService.sortList(list);
    }

    @GetMapping("/test/sum")
    public Integer testCalculateSum() throws ExecutionException, InterruptedException {
        List<Integer> list = Arrays.asList(5, 3, 8, 1, 2);
        return sampleService.calculateSum(list).get();
    }
}