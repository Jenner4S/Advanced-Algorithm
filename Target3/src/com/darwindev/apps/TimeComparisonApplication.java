package com.darwindev.apps;

import com.darwindev.RandomData;
import com.darwindev.algo.*;
import static com.darwindev.algo.AlgoName.*;
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
import java.util.ArrayList;

/**
 * Application - Time comparation
 * Created by Zheng on 07/03/2017.
 */
public class TimeComparisonApplication extends ApplicationFrame {
    private AlgoName[] algorithmTypes;

    private ArrayList<XYSeries> getTimeSeries() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        ArrayList<XYSeries> res = new ArrayList<>();
        for (AlgoName algorithmType: algorithmTypes) {
            XYSeries series = new XYSeries(algorithmType);

            Algo algoInstance = AlgoFactory.generateAlgo(algorithmType);

            for (int i = 500; i <= 10000; i += 100) {
                int[] data = RandomData.generate1d(i, 0, 10000000);
                long beginCpuTime = bean.getCurrentThreadCpuTime();

                algoInstance.perform(data);

                long cpuTimeDuration = bean.getCurrentThreadCpuTime() - beginCpuTime;
                series.add(i, cpuTimeDuration);
                System.gc();
            }

            res.add(series);
        }
        return res;
    }

    private XYDataset createDataset( ) {
//        warmUp();
        final XYSeriesCollection dataset = new XYSeriesCollection();
        ArrayList<XYSeries> series = getTimeSeries();
        for (XYSeries serie : series) {
            dataset.addSeries(serie);
        }
        return dataset;
    }

    public TimeComparisonApplication(String applicationTitle, AlgoName chartTitle) {
        super(applicationTitle);
        algorithmTypes = new AlgoName[] {
                AlgoSelectionSort,
                AlgoBubbleSort,
                AlgoMergeSort,
                AlgoQuickSort
        };
        JFreeChart xylineChart = ChartFactory.createScatterPlot(
                "Comparation of " + chartTitle,
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
