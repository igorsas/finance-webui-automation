package com.igor.model.daoModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.StringJoiner;

@Entity
@Table(name = "deposit")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "initial_sum")
    private BigDecimal initialSum;
    @Column(name = "interest_rate")
    private BigDecimal interestRate;
    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;
    @ManyToOne
    @JoinColumn(name = "term_id", referencedColumnName = "id")
    private Term term;
    @ManyToOne
    @JoinColumn(name = "replenishment_type_id", referencedColumnName = "id")
    private ReplenishmentType replenishmentType;
    @Column(name = "replenishment_sum")
    private BigDecimal replenishmentSum;
    @ManyToOne
    @JoinColumn(name = "capitalization_type_id", referencedColumnName = "id")
    private ReplenishmentType capitalizationType;

    public Deposit() {
    }

    public Deposit(BigDecimal initialSum, BigDecimal interestRate, Currency currency,
                   Term term, ReplenishmentType replenishmentType,
                   BigDecimal replenishmentSum, ReplenishmentType capitalizationTypeId) {
        this.initialSum = initialSum;
        this.interestRate = interestRate;
        this.currency = currency;
        this.term = term;
        this.replenishmentType = replenishmentType;
        this.replenishmentSum = replenishmentSum;
        this.capitalizationType = capitalizationTypeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getInitialSum() {
        return initialSum;
    }

    public void setInitialSum(BigDecimal initialSum) {
        this.initialSum = initialSum;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public ReplenishmentType getReplenishmentType() {
        return replenishmentType;
    }

    public void setReplenishmentType(ReplenishmentType replenishmentType) {
        this.replenishmentType = replenishmentType;
    }

    public BigDecimal getReplenishmentSum() {
        return replenishmentSum;
    }

    public void setReplenishmentSum(BigDecimal replenishmentSum) {
        this.replenishmentSum = replenishmentSum;
    }

    public ReplenishmentType getCapitalizationType() {
        return capitalizationType;
    }

    public void setCapitalizationType(ReplenishmentType capitalizationType) {
        this.capitalizationType = capitalizationType;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Deposit.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("initialSum=" + initialSum)
                .add("interestRate=" + interestRate)
                .add("currency=" + currency)
                .add("term=" + term)
                .add("replenishmentType=" + replenishmentType)
                .add("replenishmentSum=" + replenishmentSum)
                .add("capitalizationType=" + capitalizationType)
                .toString();
    }
}
