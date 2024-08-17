package com.novalabs.taskmanagement.customException;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class TaskIdNotInRequest extends RuntimeException{
    public TaskIdNotInRequest(String message) {
        super(message);
    }
}
