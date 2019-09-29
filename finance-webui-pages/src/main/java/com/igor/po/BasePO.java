//package com.igor.po;
//
//import com.igor.utils.driver.DriverManager;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public abstract class BasePO {
//    protected WebDriver driver;
//    protected WebDriverWait webDriverWait;
//
//    protected BasePO() {
//        driver = DriverManager.getDriver();
//        webDriverWait = new WebDriverWait(driver, 30);
//        PageFactory.initElements(driver, this);
//    }
//}
