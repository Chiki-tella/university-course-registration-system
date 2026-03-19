package com.university.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("STUDENT")
public class Student extends Person {
    @Column(unique = true)
    private String studentId;

    private LocalDate enrollmentDate;

    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();

    // Constructors
    public Student() {}

    public Student(String name, String email, String phone, String studentId, LocalDate enrollmentDate) {
        super(name, email, phone);
        this.studentId = studentId;
        this.enrollmentDate = enrollmentDate;
    }

	public String getStudentId() {
		return studentId;
	}

	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

    
}