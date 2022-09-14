package com.maula.ismatul.resto.controller;

import com.maula.ismatul.resto.model.dto.BaseFilter;
import com.maula.ismatul.resto.model.dto.BaseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

public class BaseController {

    protected static final String ROLE_USER = "hasRole('ROLE_USER')";
    protected static final String ROLE_ADMIN = "hasRole('ROLE_ADMIN') ";
    protected static final String ROLE_USER_ADMIN = "hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') ";

    protected static final String AUTHORIZATION = "Authorization";

    protected Pageable getPageable(BaseFilter filter){

        return PageRequest.of(
                filter.getPage() == 0 ? 0 : filter.getPage() - 1,
                filter.getSize() == 0 ? 100 : filter.getSize(),
                filter.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC,
                Objects.isNull(filter.getSortBy()) ? "id" : filter.getSortBy()
        );
    }

    protected ResponseEntity<BaseResponse<Object>> getResponseOk(Object data, String message) {
        return ResponseEntity.status(HttpStatus.OK).body(getResponse(data, message, HttpStatus.OK));
    }

    protected ResponseEntity<BaseResponse<Object>> getResponseCreated(Object data, String message) {
        return ResponseEntity.status(HttpStatus.CREATED).body(getResponse(data, message, HttpStatus.CREATED));
    }

    protected ResponseEntity<BaseResponse<Page<?>>> getResponsePage(Page<?> data, String message){

        BaseResponse<Page<?>> response = new BaseResponse<>(
                data,
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                message
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    protected BaseResponse<Object> getResponse(Object data, String message, HttpStatus httpStatus) {
        return BaseResponse.builder()
                .data(data)
                .code(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .message(message)
                .build();
    }
}
