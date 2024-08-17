package com.novalabs.taskmanagement.service;

import com.novalabs.taskmanagement.customException.TaskIdNotInRequest;
import com.novalabs.taskmanagement.customException.TaskNotFound;
import com.novalabs.taskmanagement.model.Task;
import com.novalabs.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    TaskRepository taskRepository;
    public Task createNewTask(Task task){
        task.setCreatedAt(new Date());
        task.setUpdatedAt(new Date());
       return taskRepository.save(task);
    }

    @Override
    public List<Task> getALlTasks(int page, int size) {
        if(page!=0 && size!=0){
        Pageable pageable = PageRequest.of(page, size);
        return  taskRepository.findAll(pageable).getContent();}
        else{
           return taskRepository.findAll();
        }
    }

    @Override
    public Task updateTask(Task task) {
        if(task.getId()==null){
            throw new TaskIdNotInRequest("task id should not null");
        }
       Optional<Task> dbTaskOpt= taskRepository.findById(task.getId());
       if(!dbTaskOpt.isEmpty()){
           Task dbTask=dbTaskOpt.get();
           dbTask.setTitle(task.getTitle());
           dbTask.setDescription(task.getDescription());
           dbTask.setDueDate(task.getDueDate());
           dbTask.setUpdatedAt(new Date());
         return   taskRepository.save(dbTask);
       }
       throw new TaskNotFound("task with this Id not found");
    }
}
