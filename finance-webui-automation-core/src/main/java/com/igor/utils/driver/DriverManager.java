package com.igor.utils.driver;

import com.igor.utils.property.Property;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static ThreadLocal<WebDriver> DRIVER_POOL = new ThreadLocal<>();
    private static final String NAME = Objects.requireNonNull(Property.getProperty("name"));
    private static final String PATH = Objects.requireNonNull(Property.getProperty("path"));
    private static final boolean HEADLESS_MODE = Boolean.parseBoolean(Property.getProperty("headless"));

    static {
        System.setProperty(NAME, PATH);
    }

    private DriverManager() { }

    public static WebDriver getDriver()
    {
        if(Objects.isNull(DRIVER_POOL.get())) {
            initializeDriver();
        }
        return DRIVER_POOL.get();
    }

    private static void initializeDriver(){
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(HEADLESS_MODE);
        DRIVER_POOL.set(new ChromeDriver(options));
        DRIVER_POOL.get().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        DRIVER_POOL.get().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        DRIVER_POOL.get().manage().timeouts().setScriptTimeout(50, TimeUnit.SECONDS);
        DRIVER_POOL.get().manage().window().maximize();
    }

    public static void quit() {
        DRIVER_POOL.get().quit();
        DRIVER_POOL.set(null);
    }
}
