package com.university.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("PROFESSOR")
public class Professor extends Person {
    private String office;

    @OneToMany(mappedBy = "professor")
    private List<Course> courses;

	public String getOffice() {
		return office;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Professor(String office, List<Course> courses) {
		super();
		this.office = office;
		this.courses = courses;
	}

    
}