package com.darwindev;

import static com.darwindev.ApplicationType.*;
import static com.darwindev.AlgoName.*;

import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * Time measurement test.
 * Created by Zheng on 08/02/2017.
 */
public class TimeMeasurementTests {

    public static void testDraw(ApplicationType applicationTitle, AlgoName type) throws Exception {
        AlgoName[] applicationArgs = {
                type
        };
        ApplicationFrame chart = ApplicationFactory.generateApplication(applicationTitle, applicationArgs);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }

    public static void main(String args[]) throws Exception {
        testDraw(TimeMeasurement, AlgoFindMinimum);
        testDraw(TimeMeasurement, AlgoSelectionSort);
        testDraw(TimeMeasurement, AlgoBubbleSort);
        testDraw(TimeMeasurement, AlgoMergeSort);
        testDraw(TimeMeasurement, AlgoQuickSort);
        testDraw(TimeMeasurement, AlgoBinarySearch);
        testDraw(TimeComparison, AlgoAllSort);
    }

}
