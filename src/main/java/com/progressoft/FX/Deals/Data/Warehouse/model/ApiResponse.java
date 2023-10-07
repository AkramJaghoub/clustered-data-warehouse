package com.progressoft.FX.Deals.Data.Warehouse.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    private String message;
    private HttpStatus statusCode;
    @Nullable
    private Object data;

    public ApiResponse(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}