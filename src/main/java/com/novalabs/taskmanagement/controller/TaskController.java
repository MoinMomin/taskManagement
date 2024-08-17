package com.novalabs.taskmanagement.controller;

import com.novalabs.taskmanagement.model.Task;
import com.novalabs.taskmanagement.service.TaskService;
import com.novalabs.taskmanagement.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskService taskService;
    @PostMapping("/tasks")
    public ResponseEntity<Map> createTask(@RequestBody Task task){
      Task taskResponse=  taskService.createNewTask(task);
        return CustomResponse.created(taskResponse);
    }
    @GetMapping("/tasks")
    public ResponseEntity<Map> getAllTask( @RequestParam(required = false) Integer pageNumber,
                                           @RequestParam(required = false) Integer size){
        if(pageNumber==null){
            pageNumber=0;
        }
        if(size==null){
            size=0;
        }
        List<Task> taskResponse=  taskService.getALlTasks(pageNumber,size);
        return CustomResponse.ok(taskResponse);
    }
    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<Map> updateTask(@RequestBody Task task,@PathVariable long taskId){
        task.setId(taskId);
        Task taskResponse=  taskService.updateTask(task);
        return CustomResponse.created(taskResponse);
    }
}
