package com.kainos.ea.seleniumframework.driver.producer.desktop.opera;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import com.kainos.ea.seleniumframework.driver.producer.BaseLocalDriver;
import com.kainos.ea.seleniumframework.driver.producer.WebDriverProducer;

public class OperaLocalWebDriverProducer extends BaseLocalDriver implements WebDriverProducer {

    @Override
    public WebDriver produce() {
        if (browserVersion != null) {
            WebDriverManager.operadriver().browserVersion(browserVersion).setup();
        } else {
            WebDriverManager.operadriver().setup();
        }

        return new OperaDriver();
    }
}

