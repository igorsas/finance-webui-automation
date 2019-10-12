package com.igor.dao;

import com.igor.model.daoModel.Currency;
import com.igor.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CurrencyDao {
    public Currency getById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Currency.class, id);
    }

    public void save(Currency t) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
        session.close();
    }

    public void update(Currency t) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(t);
        transaction.commit();
        session.close();
    }

    public void delete(Currency t) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(t);
        transaction.commit();
        session.close();
    }

    public List<Currency> getAll() {
        return (List<Currency>) HibernateSessionFactory.getSessionFactory().openSession().
                createQuery("FROM Currency ").list();
    }
}
