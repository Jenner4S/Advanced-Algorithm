package com.darwindev;

import java.awt.Shape;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.ShapeUtilities;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Created by Zheng on 08/02/2017.
 *
 */
public class TimeMeasurementTargets extends ApplicationFrame {

    private static int findMinimumValue(int[] inputData) {
        int min = inputData[0];
        for (int anInputData : inputData) {
            if (anInputData < min) min = anInputData;
        }
        return min;
    }

    private static int findMinimumIndex(int[] inputData, int begin, int end) {
        int res = begin;
        for (int i = begin + 1; i < end; i++) {
            if (inputData[i] < inputData[res]) {
                res = i;
            }
        }
        return res;
    }

    private static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    private static void selectionSort(int[] inputData) {
        if (inputData.length < 2) return;
        for (int i = 0; i < inputData.length; i++) {
            swap(inputData, i, findMinimumIndex(inputData, i, inputData.length));
        }
    }

    private static void bubbleSort(int[] inputData) {
        if (inputData.length < 2) return;
        boolean hadToSwap;
        do {
            hadToSwap = false;
            for (int i = 0; i != inputData.length - 1; ++i) {
                if (inputData[i] > inputData[i + 1]) {
                    swap(inputData, i, i + 1);
                    hadToSwap = true;
                }
            }
        } while (hadToSwap);
    }

    private static void mergeSorted(int inputData[], int begin, int middle, int end) {
        int[] tmp = new int[middle - begin];
        System.arraycopy(inputData, begin, tmp, 0, tmp.length);
        int i = 0, j = middle, dest = begin;
        while ((i < tmp.length) && (j < end)) {
            inputData[dest++] = (tmp[i] < inputData[j]) ? tmp[i++] : inputData[j++];
        }
        while (i < tmp.length) {
            inputData[dest++] = tmp[i++];
        }
    }

    private static void mergeSort(int[] inputData) {
        mergeSort(inputData, 0, inputData.length);
    }

    private static void mergeSort(int[] inputData, int begin, int end) {
        if ((end - begin) < 2) {
            return;
        }
        int middle = (end + begin) / 2;
        mergeSort(inputData, begin, middle);
        mergeSort(inputData, middle, end);
        mergeSorted(inputData, begin, middle, end);
    }

    private static int partition(int[] inputData, int begin, int end, int pivotIdx) {
        swap(inputData, pivotIdx, --end);
        pivotIdx = end;
        int pivot = inputData[pivotIdx];
        // invariant is that everything before begin is known to be < pivot
        // and everything after end is known to be >= pivot
        while (begin != end) {
            if (inputData[begin] >= pivot) {
                swap(inputData, begin, --end);
            } else {
                ++begin;
            }
        }
        swap(inputData, pivotIdx, begin);
        return begin;
    }

    private static void quickSort(int[] inputData) {
        quickSort(inputData, 0, inputData.length);
    }

    private static void quickSort(int[] inputData, int begin, int end) {
        if ((end - begin) < 2) {
            return;
        }
        int m = partition(inputData, begin, end, (end + begin) / 2);
        quickSort(inputData, begin, m);
        quickSort(inputData, m + 1, end); // +1 for convergence
    }

    // index of first element >= v
    private static int lowerBound(int[] data, int v) {
        return lowerBound(data, v, 0, data.length);
    }

    private static int lowerBound(int[] data, int v, int begin, int end) {
        if (begin == end) {
            return begin;
        }
        int m = (begin + end) / 2;
        return data[m] < v ? lowerBound(data, v, m + 1, end) : lowerBound(data, v, begin, m);
    }

    private static int binarySearch(int[] inputData, int val) {
        for (int i = 0; i != inputData.length; ++i) {
            inputData[i] = 2 * i;
        }
        return lowerBound(inputData, val);
    }

    private XYSeries[] getTimeSeries()
    {
		/* You need to customize this function, this
		 * exception is just a reminder to do so
		 */
//        if (true)
//        {
//            throw new UnsupportedOperationException("getTimeSeries()");
//        }

        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );

		/*
		 * Set this array to the accurate length for your series
		 */
//        XYSeries[] res = new XYSeries[]{
//				new XYSeries( "UserTime" ),
//				new XYSeries( "WallTime" )
//		};
        XYSeries[] res = new XYSeries[]{
                new XYSeries( "Selection Sort" ),
                new XYSeries( "Bubble Sort" ),
                new XYSeries( "Merge Sort" ),
                new XYSeries( "Quick Sort" ),
        };

//        for(int i = 500000; i <= 10000000 ; i += 100000)
//        for(int i = 5000; i <= 100000 ; i += 1000)
//        for(int i = 500; i <= 10000 ; i += 100)
//        for(int i = 50000; i <= 1000000 ; i += 10000)
//        for(int i = 50000; i <= 1000000 ; i += 10000)
        for (int n = 0; n < res.length; n++) {
            for (int i = 500; i <= 10000; i += 100) {
			/*
			 * Here, do the setUp(), the part of your test
			 * that need to be done before each calculus
			 * but does count in the time mesured
			 */
                int[] random_test_data = RandomData.generate1d(i, 0, 10000000);
                int find_num = RandomData.generate1d(1, 0, random_test_data.length - 1)[0];

			/*
			 * This is two way to calculate the time spent
			 * cpu time should be more accurate, but its precision
			 * depends on your system (it can be nanoseconds)
			 */
//            long beginWallClockTime = System.nanoTime();
                long beginCpuTime = bean.getCurrentThreadCpuTime();

			/*
			 * Here, call the code you want to test
			 */
//			findMinimumValue(random_test_data);
//            selectionSort(random_test_data);
//            bubbleSort(random_test_data);
//            mergeSort(random_test_data);
//            quickSort(random_test_data);
//            binarySearch(random_test_data, find_num);
                switch (n) {
                    case 0:
                        selectionSort(random_test_data);
                        break;
                    case 1:
                        bubbleSort(random_test_data);
                        break;
                    case 2:
                        mergeSort(random_test_data);
                        break;
                    case 3:
                        quickSort(random_test_data);
                        break;
                    default:
                        break;
                }

//            long wallClockDuration = System.nanoTime() - beginWallClockTime;
                long cpuTimeDuration = bean.getCurrentThreadCpuTime() - beginCpuTime;

			/*
			 * Add these duration to your result the way you want
			 */
//            res[0].add(i,wallClockDuration);
                res[n].add(i, cpuTimeDuration);

			/*
			 * Forcing GC to run, so it reduce the chance of it
			 * running during your time measurement phase
			 */
                System.gc();
                System.out.println(i);
            }
        }
        return res;
    }

    /* This function goal is just to make sure everything
	 * in Java and the virtual machine is loaded correctly
	 * before actually measuring time.
	 *
	 * Otherwise, first time measurement can be more important
	 * than the real algorithm execution time.
	 *
	 * Just run your algorithm for about a second of time
	 */
    private void warmUp()
    {
//        for (int i = 0; i < 10000; ++i)
//        for (int i = 0; i < 100; ++i)
//        for (int i = 0; i < 100; ++i)
//        for (int i = 0; i < 10000; ++i)
        for (int i = 0; i < 10; ++i)
        {
            int[] random_warmup_data = RandomData.generate1d(5000, 0, 10000000);
            int find_num = RandomData.generate1d(1, 0, random_warmup_data.length - 1)[0];

//           findMinimumValue(random_warmup_data);
//           selectionSort(random_warmup_data);
//            bubbleSort(random_warmup_data);
//            mergeSort(random_warmup_data);
//            quickSort(random_warmup_data);
//            binarySearch(random_warmup_data, find_num);
            selectionSort(random_warmup_data);
            bubbleSort(random_warmup_data);
            mergeSort(random_warmup_data);
            quickSort(random_warmup_data);
        }
    }

    private XYDataset createDataset( )
    {
        warmUp();
        final XYSeriesCollection dataset = new XYSeriesCollection( );

		/*
		 * You might want to add a loop here
		 */
        XYSeries[] series = getTimeSeries();


        for(XYSeries serie : series )
        {
            dataset.addSeries(serie);
        }


        return dataset;
    }

    private static void draw_graph() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
        TimeMeasurementTargets chart = new TimeMeasurementTargets(
//                "Time Measurement", "Time usage of finding minimum of an array"
//                "Time Measurement", "Time usage of selection sort"
//                "Time Measurement", "Time usage of bubble sort"
//                "Time Measurement", "Time usage of merge sort"
//                "Time Measurement", "Time usage of quick sort"
//                "Time Measurement", "Time usage of binary search"
                "Time Measurement", "Comparision of sorting algorithm"
        );
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }

    /*
	 * The constructor is actually doing most of the work here,
	 * but you don't need to change it
	 */
    private TimeMeasurementTargets(String applicationTitle, String chartTitle)
    {
        super(applicationTitle);
        JFreeChart xylineChart = ChartFactory.createScatterPlot(
                chartTitle ,
                "Array Size" ,
                "Time (ns)" ,
                createDataset() ,
                PlotOrientation.VERTICAL,
                true , false , false);

        Shape cross = ShapeUtilities.createDiagonalCross(3, 1);

        ChartPanel chartPanel = new ChartPanel( xylineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        final XYPlot plot = xylineChart.getXYPlot( );

		/*
		 * OPTIONNAL :
		 *
		 * If you want to customize the series layout, but the program does it
		 * quite well by itself
		 */
		/*XYItemRenderer renderer = plot.getRenderer( );
		renderer.setSeriesPaint( 2 , Color.RED );
		renderer.setSeriesPaint( 1 , Color.GREEN );
		renderer.setSeriesPaint( 0 , Color.YELLOW );
		renderer.setSeriesShape(0, cross);

		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);*/

        setContentPane( chartPanel );
    }

    public static void main( String[ ] args )
    {
        draw_graph();
    }
}
