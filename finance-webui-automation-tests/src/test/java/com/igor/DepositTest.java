package com.igor;


import com.igor.bo.DepositBO;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static com.igor.assertion.DepositAssertion.assertExchangeRate;

public class DepositTest extends BaseTest{

    @Test()
    void test(){
        DepositBO depositBO = new DepositBO();
        depositBO.setMainDepositInfo("USD", BigDecimal.valueOf(900.0), BigDecimal.valueOf(15.0));
        depositBO.setDateAndTerms("15/10/19", 5, "months");
        depositBO.setAdditionalInfo(BigDecimal.valueOf(5.0), "monthly", "monthly");
        depositBO.submit();
        assertExchangeRate(depositBO);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
