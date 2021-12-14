package com.kainos.ea.seleniumframework.site;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.kainos.ea.seleniumframework.driver.DriverManager;
import com.kainos.ea.seleniumframework.log.Log;
import com.kainos.ea.seleniumframework.properties.CommonProperties;
import com.kainos.ea.seleniumframework.properties.PropertyLoader;

/**
 * Helper class for using the Selenium web driver.
 */
public class Site {

    private static final long DEFAULT_WAIT_TIMEOUT = 10;
    protected WebDriver driver;

    public Site(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Effectively a getter for the web driver wait object. Just makes code more readable to not have "get" at the front.
     * <p>
     * E.g. site.webDriverWait().until(...)
     *
     * @return a web driver wait object
     */
    public static WebDriverWait webDriverWait(long timeoutInSeconds) {

        return new WebDriverWait(DriverManager.getDriver(), timeoutInSeconds);
    }

    /**
     * Like <code>webDriverWait(long timeoutInSeconds) but with the default timeout in seconds.</code>
     *
     * @return a web driver wait object
     */
    public static WebDriverWait webDriverWait() {

        long timeoutInSeconds = DEFAULT_WAIT_TIMEOUT;

        String timeoutProperty = PropertyLoader.getProperty(CommonProperties.SELENIUM_DRIVER_WAIT_TIMEOUT);

        if (timeoutProperty != null) {
            try {
                timeoutInSeconds = Long.parseLong(timeoutProperty);
            } catch (NumberFormatException e) {
                throw new SiteCreationException("The format of " + timeoutProperty + " for the driver_wait_timeout property is invalid", e);
            }
        }

        return webDriverWait(timeoutInSeconds);
    }

    public static boolean verifyPageTitle(String actual, String expected) {
        if (actual.contains(expected)) {
            Log.Info("Page loaded: " + actual);

            return true;
        }

        throw new NotFoundException("Page title did not match: " + expected + " but got: " + actual);
    }

    public static void goToURL(String webAddress) {
        DriverManager.getDriver().get(webAddress);
    }

    public static void closeWindow() {
        DriverManager.getDriver().close();
    }
}
