package com.university.dao;

import com.university.model.Professor;
import com.university.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ProfessorDAO implements DAO<Professor> {

    @Override
    public Professor save(Professor professor) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(professor);
            transaction.commit();
            return professor;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Professor update(Professor professor) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            Professor updated = session.merge(professor);
            transaction.commit();
            return updated;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Professor professor) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(professor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Professor findById(Long id) {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            return session.get(Professor.class, id);
        }
    }

    @Override
    public List<Professor> findAll() {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            return session.createQuery("from Professor", Professor.class).list();
        }
    }
}