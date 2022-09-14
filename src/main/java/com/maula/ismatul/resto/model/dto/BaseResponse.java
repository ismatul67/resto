package com.maula.ismatul.resto.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BaseResponse<T> {
    private T data;
    private int code;
    private String status;
    private String message;
}
