package com.darwindev.apps;

import org.jfree.ui.ApplicationFrame;

/**
 * Factory singleton
 * Created by Zheng on 07/03/2017.
 */
public class ApplicationFactory {
    public static ApplicationFrame generateApplication(String applicationName, String[] applicationArgs) throws Exception {
        ApplicationFrame applicationFrame = new ApplicationFrame(applicationName);
        switch (applicationName) {
            case "TimeMeasurement":
                applicationFrame = new TimeMeasurementApplication(applicationName, applicationArgs[0]);
                break;
            case "TimeComparation":
                applicationFrame = new TimeComparationApplication(applicationName, applicationArgs[0]);
                break;
            default:
                throw new Exception(applicationName + "is not a valid application.");
        }
        return applicationFrame;
    }
}
