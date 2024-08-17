package com.novalabs.taskmanagement.utils;

import com.novalabs.taskmanagement.model.MetaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class CustomResponse {
    public static <T> ResponseEntity<Map> ok(T data){
        //Prepare meta info
        MetaResponse metaInfo = getMeta("SUCCESS", 200, false);
        //Prepare response
        Map<String, Object> map = new HashMap<>();
        map.put("meta", metaInfo);
        map.put("data", data);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    public static <T>ResponseEntity<Map> created(T data){
        MetaResponse metaInfo = getMeta("SUCCESS", 201, false);
        //Prepare response
        Map<String, Object> map = new HashMap<>();
        map.put("meta", metaInfo);
        map.put("body", data);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
    public static MetaResponse getMeta(String msg, int statusCode, boolean isError) {
        MetaResponse metaInfo = new MetaResponse();
        metaInfo.setMsg(msg);
        metaInfo.setStatus(statusCode);
        metaInfo.setError(isError);
        return metaInfo;
    }
    public static ResponseEntity<Map> expectationFailed(String msg) {
        //Prepare meta info
        MetaResponse metaInfo = getMeta(msg, 412, true);
        //Prepare response
        Map<String, Object> map = new HashMap<>();
        map.put("meta", metaInfo);
        return new ResponseEntity<>(map, HttpStatus.EXPECTATION_FAILED);
    }
    public static ResponseEntity<Map> forbidden(String errorMessage){
        //Prepare meta info
        MetaResponse metaInfo = getMeta(errorMessage, 403, true);
        //Prepare response
        Map<String, Object> map = new HashMap<>();
        map.put("meta", metaInfo);
        return new ResponseEntity<>(map, HttpStatus.FORBIDDEN);
    }
}
