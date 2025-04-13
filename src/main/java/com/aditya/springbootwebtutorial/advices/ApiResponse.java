package com.aditya.springbootwebtutorial.advices;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

/*
 * @author adityagupta
 * @date 14/04/25
 */
@Data
public class ApiResponse<T> {

    // Tz - format timezone format or UTC format
    @JsonFormat(pattern = "hh:mm:ss dd-MM-YYYY")
    private LocalDateTime timeStamp;
    private T data;
    private ApiError error;

    public ApiResponse() {
        this.timeStamp = LocalDateTime.now();
    }
    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError error) {
        this();
        this.error = error;
    }
}
