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

    public static void main(String[] args) {
//        Currency currency = new Currency("UAH");
//        new CurrencyDao().save(currency);
//
//        Term term = new Term();
//        term.setPeriod(8);
//        term.setPeriodType(TermType.MONTHS.getType());
//        term.setStartDate(new Date(System.currentTimeMillis()));
//        new TermDao().save(term);
//
//        ReplenishmentType replenishmentType = new ReplenishmentType();
//        replenishmentType.setType(ReplenishmentType.NONE.getType());
//        new ReplenishmentTypeDao().save(replenishmentType);
//
//        ReplenishmentType capitalizationType = new ReplenishmentType();
//        capitalizationType.setType(ReplenishmentType.MONTHLY.getType());
//        new ReplenishmentTypeDao().save(capitalizationType);

//        Deposit deposit = new Deposit();
//        deposit.setInitialSum(BigDecimal.valueOf(1000.0));
//        deposit.setInterestRate(BigDecimal.valueOf(5.0));
//        deposit.setCurrency(new CurrencyDao().getById(1));
//        deposit.setReplenishmentType(new ReplenishmentTypeDao().getById(1));
//        deposit.setReplenishmentSum(BigDecimal.valueOf(100.0));
//        deposit.setCapitalizationType(new ReplenishmentTypeDao().getById(2));
//        new DepositDao().save(deposit);

//        ReplenishmentType replenishmentTypeModel = new ReplenishmentType();
//        replenishmentTypeModel.setId(4);
//        replenishmentTypeModel.setType(ReplenishmentType.MONTHLY.getType());
//
//        new ReplenishmentTypeDao().delete(replenishmentTypeModel);

//        Currency currencyModel = new CurrencyDao().getById(1);
//        System.out.println(currencyModel);
//
//        Term termModel = new TermDao().getById(1);
//        System.out.println(termModel);
//
//        ReplenishmentType replenishmentTypeModel = new ReplenishmentTypeDao().getById(1);
//        System.out.println(replenishmentTypeModel);
//
//        ReplenishmentType capitalizationTypeModel = new ReplenishmentTypeDao().getById(2);
//        System.out.println(capitalizationTypeModel);
//
//        Deposit deposit = new Deposit();
//        deposit.setInitialSum(BigDecimal.valueOf(1000.0));
//        deposit.setInterestRate(BigDecimal.valueOf(5.0));
//        deposit.setCurrency(currencyModel);
//        deposit.setTerm(termModel);
//        deposit.setReplenishmentType(replenishmentTypeModel);
//        deposit.setReplenishmentSum(BigDecimal.valueOf(100.0));
//        deposit.setCapitalizationType(capitalizationTypeModel);
//        new DepositDao().save(deposit);

        new DepositDao().getAll().forEach(System.out::println);
    }
}
