package ezgisevi;


import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class LineChartDemo1 extends ApplicationFrame {
	private Child child;

    public LineChartDemo1(final String title, Child child) {
        super(title);
        //child = new Child();
        this.child = child;
    }

    public CategoryDataset createDataset() {
        final String series1 = "Doğruluk Skoru";
        final String series2 = "Hız Skoru";

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Exercise ex : child.getExercises()) {
            String type = ex.getName();
            float value = ex.getAccuracyRate();
            dataset.addValue(value, series1, type);
            float value2 = ex.getSpeedScore();
            dataset.addValue(value2, series2, type);
        }

        return dataset;
    }

    public JFreeChart createChart(final CategoryDataset dataset) {
        final JFreeChart chart = ChartFactory.createLineChart(
                "İlerleme Takip Grafiği",
                "Exercise",
                "Value",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinePaint(Color.lightGray);

        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);

        return chart;
    }

    public static void getGraphic(String graphicTitle, Child currentChild) {
        LineChartDemo1 demo = new LineChartDemo1(graphicTitle, currentChild);
        final CategoryDataset dataset = demo.createDataset();
        final JFreeChart chart = demo.createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));

        JFrame frame = new JFrame("Gelişim Grafiği");
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
        
    }
}

