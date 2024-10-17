package com.searchandfilter.Search.and.Filter.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestDto{
    private List<SearchRequestDto> searchRequestDtos;

    private GlobalOperator globalOperator;

    private PageRequestDto pageDto;

    public enum GlobalOperator {
        AND,
        OR
    }
}
