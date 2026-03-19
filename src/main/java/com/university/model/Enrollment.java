package com.university.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDate enrollmentDate;
    private Double grade; // optional
	public Long getId() {
		return id;
	}
	public Student getStudent() {
		return student;
	}
	public Course getCourse() {
		return course;
	}
	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}
	public Double getGrade() {
		return grade;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	public void setGrade(Double grade) {
		this.grade = grade;
	}
	public Enrollment(Long id, Student student, Course course, LocalDate enrollmentDate, Double grade) {
		super();
		this.id = id;
		this.student = student;
		this.course = course;
		this.enrollmentDate = enrollmentDate;
		this.grade = grade;
	}

    
}