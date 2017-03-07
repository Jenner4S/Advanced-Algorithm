package com.darwindev.timemeasurement;

import org.jfree.ui.ApplicationFrame;

/**
 * Factory singleton
 * Created by Zheng on 07/03/2017.
 */
public class ApplicationFactory {
    public static ApplicationFrame generateApplication(ApplicationType applicationName, AlgoName[] applicationArgs) throws Exception {
        ApplicationFrame applicationFrame;
        switch (applicationName) {
            case TimeMeasurement:
                applicationFrame = new TimeMeasurementApplication("TimeMeasurement", applicationArgs[0]);
                break;
            case TimeComparison:
                applicationFrame = new TimeComparisonApplication("TimeComparison", applicationArgs[0]);
                break;
            default:
                throw new Exception(applicationName + "is not a valid application.");
        }
        return applicationFrame;
    }
}
