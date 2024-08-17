package com.novalabs.taskmanagement.customException;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class TaskNotFound extends RuntimeException{
    public TaskNotFound(String message) {
        super(message);
    }
}
