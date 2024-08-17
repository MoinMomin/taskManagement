package com.novalabs.taskmanagement.customException;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenUnAuthorized extends RuntimeException{
    public TokenUnAuthorized(String message) {
        super(message);
    }
}
