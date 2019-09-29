package com.igor.po;

import com.igor.utils.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DepositPO {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    public DepositPO(){
        driver = DriverManager.getDriver();
        driver.get("initial_url");
        webDriverWait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }
    //main data
    @FindBy(name = "currency")
    private WebElement currency;
    @FindBy(name = "sum")
    private WebElement sumInputField;
    @FindBy(name = "interest_rate")
    private WebElement interestRateInputField;
    @FindBy(name = "start_date")
    private WebElement startDateButton;
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

    public DepositPO setCurrency(String currencyCode){
        Select drpCurrency = new Select(currency);
        drpCurrency.selectByValue(currencyCode);
        return this;
    }

    
}
