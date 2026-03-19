package com.university.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "head_id")
    private Professor head;

    @OneToMany(mappedBy = "department")
    private List<Course> courses;

    // ...
}