package com.novalabs.taskmanagement.customException;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenNotAvailableException extends RuntimeException{
    public TokenNotAvailableException(String message) {
        super(message);
    }
}
