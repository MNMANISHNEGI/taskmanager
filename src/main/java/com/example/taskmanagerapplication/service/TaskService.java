package com.example.taskmanagerapplication.service;

import com.example.taskmanagerapplication.enitity.TaskEntity;
import com.example.taskmanagerapplication.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<TaskEntity> getAllTask()
    {
        return taskRepository.findAll();
    }

    public Optional<TaskEntity> getTaskById(Long id)
    {
        return taskRepository.findById(id);
    }

    public TaskEntity createTask(TaskEntity taskEntity)
    {
        return taskRepository.save(taskEntity);
    }

    public TaskEntity updateTask(Long id, TaskEntity taskEntity)
    {
        TaskEntity task = taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Task id not found"));
        task.setTitle(taskEntity.getTitle());
        task.setDescription(taskEntity.getDescription());
        task.setDueDate(taskEntity.getDueDate());
        task.setCompleted(taskEntity.isCompleted());

        return taskRepository.save(task);

    }


    public void deleteTask(Long id)
    {
    TaskEntity task = taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Not able to find Task id"));
    taskRepository.delete(task);
    }

}
