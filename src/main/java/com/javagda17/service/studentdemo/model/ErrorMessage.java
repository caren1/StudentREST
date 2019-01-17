package com.javagda17.service.studentdemo.model;

import lombok.Data;

import java.util.Map;

@Data
public class ErrorMessage {
    private String errorMessage;
    private String reason;
    private Map<String, String> params;
}
