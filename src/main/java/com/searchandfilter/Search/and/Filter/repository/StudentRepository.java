package com.searchandfilter.Search.and.Filter.repository;

import com.searchandfilter.Search.and.Filter.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    Student findByName(String name);

    List<Student> findByAddressCity(String city);

    List<Student> findBySubjectsName(String subName);
}
