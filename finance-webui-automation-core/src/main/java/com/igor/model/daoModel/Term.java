package com.igor.model.daoModel;


import javax.persistence.*;
import java.sql.Date;
import java.util.StringJoiner;

@Entity
@Table(name = "term")
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "period")
    private int period;
    @Column(name = "period_type")
    private String periodType;

    public Term() {
    }

    public Term(Date startDate, int period, String periodType) {
        this.startDate = startDate;
        this.period = period;
        this.periodType = periodType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Term.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("startDate=" + startDate)
                .add("period=" + period)
                .add("periodType='" + periodType + "'")
                .toString();
    }
}
