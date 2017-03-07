package com.darwindev.apps;

import com.darwindev.RandomData;
import com.darwindev.algo.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 * Application - Time measurement
 * Created by Zheng on 07/03/2017.
 */
public class TimeMeasurementApplication extends ApplicationFrame {
    private String algorithmType;

    private XYSeries[] getTimeSeries() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        XYSeries[] res = new XYSeries[]{
                new XYSeries("UserTime"),
                new XYSeries("WallTime")
        };

        Algo algoInstance;
        int begin, end, step;
        switch (algorithmType) {
            case "FindMinimum":
                begin = 500000;
                end = 10000000;
                step = 100000;
                algoInstance = new FindMinimum();
                break;
            case "SelectionSort":
                begin = 5000;
                end = 100000;
                step = 1000;
                algoInstance = new SelectionSort();
                break;
            case "BubbleSort":
                begin = 500;
                end = 10000;
                step = 100;
                algoInstance = new BubbleSort();
                break;
            case "MergeSort":
                begin = 50000;
                end = 1000000;
                step = 10000;
                algoInstance = new MergeSort();
                break;
            case "QuickSort":
                begin = 50000;
                end = 1000000;
                step = 10000;
                algoInstance = new QuickSort();
                break;
            case "BinarySearch":
                begin = 5000000;
                end = 100000000;
                step = 1000000;
                algoInstance = new BinarySearch();
                break;
            default:
                begin = 0;
                end = 1;
                step = 1;
                algoInstance = new FindMinimum();
                break;
        }

        for (int i = begin; i <= end; i += step) {
            int[] data = RandomData.generate1d(i, 0, 10000000);

            long beginWallClockTime = System.nanoTime();
            long beginCpuTime = bean.getCurrentThreadCpuTime();

            algoInstance.perform(data);

            long wallClockDuration = System.nanoTime() - beginWallClockTime;
            long cpuTimeDuration = bean.getCurrentThreadCpuTime() - beginCpuTime;

            res[0].add(i, wallClockDuration);
            res[1].add(i, cpuTimeDuration);

            System.gc();
        }
        return res;
    }

    private XYDataset createDataset( ) {
//        warmUp();
        final XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries[] series = getTimeSeries();
        for (XYSeries serie : series) {
            dataset.addSeries(serie);
        }
        return dataset;
    }

    public TimeMeasurementApplication(String applicationTitle, String sortAlgo) {
        super(applicationTitle);
        algorithmType = sortAlgo;
        JFreeChart xylineChart = ChartFactory.createScatterPlot(
                "Time usage of " + sortAlgo,
                "Array Size",
                "Time (ns)",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, false, false
        );
        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

}