package com.igor;

import com.igor.po.DepositPO;
import org.testng.annotations.Test;

public class DepositTest{

    @Test()
    void test(){
        DepositPO depositPO = new DepositPO();
        depositPO.setCurrency("USD");
    }
}
