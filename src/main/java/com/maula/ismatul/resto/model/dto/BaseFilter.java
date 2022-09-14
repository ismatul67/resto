package com.maula.ismatul.resto.model.dto;

import lombok.Data;

@Data
public class BaseFilter {
    private String sortBy;
    private boolean ascending;
    private int page;
    private int size;
}
