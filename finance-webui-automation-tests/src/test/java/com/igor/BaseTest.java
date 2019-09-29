package com.igor;

import com.igor.utils.driver.DriverManager;
import com.igor.utils.property.Property;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.igor.utils.driver.DriverManager.getDriver;

public abstract class BaseTest {
    @BeforeMethod
    public void setStartedPage() {
        getDriver().get(Property.getProperty("initial_url", "finance-webui-automation-tests/src/test/resources/url.properties"));
    }

    @AfterMethod
    public void quitDriver() {
        DriverManager.quit();
    }
}
