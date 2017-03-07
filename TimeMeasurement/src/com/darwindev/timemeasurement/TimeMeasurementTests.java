package com.darwindev.timemeasurement;

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
        testDraw(ApplicationType.TimeMeasurement, AlgoName.AlgoFindMinimum);
        testDraw(ApplicationType.TimeMeasurement, AlgoName.AlgoSelectionSort);
        testDraw(ApplicationType.TimeMeasurement, AlgoName.AlgoBubbleSort);
        testDraw(ApplicationType.TimeMeasurement, AlgoName.AlgoMergeSort);
        testDraw(ApplicationType.TimeMeasurement, AlgoName.AlgoQuickSort);
        testDraw(ApplicationType.TimeMeasurement, AlgoName.AlgoBinarySearch);
        testDraw(ApplicationType.TimeComparison, AlgoName.AlgoAllSort);
    }

}
