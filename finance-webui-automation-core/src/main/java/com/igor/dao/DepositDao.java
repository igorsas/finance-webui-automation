package com.igor.dao;

import com.igor.model.daoModel.Deposit;
import com.igor.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DepositDao {
    public Deposit getById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Deposit.class, id);
    }

    public void save(Deposit t) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
        session.close();
    }

    public void update(Deposit t) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(t);
        transaction.commit();
        session.close();
    }

    public void delete(Deposit t) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(t);
        transaction.commit();
        session.close();
    }

    public List<Deposit> getAll() {
        return (List<Deposit>) HibernateSessionFactory.getSessionFactory().openSession().
                createQuery("FROM Deposit").list();
    }
}
