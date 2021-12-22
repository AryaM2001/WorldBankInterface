package httpTest;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import com.google.gson.JsonArray;
/**
 * Class for viewer Bar method
 * @author Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 *
 */
public class BarViewer implements Viewer{
	public JPanel west;

	public Analysis analy;
	
	public ArrayList<Double[]> data_list = new ArrayList<Double[]>();
	
	/**
	 * Constructor for BarViewer which creates the Bar Viewer for panel
	 * @param _west JPanel for the JFrame
	 * @param _analy Analysis object 
	 */
	public BarViewer(JPanel _west, Analysis _analy) {
		west = _west;

		analy = _analy;

		if (data_list.size() == 3) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for (int i = 0; i < analy.getTo() - analy.getFrom() + 1; i++) {
				dataset.setValue(data_list.get(0)[i], analy.getType1(), Integer.toString(analy.getFrom() + i));

			}

			for (int i = 0; i < analy.getTo() - analy.getFrom() + 1; i++) {
				dataset.setValue(data_list.get(1)[i], analy.getType1(), Integer.toString(analy.getFrom() + i));

			}
			DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
			for (int i = 0; i < analy.getTo() - analy.getFrom() + 1; i++) {
				dataset2.setValue(data_list.get(2)[i], analy.getType1(), Integer.toString(analy.getFrom() + i));

			}

			CategoryPlot plot = new CategoryPlot();
			BarRenderer barrenderer1 = new BarRenderer();
			BarRenderer barrenderer2 = new BarRenderer();

			plot.setDataset(0, dataset);

			plot.setRenderer(0, barrenderer1);

			CategoryAxis domainAxis = new CategoryAxis("Year");

			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis(""));

			plot.setDataset(1, dataset2);

			plot.setRenderer(1, barrenderer2);

			plot.setRangeAxis(1, new NumberAxis("US$"));

			plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis

			plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

			JFreeChart barChart = new JFreeChart(
					analy.getType1() + " Vs " + analy.getType2() + " Vs " + analy.getType3(),

					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(barChart);

			chartPanel.setPreferredSize(new Dimension(400, 300));

			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

			chartPanel.setBackground(Color.white);

			west.add(chartPanel);
		}

		else if (data_list.size() == 2) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for (int i = 0; i < analy.getTo() - analy.getFrom() + 1; i++) {
				dataset.setValue(data_list.get(0)[i], analy.getType1(), Integer.toString(analy.getFrom() + i));

			}

			for (int i = 0; i < analy.getTo() - analy.getFrom() + 1; i++) {
				dataset.setValue(data_list.get(1)[i], analy.getType1(), Integer.toString(analy.getFrom() + i));

			}

			CategoryPlot plot = new CategoryPlot();
			BarRenderer barrenderer1 = new BarRenderer();
			BarRenderer barrenderer2 = new BarRenderer();

			plot.setDataset(0, dataset);

			plot.setRenderer(0, barrenderer1);

			CategoryAxis domainAxis = new CategoryAxis("Year");

			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis(""));

			plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis

			JFreeChart barChart = new JFreeChart(analy.getType1() + " Vs " + analy.getType2(),

					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(barChart);

			chartPanel.setPreferredSize(new Dimension(400, 300));

			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

			chartPanel.setBackground(Color.white);

			west.add(chartPanel);
		}

		else if (data_list.size() == 1) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for (int i = 0; i < analy.getTo() - analy.getFrom() + 1; i++) {
				dataset.setValue(data_list.get(0)[i], analy.getType1(), Integer.toString(analy.getFrom() + i));

			}

			CategoryPlot plot = new CategoryPlot();
			BarRenderer barrenderer1 = new BarRenderer();
			BarRenderer barrenderer2 = new BarRenderer();

			plot.setDataset(0, dataset);

			plot.setRenderer(0, barrenderer1);

			CategoryAxis domainAxis = new CategoryAxis("Year");

			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis(""));

			plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis

			JFreeChart barChart = new JFreeChart(analy.getType1(),

					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(barChart);

			chartPanel.setPreferredSize(new Dimension(400, 300));

			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

			chartPanel.setBackground(Color.white);

			west.add(chartPanel);
		}

	}


	/**
	 * Method for calculating the analysis
	 * @throws Exception
	 */
	public void analyizeData() throws Exception {
		analy.performAnalysis();
		data_list = analy.getdata();
	}
}
