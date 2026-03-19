package com.university.controller;

import com.university.dao.EnrollmentDAO;
import com.university.model.Enrollment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@WebServlet("/statistics")
public class StatisticsServlet extends HttpServlet {
    private EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Enrollment> allEnrollments = enrollmentDAO.findAll();

        // Use streams to compute average grade (if grades are not null)
        double averageGrade = allEnrollments.stream()
                .filter(e -> e.getGrade() != null)
                .mapToDouble(Enrollment::getGrade)
                .average()
                .orElse(0.0);

        // Count enrollments per course using streams
        // This is a bit complex; we can do a simpler example: count of students with grade > 80
        long highAchievers = allEnrollments.stream()
                .filter(e -> e.getGrade() != null && e.getGrade() > 80)
                .count();

        req.setAttribute("averageGrade", averageGrade);
        req.setAttribute("highAchievers", highAchievers);

        req.getRequestDispatcher("/statistics.jsp").forward(req, resp);
    }
}