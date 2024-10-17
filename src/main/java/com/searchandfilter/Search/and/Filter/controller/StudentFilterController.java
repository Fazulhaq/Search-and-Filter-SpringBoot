package com.searchandfilter.Search.and.Filter.controller;

import com.searchandfilter.Search.and.Filter.domain.Student;
import com.searchandfilter.Search.and.Filter.dto.RequestDto;
import com.searchandfilter.Search.and.Filter.repository.StudentRepository;
import com.searchandfilter.Search.and.Filter.service.FiltersSpecificatoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentFilterController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FiltersSpecificatoin<Student> studentFiltersSpecificatoin;

    @GetMapping("/name/{name}")
    public Student getStdByName(@PathVariable(name = "name") String name) {
        return studentRepository.findByName(name);
    }

    @GetMapping("/city/{city}")
    public List<Student> getStdByCity(@PathVariable(name = "city") String city) {
        return studentRepository.findByAddressCity(city);
    }

    @GetMapping("/subject/{subject}")
    public List<Student> getStdBySubject(@PathVariable(name = "subject") String subject) {
        return studentRepository.findBySubjectsName(subject);
    }

//    @PostMapping("/specification")
//    public List<Student> getStudents(){
//        Specification<Student> studentSpecification = new Specification<>(){
//            @Override
//            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                return criteriaBuilder.equal(root.get("id"), "2");
//            }
//        };
//        List<Student> all = studentRepository.findAll(studentSpecification);
//        return all;
//    }

    @PostMapping("/filteredstudents")
    public List<Student> getFilteredStudents(@RequestBody RequestDto requestDto){
        Specification<Student> studentSearchSpecification = studentFiltersSpecificatoin.getSearchSpecification(requestDto.getSearchRequestDtos(), requestDto.getGlobalOperator());
        return studentRepository.findAll(studentSearchSpecification);
    }
}
