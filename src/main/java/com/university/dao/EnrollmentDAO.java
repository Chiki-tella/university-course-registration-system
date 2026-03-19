package com.university.dao;

import com.university.model.Enrollment;
import com.university.model.Student;
import com.university.model.Course;
import com.university.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class EnrollmentDAO implements DAO<Enrollment> {

    @Override
    public Enrollment save(Enrollment enrollment) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(enrollment);
            transaction.commit();
            return enrollment;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Enrollment update(Enrollment enrollment) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            Enrollment updated = session.merge(enrollment);
            transaction.commit();
            return updated;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Enrollment enrollment) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(enrollment);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Enrollment findById(Long id) {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            return session.get(Enrollment.class, id);
        }
    }

    @Override
    public List<Enrollment> findAll() {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            return session.createQuery("from Enrollment", Enrollment.class).list();
        }
    }

    // Additional methods specific to Enrollment

    public List<Enrollment> findByStudent(Student student) {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query<Enrollment> query = session.createQuery(
                "from Enrollment e where e.student = :student", Enrollment.class);
            query.setParameter("student", student);
            return query.list();
        }
    }

    public List<Enrollment> findByCourse(Course course) {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query<Enrollment> query = session.createQuery(
                "from Enrollment e where e.course = :course", Enrollment.class);
            query.setParameter("course", course);
            return query.list();
        }
    }

    public Enrollment findByStudentAndCourse(Student student, Course course) {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query<Enrollment> query = session.createQuery(
                "from Enrollment e where e.student = :student and e.course = :course", Enrollment.class);
            query.setParameter("student", student);
            query.setParameter("course", course);
            return query.uniqueResult();
        }
    }
}