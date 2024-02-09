package com.lliscano.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDTO<T> {
    private String time = new Date().toString();
    private String message = "success";
    private T data;
    private Long total;
}
