package com.university.dao;

import com.university.model.Student;
import com.university.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class StudentDAO implements DAO<Student> {

    @Override
    public Student save(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
            return student;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Student update(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            Student updated = session.merge(student);
            transaction.commit();
            return updated;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Student findById(Long id) {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            return session.get(Student.class, id);
        }
    }

    @Override
    public List<Student> findAll() {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            return session.createQuery("from Student", Student.class).list();
        }
    }
}