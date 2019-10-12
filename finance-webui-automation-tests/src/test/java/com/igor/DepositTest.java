package com.igor;


import com.igor.bo.DepositBO;
import com.igor.dao.DepositDao;
import com.igor.model.daoModel.Currency;
import com.igor.model.daoModel.Deposit;
import com.igor.model.daoModel.ReplenishmentType;
import com.igor.model.daoModel.Term;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.igor.assertion.DepositAssertion.assertExchangeRate;
import static com.igor.assertion.DepositAssertion.assertTotal;
import static com.igor.utils.Constants.UAH_STR;
import static com.igor.utils.SqlUtil.updateDB;

public class DepositTest extends BaseTest {

    @DataProvider
    private Object[][] deposit() {
        updateDB();
        return new DepositDao().getAll().stream()
                .map(student -> new Object[]{student})
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
        assertTotal(depositBO);
        if(!currency.getCode().equals(UAH_STR)) {
            assertExchangeRate(depositBO);
        }
    }
}
