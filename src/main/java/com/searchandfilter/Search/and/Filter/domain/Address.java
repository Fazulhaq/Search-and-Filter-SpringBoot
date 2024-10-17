package com.searchandfilter.Search.and.Filter.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity(name = "address")
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long addressId;

    private String city;

    @JsonManagedReference
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Student> students;
}
