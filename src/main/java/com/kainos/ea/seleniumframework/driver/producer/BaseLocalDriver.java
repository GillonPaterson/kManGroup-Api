package com.kainos.ea.seleniumframework.driver.producer;

import com.kainos.ea.seleniumframework.properties.CommonProperties;
import com.kainos.ea.seleniumframework.properties.PropertyLoader;

public class BaseLocalDriver {

    protected final static String browserVersion = PropertyLoader.getProperty(CommonProperties.BROWSER_VERSION);
}
