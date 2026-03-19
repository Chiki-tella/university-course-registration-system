# University Course Registration System

A Java web application for managing university course registrations, built with **Jakarta EE (Servlet, JSP, JSTL)**, **Hibernate ORM**, and **Maven**. This project demonstrates the practical use of advanced Java concepts including OOP, exception handling, multithreading, streams, and Hibernate inheritance mappings.

## Features

- **Student Management**: Add, list, and view students.
- **Course Management**: Add and list courses.
- **Professor Management**: Add professors and assign them to courses.
- **Enrollment**: Students can enroll in courses (with simulated email confirmation via multithreading).
- **Statistics**: Use Java Streams to compute average grades and other metrics.
- **Inheritance Mapping**: Single Table strategy for `Person` → `Student` / `Professor`.
- **Associations**: Many-to-Many (Student ↔ Course), One-to-Many (Professor → Courses).
- **Custom Exceptions**: Validate input and handle errors gracefully.
- **MVC Architecture**: Clear separation of concerns with servlets, DAOs, and JSPs.

## Technologies Used

| Technology        | Purpose                               |
|-------------------|---------------------------------------|
| Java 17           | Core language                         |
| Jakarta EE 10     | Servlet, JSP, JSTL                    |
| Hibernate 6.4     | ORM (Object-Relational Mapping)       |
| MySQL             | Database (can be replaced)            |
| Maven             | Build and dependency management       |
| Tomcat 11         | Web server / Servlet container        |
| Eclipse           | IDE (but works with any)               |

## Project Structure
```text
UniversityApp/
├── pom.xml
├── src/main/java
│ ├── com.university.model // Entities (Person, Student, Professor, Course, Enrollment)
│ ├── com.university.dao // Data Access Objects
│ ├── com.university.controller // Servlets
│ ├── com.university.util // HibernateUtil, EmailSender, custom exceptions
│ └── com.university.service // Business logic (statistics using streams)
├── src/main/resources
│ └── hibernate.cfg.xml // Hibernate configuration
└── src/main/webapp
├── WEB-INF/web.xml
├── index.jsp
├── listStudents.jsp
├── addStudent.jsp
├── listCourses.jsp
├── enrollStudent.jsp
└── statistics.jsp
```

## Prerequisites

- **Java JDK 17** or higher
- **Apache Tomcat 11** (or any Jakarta EE 10 compatible server)
- **MySQL** (or another database; adjust `hibernate.cfg.xml` accordingly)
- **Maven** (or use Eclipse's embedded Maven)
- **Eclipse IDE** (optional, but recommended for easy setup)

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/university-course-registration.git
cd university-course-registration
```

### 2. Configure the Database
Create a MySQL database, e.g., `university_db`.

Update `src/main/resources/hibernate.cfg.xml` with your database URL, username, and password.

```xml
<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/university_db?useSSL=false&amp;serverTimezone=UTC</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">yourpassword</property>
```
Hibernate will automatically create the tables when the application first runs (`hbm2ddl.auto=update`).

### 3. Build the Project with Maven
```bash
mvn clean package
```
This generates a WAR file in the `target` directory.

### 4. Deploy to Tomcat
Copy the WAR file to Tomcat's `webapps` folder, or:

In Eclipse: right-click the project → **Run As** → **Run on Server** (select Tomcat 11).

### 5. Access the Application
Open your browser and go to:
```text
http://localhost:8080/UniversityApp/
```

## Usage Guide
- **Add Students**: Navigate to `/students?action=new` to add a new student.
- **List Students**: Visit `/students` to see all students.
- **Add Courses**: Go to `/courses?action=new` (you'll need to implement `CourseServlet` similarly).
- **Enroll a Student**: Use the enrollment form at `/enrollments`.
- **View Statistics**: Check `/statistics` to see computed averages using streams.

## Key Code Highlights
- **Inheritance Mapping** (Person → Student/Professor) – uses `SINGLE_TABLE` strategy.
- **Multithreading** – `EmailSender` implements `Runnable` and is triggered on enrollment.
- **Streams API** – `StatisticsServlet` filters and averages enrollment grades.
- **Custom Exceptions** – `ValidationException` thrown and caught in servlets.

## Troubleshooting
- **Facet version errors**: Ensure your project facets are set to **Dynamic Web Module 6.0** and **Java 17**. If stuck, edit `.settings/org.eclipse.wst.common.project.facet.core.xml` manually.
- **Servlet not found**: Check that all servlet classes use `@WebServlet` with correct URL patterns and that Tomcat 11 is properly configured.
- **Database connection issues**: Verify MySQL is running and credentials in `hibernate.cfg.xml` are correct.

## Contributing
Contributions are welcome! Feel free to open issues or submit pull requests for improvements, bug fixes, or additional features.

## License
This project is licensed under the MIT License – see the [LICENSE](file:///C:/Users/user/eclipse-workspace-clean/UniversityApp/LICENSE) file for details.

## Author
Christella – [@yourgithub](https://github.com/yourgithub)

Project Link: [https://github.com/yourusername/university-course-registration](https://github.com/yourusername/university-course-registration)
