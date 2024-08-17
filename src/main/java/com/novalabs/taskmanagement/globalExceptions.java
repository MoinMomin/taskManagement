package com.novalabs.taskmanagement;

import com.novalabs.taskmanagement.customException.TaskIdNotInRequest;
import com.novalabs.taskmanagement.customException.TaskNotFound;
import com.novalabs.taskmanagement.customException.TokenNotAvailableException;
import com.novalabs.taskmanagement.customException.TokenUnAuthorized;
import com.novalabs.taskmanagement.utils.CustomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class globalExceptions {
    @ExceptionHandler(TaskIdNotInRequest.class)
    public ResponseEntity<Map> taskIdNotInRequest(TaskIdNotInRequest taskIdNotInRequest){
        return CustomResponse.expectationFailed("Task Ids should not be  null");
    }
    @ExceptionHandler(TaskNotFound.class)
    public ResponseEntity<Map> taskNotFound(TaskNotFound taskNotFound){
        return CustomResponse.expectationFailed("Task is not Available with this Task Ids");
    }
    @ExceptionHandler(TokenNotAvailableException.class)
    public ResponseEntity<Map> tokenNotAvailableException(TokenNotAvailableException tokenNotFound){
        return CustomResponse.expectationFailed("Token Not found in request");
    }
    @ExceptionHandler(TokenUnAuthorized.class)
    public ResponseEntity<Map> tokenUnAuthorized(TokenUnAuthorized tokenUnAuthorized){
        return CustomResponse.forbidden("Token UnAuthorized");
    }
}
