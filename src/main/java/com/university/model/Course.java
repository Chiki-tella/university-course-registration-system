package com.university.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer credits;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Integer getCredits() {
		return credits;
	}

	public Professor getProfessor() {
		return professor;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Course(Long id, String title, Integer credits, Professor professor, List<Student> students) {
		super();
		this.id = id;
		this.title = title;
		this.credits = credits;
		this.professor = professor;
		this.students = students;
	}

    
}
