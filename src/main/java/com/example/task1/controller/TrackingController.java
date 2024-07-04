package com.example.task1.controller;

import com.example.task1.model.ExecutionTime;
import com.example.task1.repository.ExecutionTimeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/tracking")
@RequiredArgsConstructor
public class TrackingController {

    private final ExecutionTimeRepository executionTimeRepository;

    @GetMapping("/stats")
    public Map<String, Map<String, Double>> getMethodStatistics() {
        List<ExecutionTime> executionTimes = executionTimeRepository.findAll();

        return executionTimes.stream().collect(Collectors.groupingBy(
                ExecutionTime::getMethodName,
                Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            long totalExecutionTime = list.stream().mapToLong(ExecutionTime::getDuration).sum();
                            double averageExecutionTime = list.stream().mapToLong(ExecutionTime::getDuration).average().orElse(0.0);
                            return Map.of(
                                    "totalTime", (double) totalExecutionTime,
                                    "averageTime", averageExecutionTime
                            );
                        }
                )
        ));
    }
}