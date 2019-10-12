package com.igor.dao;

import com.igor.model.daoModel.Term;
import com.igor.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TermDao {
    public Term getById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Term.class, id);
    }

    public void save(Term t) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
        session.close();
    }

    public void update(Term t) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(t);
        transaction.commit();
        session.close();
    }

    public void delete(Term t) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(t);
        transaction.commit();
        session.close();
    }

    public List<Term> getAll() {
        return (List<Term>) HibernateSessionFactory.getSessionFactory().openSession().
                createQuery("FROM Term ").list();
    }
}
