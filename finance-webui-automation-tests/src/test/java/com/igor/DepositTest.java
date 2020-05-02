package com.igor;


import com.igor.bo.DepositBO;
import com.igor.dao.DepositDao;
import com.igor.model.daoModel.Currency;
import com.igor.model.daoModel.Deposit;
import com.igor.model.daoModel.ReplenishmentType;
import com.igor.model.daoModel.Term;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.Date;

import static com.igor.assertion.DepositAssertion.assertExchangeRate;
import static com.igor.assertion.DepositAssertion.assertTotal;
import static com.igor.utils.Constants.UAH_STR;
import static com.igor.utils.SqlUtil.updateDB;

public class DepositTest extends BaseTest {

    @DataProvider
    private Object[][] deposit() {
        updateDB();
        return new DepositDao().getAll().stream()
                .map(deposit -> new Object[]{deposit})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider = "deposit")
    void depositTest(Deposit deposit) {
        Currency currency = deposit.getCurrency();
        ReplenishmentType replenishmentType = deposit.getReplenishmentType();
        ReplenishmentType capitalizationType = deposit.getCapitalizationType();
        Term term = deposit.getTerm();

        DepositBO depositBO = new DepositBO();
        depositBO.setMainDepositInfo(currency, deposit.getInitialSum(), deposit.getInterestRate());
        depositBO.setDateAndTerms(term);
        depositBO.setAdditionalInfo(deposit.getReplenishmentSum(), replenishmentType, capitalizationType);
        depositBO.submit();
        Reporter.log(depositBO.toString());
        assertTotal(depositBO);
        if (!currency.getCode().equals(UAH_STR)) {
            assertExchangeRate(depositBO);
        }
    }

    @Test
    void exchangeRateTestUSD() {
        Currency currency = new Currency("USD");

        ReplenishmentType replenishmentType = new ReplenishmentType("none");
        ReplenishmentType capitalizationType = new ReplenishmentType("monthly");
        Term term = new Term(new Date(2019, 12, 13), 5, "months");

        DepositBO depositBO = new DepositBO();
        depositBO.setMainDepositInfo(currency, BigDecimal.valueOf(1000), BigDecimal.valueOf(4.5));
        depositBO.setDateAndTerms(term);
        depositBO.setAdditionalInfo(BigDecimal.valueOf(0), replenishmentType, capitalizationType);
        depositBO.submit();
        Reporter.log(depositBO.toString());
        assertTotal(depositBO);
        if (!currency.getCode().equals(UAH_STR)) {
            assertExchangeRate(depositBO);
        }
    }

    @Test
    void exchangeRateTestEUR() {
        Currency currency = new Currency("EUR");

        ReplenishmentType replenishmentType = new ReplenishmentType("none");
        ReplenishmentType capitalizationType = new ReplenishmentType("monthly");
        Term term = new Term(new Date(2019, 12, 13), 5, "months");

        DepositBO depositBO = new DepositBO();
        depositBO.setMainDepositInfo(currency, BigDecimal.valueOf(1000), BigDecimal.valueOf(4.5));
        depositBO.setDateAndTerms(term);
        depositBO.setAdditionalInfo(BigDecimal.valueOf(0), replenishmentType, capitalizationType);
        depositBO.submit();
        Reporter.log(depositBO.toString());
        assertTotal(depositBO);
        if (!currency.getCode().equals(UAH_STR)) {
            assertExchangeRate(depositBO);
        }
    }
}
