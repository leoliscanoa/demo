package com.lliscano.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseDTO<T> {
    @Builder.Default
    private String time = new Date().toString();
    @Builder.Default
    private String message = "success";
    private T data;
    private Long total;
}
