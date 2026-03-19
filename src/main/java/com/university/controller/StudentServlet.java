package com.university.controller;

import com.university.dao.StudentDAO;
import com.university.model.Student;
import com.university.util.exceptions.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.getRequestDispatcher("/addStudent.jsp").forward(req, resp);
                break;
            case "list":
            default:
                List<Student> students = studentDAO.findAll();
                req.setAttribute("students", students);
                req.getRequestDispatcher("/listStudents.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("insert".equals(action)) {
            try {
                // Retrieve form parameters
                String name = req.getParameter("name");
                String email = req.getParameter("email");
                String phone = req.getParameter("phone");
                String studentId = req.getParameter("studentId");
                LocalDate enrollDate = LocalDate.parse(req.getParameter("enrollmentDate"));

                // Basic validation (throw custom exception)
                if (name == null || name.trim().isEmpty()) {
                    throw new ValidationException("Name cannot be empty");
                }

                Student student = new Student(name, email, phone, studentId, enrollDate);
                studentDAO.save(student);

                // Redirect to list
                resp.sendRedirect("students?action=list");
            } catch (ValidationException e) {
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("/addStudent.jsp").forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving student");
            }
        }
    }
}