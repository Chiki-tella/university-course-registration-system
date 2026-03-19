package com.university.dao;

import com.university.model.Course;
import com.university.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CourseDAO implements DAO<Course> {

    @Override
    public Course save(Course course) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(course);
            transaction.commit();
            return course;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Course update(Course course) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            Course updated = session.merge(course);
            transaction.commit();
            return updated;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Course course) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Course findById(Long id) {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            return session.get(Course.class, id);
        }
    }

    @Override
    public List<Course> findAll() {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            return session.createQuery("from Course", Course.class).list();
        }
    }
}