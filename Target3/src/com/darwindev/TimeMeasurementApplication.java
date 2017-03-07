package com.darwindev;

import com.darwindev.Algo;
import com.darwindev.AlgoFactory;
import com.darwindev.AlgoName;
import com.darwindev.RandomData;
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
    private AlgoName algorithmType;

    private XYSeries[] getTimeSeries(Boolean warmUp) throws Exception {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        XYSeries[] res = new XYSeries[]{
                new XYSeries("UserTime"),
                new XYSeries("WallTime")
        };

        Algo algoInstance = AlgoFactory.generateAlgo(algorithmType);
        int begin = algoInstance.rangeBegin(), end = algoInstance.rangeEnd(), step = algoInstance.rangeStep();
        if (warmUp) {
            begin /= 10; end /= 10; step /= 10;
        }
        for (int i = begin; i <= end; i += step) {
            int[] data = RandomData.generate1d(i, 0, Integer.MAX_VALUE);

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

    private void warmUp() throws Exception {
        getTimeSeries(true);
    }

    private XYDataset createDataset( ) throws Exception {
        warmUp();
        final XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries[] series = getTimeSeries(false);
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