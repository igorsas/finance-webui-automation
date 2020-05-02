package com.igor.po;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.math.BigDecimal;

import static com.igor.utils.MoneyUtil.clearTextForConverting;

public class DepositPO extends BasePO {
    //main data
    @FindBy(name = "currency")
    private WebElement currency;
    @FindBy(name = "sum")
    private WebElement sumInputField;
    @FindBy(name = "interest_rate")
    private WebElement interestRateInputField;
    @FindBy(name = "start_date")
    private WebElement startDateInputField;
    @FindBy(name = "term")
    private WebElement termInputField;
    @FindBy(name = "term_length")
    private WebElement termType;

    //adding money
    @FindBy(name = "replenishment")
    private WebElement replenishment;
    @FindBy(name = "rep_sum_reg")
    private WebElement sumOfRegularReplenishmentInputField;
    @FindBy(name = "add_fix")
    private WebElement addFixSumButton;

    //capitalization
    @FindBy(name = "cap")
    private WebElement typeOfCapitalization;

    //withdrawals
    @FindBy(name = "add_wdraw")
    private WebElement addWithDrawalButton;

    //inflation
    @FindBy(name = "infl_level")
    private WebElement inflationLevelInputField;

    @FindBy(name = "submit_it")
    private WebElement submitButton;

    //result
    @FindBy(xpath = "//*[@id='info_span']/preceding-sibling::div[@class='info-block']/table[@class='summary_table']/tbody/tr[2]/td[2]")
    private WebElement initialSumInSelectedCurrency;
    @FindBy(xpath = "//*[@id='info_span']/preceding-sibling::div[@class='info-block']/table[@class='summary_table']/tbody/tr[2]/td[3]")
    private WebElement initialSumInUAH;
    @FindBy(xpath = "//*[@id=\"result\"]/div[2]/table/tbody/tr[3]/td[2]")
    private WebElement sumOfPercentsInSelectedCurrency;
    @FindBy(xpath = "//*[@id=\"result\"]/div[2]/table/tbody/tr[3]/td[3]")
    private WebElement sumOfPercentInUAH;
    @FindBy(xpath = "//*[@id='info_span']/preceding-sibling::div[@class='info-block']/table[@class='summary_table']/tbody/tr[last()]/td[2]")
    private WebElement finalSum;
    @FindBy(id = "info_span")
    private WebElement infoAboutRate;

    public BigDecimal getInitialSumInSelectedCurrency() {
        return new BigDecimal(clearTextForConverting(initialSumInSelectedCurrency.getText()));
    }

    public BigDecimal getInitialSumInUAH() {
        return new BigDecimal(clearTextForConverting(initialSumInUAH.getText()));
    }

    public BigDecimal getSumOfPercentsInSelectedCurrency() {
        return new BigDecimal(clearTextForConverting(sumOfPercentsInSelectedCurrency.getText()));
    }

    public BigDecimal getSumOfPercentInUAH() {
        return new BigDecimal(clearTextForConverting(sumOfPercentInUAH.getText()));
    }

    public BigDecimal getFinalSum() {
        return new BigDecimal(clearTextForConverting(finalSum.getText()));
    }

    public String getInfoAboutRate() {
        return infoAboutRate.getText();
    }

    public DepositPO setCurrency(String currencyCode) {
        Select drpCurrency = new Select(currency);
        drpCurrency.selectByValue(currencyCode);
        return this;
    }

    public DepositPO setSum(BigDecimal sum) {
        sumInputField.clear();
        sumInputField.sendKeys(sum.toString());
        return this;
    }

    public DepositPO setInterestRate(BigDecimal percent) {
        interestRateInputField.clear();
        interestRateInputField.sendKeys(percent.toString());
        return this;
    }

    public DepositPO setStartDate(String date) {
        startDateInputField.clear();
        startDateInputField.sendKeys(date);
        return this;
    }

    public DepositPO setTerm(Integer term) {
        termInputField.clear();
        termInputField.sendKeys(term.toString());
        return this;
    }

    public DepositPO setTermType(String type) {
        Select drpTermType = new Select(termType);
        drpTermType.selectByValue(type);
        return this;
    }

    public DepositPO setReplenishment(String replenishmentType) {
        Select drpReplenishment = new Select(replenishment);
        drpReplenishment.selectByValue(replenishmentType);
        return this;
    }

    public DepositPO setSumOfRegularReplenishment(BigDecimal sum) {
        sumOfRegularReplenishmentInputField.clear();
        sumOfRegularReplenishmentInputField.sendKeys(sum.toString());
        return this;
    }

    public DepositPO setTypeOfCapitalization(String type) {
        Select drpTypeOfCapitalization = new Select(typeOfCapitalization);
        drpTypeOfCapitalization.selectByValue(type);
        return this;
    }

    public DepositPO calculate() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        return this;
    }
}
