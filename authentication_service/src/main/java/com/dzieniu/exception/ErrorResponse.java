package com.dzieniu.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
class ErrorResponse {

    private HttpStatus response_status;
    private int status_code;
    private String message;
}
