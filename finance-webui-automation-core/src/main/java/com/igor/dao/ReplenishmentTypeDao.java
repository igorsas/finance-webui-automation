package com.igor.dao;

import com.igor.model.daoModel.ReplenishmentType;
import com.igor.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReplenishmentTypeDao {
    public ReplenishmentType getById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(ReplenishmentType.class, id);
    }

    public void save(ReplenishmentType t) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
        session.close();
    }

    public void update(ReplenishmentType t) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(t);
        transaction.commit();
        session.close();
    }

    public void delete(ReplenishmentType t) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(t);
        transaction.commit();
        session.close();
    }

    public List<ReplenishmentType> getAll() {
        return (List<ReplenishmentType>) HibernateSessionFactory.getSessionFactory().openSession().
                createQuery("FROM ReplenishmentType ").list();
    }
}
