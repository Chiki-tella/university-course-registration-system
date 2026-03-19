package com.university.dao;

import com.university.model.Department;
import com.university.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class DepartmentDAO implements DAO<Department> {

    @Override
    public Department save(Department department) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(department);
            transaction.commit();
            return department;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Department update(Department department) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            Department updated = session.merge(department);
            transaction.commit();
            return updated;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Department department) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(department);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Department findById(Long id) {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            return session.get(Department.class, id);
        }
    }

    @Override
    public List<Department> findAll() {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            return session.createQuery("from Department", Department.class).list();
        }
    }
}