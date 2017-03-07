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

import static com.darwindev.algo.AlgoName.*;

/**
 * Application - Time measurement
 * Created by Zheng on 07/03/2017.
 */
public class TimeMeasurementApplication extends ApplicationFrame {
    private AlgoName algorithmType;

    private XYSeries[] getTimeSeries() throws Exception {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        XYSeries[] res = new XYSeries[]{
                new XYSeries("UserTime"),
                new XYSeries("WallTime")
        };

        Algo algoInstance = AlgoFactory.generateAlgo(algorithmType);
        int begin, end, step;
        switch (algorithmType) {
            case AlgoFindMinimum:
                begin = 500000;
                end = 10000000;
                step = 100000;
                break;
            case AlgoSelectionSort:
                begin = 5000;
                end = 100000;
                step = 1000;
                break;
            case AlgoBubbleSort:
                begin = 500;
                end = 10000;
                step = 100;
                break;
            case AlgoMergeSort:
                begin = 50000;
                end = 1000000;
                step = 10000;
                break;
            case AlgoQuickSort:
                begin = 50000;
                end = 1000000;
                step = 10000;
                break;
            case AlgoBinarySearch:
                begin = 5000000;
                end = 100000000;
                step = 1000000;
                break;
            default:
                throw new Exception("Invalid algorithm type.");
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

    private XYDataset createDataset( ) throws Exception {
//        warmUp();
        final XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries[] series = getTimeSeries();
        for (XYSeries serie : series) {
            dataset.addSeries(serie);
        }
        return dataset;
    }

    public TimeMeasurementApplication(String applicationTitle, AlgoName sortAlgo) throws Exception {
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