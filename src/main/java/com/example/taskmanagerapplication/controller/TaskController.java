package com.example.taskmanagerapplication.controller;

import com.example.taskmanagerapplication.enitity.TaskEntity;
import com.example.taskmanagerapplication.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v17/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<TaskEntity> getAllTask()
    {
        return taskService.getAllTask();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable Long id)
    {
    TaskEntity task = taskService.getTaskById(id).orElseThrow(()->new RuntimeException("Not able this Task id"));
    return ResponseEntity.ok(task);
    }


    @PostMapping
    public TaskEntity createTask(@RequestBody TaskEntity task)
    {
        return taskService.createTask(task);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable Long id, @RequestBody TaskEntity task)
    {
    TaskEntity updatedTask  = taskService.updateTask(id,task);
    return ResponseEntity.ok(updatedTask);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id)
    {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
