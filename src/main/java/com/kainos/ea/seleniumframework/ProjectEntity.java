package com.kainos.ea.seleniumframework;

import com.kainos.ea.seleniumframework.properties.CommonProperties;
import com.kainos.ea.seleniumframework.properties.PropertyLoader;

public class ProjectEntity {

    public static String getProjectName = PropertyLoader.getProperty(CommonProperties.PROJECT_NAME) != null ?
            PropertyLoader.getProperty(CommonProperties.PROJECT_NAME) :
            "AUTOMATED SPECS TEST";
}
