package com.searchandfilter.Search.and.Filter.service;

import com.searchandfilter.Search.and.Filter.dto.RequestDto;
import com.searchandfilter.Search.and.Filter.dto.SearchRequestDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FiltersSpecificatoin<T> {
    public Specification<T> getSearchSpecification(SearchRequestDto searchRequestDto){
        return new Specification<T>(){
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
            }
        };
    }

    public Specification<T> getSearchSpecification(List<SearchRequestDto> searchRequestDtos, RequestDto.GlobalOperator globalOperator){
        // press CTR+Space to get lambda suggestions.
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (SearchRequestDto requestDto: searchRequestDtos){
                switch (requestDto.getOperation()){
                    case EQUAL:
                        Predicate equal = criteriaBuilder.equal(root.get(requestDto.getColumn()), requestDto.getValue());
                        predicates.add(equal);
                        break;
                    case LIKE:
                        Predicate like = criteriaBuilder.like(root.get(requestDto.getColumn()), "%" + requestDto.getValue() + "%");
                        predicates.add(like);
                        break;
                }
            }
            if (globalOperator.equals(RequestDto.GlobalOperator.AND)){
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }else {
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
