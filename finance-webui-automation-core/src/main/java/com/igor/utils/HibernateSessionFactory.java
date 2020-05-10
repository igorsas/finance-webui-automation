package com.igor.utils;

import com.igor.model.daoModel.Currency;
import com.igor.model.daoModel.Deposit;
import com.igor.model.daoModel.ReplenishmentType;
import com.igor.model.daoModel.Term;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Objects;

public class HibernateSessionFactory {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactory() {
    }

    public static SessionFactory getSessionFactory() {
        if (Objects.isNull(sessionFactory)) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Currency.class)
                        .addAnnotatedClass(Term.class)
                        .addAnnotatedClass(ReplenishmentType.class)
                        .addAnnotatedClass(Deposit.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Throwable e) {
                System.err.println("Failed to create sessionFactory object." + e);
                throw new ExceptionInInitializerError(e);
            }
        }
        return sessionFactory;
    }
}
