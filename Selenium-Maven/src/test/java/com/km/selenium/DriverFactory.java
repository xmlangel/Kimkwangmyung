package com.km.selenium;

import com.km.selenium.config.DriverType;
import com.km.selenium.listeners.ScreenshotListener;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.km.selenium.config.DriverType.determineEffectiveDriverType;

@Listeners(ScreenshotListener.class)
public class DriverFactory {

    private static List<WebDriver> webDriverPool = Collections.synchronizedList(new ArrayList<WebDriver>());
    private static ThreadLocal<WebDriver> driverThread;

    @BeforeSuite
    public static void instantiateDriverObject() {

        final DriverType desiredDriver = determineEffectiveDriverType(System.getProperty("browser"));

        driverThread = new ThreadLocal<WebDriver>() {
            @Override
            protected WebDriver initialValue() {
                final WebDriver webDriver = desiredDriver.configureDriverBinaryAndInstantiateWebDriver();
                webDriverPool.add(webDriver);
                return webDriver;
            }
        };
    }

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    @AfterMethod
    public static void clearCookies() {
        getDriver().manage().deleteAllCookies();
    }

    @AfterSuite
    public static void closeDriverObject() {
        for (WebDriver driver : webDriverPool) {
            driver.quit();
        }
    }
}