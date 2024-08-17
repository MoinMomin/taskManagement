package com.novalabs.taskmanagement.service;

import com.novalabs.taskmanagement.model.Task;

import java.util.List;

public interface TaskService {
    public Task createNewTask(Task task);
    public List<Task> getALlTasks(int page, int size);
    public Task updateTask(Task task);
}
