package com.example.taskmanagerapplication.repository;

import com.example.taskmanagerapplication.enitity.TaskEntity;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

}
