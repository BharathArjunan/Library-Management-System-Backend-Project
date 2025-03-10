package com.project.LibraryManagementSystemBackEnd.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorDetails {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;

}
