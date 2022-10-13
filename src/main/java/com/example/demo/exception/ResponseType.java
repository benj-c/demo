package com.example.demo.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResponseType {

    USER_NOT_FOUND((short) 1000, "USER_NOT_FOUND", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR((short) 1001, "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
    OPERATIO_SUCCESS((short) 1002, "OPERATIO_SUCCESS", HttpStatus.OK),
    JWT_ERROR((short) 1003, "jwt error", HttpStatus.FORBIDDEN),
    ACCESS_DENIED(1004, "ACCESS_DENIED", HttpStatus.FORBIDDEN)
    ;

    private int code;
    private String message;
    private HttpStatus httpStatus;

}
