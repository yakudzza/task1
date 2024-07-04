package com.example.task1.repository;

import com.example.task1.model.ExecutionTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionTimeRepository extends JpaRepository<ExecutionTime, Long> {
}
