package com.igor;

import com.igor.utils.driver.DriverManager;
import com.igor.utils.property.Property;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    @BeforeMethod
    public void setStartedPage() {
        DriverManager.getDriver().get(Property.getProperty("initial_url"));
    }

    @AfterMethod
    public void quitDriver() {
        DriverManager.quit();
    }
}
