package com.igor;

import com.igor.utils.property.Property;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.igor.utils.driver.DriverManager.getDriver;
import static com.igor.utils.driver.DriverManager.quit;

public abstract class BaseTest {
    @BeforeMethod()
    public void setStartedPage() {
        getDriver().get(Property.getProperty("initial_url"));
    }

    @AfterMethod
    public void quitDriver() {
        quit();
    }
}
