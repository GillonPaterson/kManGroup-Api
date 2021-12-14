package com.kainos.ea.seleniumframework.driver.producer.desktop.edge;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import com.kainos.ea.seleniumframework.driver.producer.BaseLocalDriver;
import com.kainos.ea.seleniumframework.driver.producer.WebDriverProducer;

public class EdgeLocalWebDriverProducer extends BaseLocalDriver implements WebDriverProducer {

    @Override
    public WebDriver produce() {
        if (browserVersion != null) {
            WebDriverManager.edgedriver().forceDownload().browserVersion(browserVersion).setup();
        } else {
            WebDriverManager.edgedriver().forceDownload().setup();
        }

        return new EdgeDriver();
    }
}