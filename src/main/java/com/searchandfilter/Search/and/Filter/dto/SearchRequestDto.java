package com.searchandfilter.Search.and.Filter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {
    private String column;
    private String value;
    private Operation operation;
    public enum Operation {
        EQUAL,
        LIKE
    }
}
