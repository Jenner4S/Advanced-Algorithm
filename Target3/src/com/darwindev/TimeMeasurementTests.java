package com.darwindev;

import com.darwindev.apps.ApplicationFactory;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * Time measurement test.
 * Created by Zheng on 08/02/2017.
 */
public class TimeMeasurementTests {
    public static void draw(String applicationTitle, String type) {
        String[] applicationArgs = {
                type
        };
        ApplicationFrame chart = ApplicationFactory.generateApplication(applicationTitle, applicationArgs);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
    public static void main(String args[]) {
        draw("TimeMeasurement", "FindMinimum");
    }
}
