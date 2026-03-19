package com.university.controller;

import com.university.dao.EnrollmentDAO;
import com.university.dao.StudentDAO;
import com.university.dao.CourseDAO;
import com.university.model.Enrollment;
import com.university.model.Student;
import com.university.model.Course;
import com.university.util.EmailSender;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/enrollments")
public class EnrollmentServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    private StudentDAO studentDAO = new StudentDAO();
    private CourseDAO courseDAO = new CourseDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = studentDAO.findAll();
        List<Course> courses = courseDAO.findAll();
        req.setAttribute("students", students);
        req.setAttribute("courses", courses);
        req.getRequestDispatcher("/enrollStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long studentId = Long.parseLong(req.getParameter("studentId"));
        Long courseId = Long.parseLong(req.getParameter("courseId"));

        Student student = studentDAO.findById(studentId);
        Course course = courseDAO.findById(courseId);

        Enrollment enrollment = new Enrollment(courseId, student, course, null, null);
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.now());

        enrollmentDAO.save(enrollment);

        // Demonstrate multithreading: send email confirmation in a new thread
        String email = student.getEmail();
        EmailSender emailSender = new EmailSender(email, "Enrollment Confirmation",
                "You have been enrolled in " + course.getTitle());
        new Thread(emailSender).start();

        // Demonstrate streams: after enrollment, we could compute statistics
        // For example, get all enrollments for this course and compute average grade (if grades exist)
        // But we'll leave streams for another servlet (StatisticsServlet)

        resp.sendRedirect("enrollments?success=true");
    }
}