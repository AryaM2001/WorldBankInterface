package httpTest;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Class for viewer Pie method
 * @author  Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 *
 */
public class PieViewer implements Viewer{
	public JPanel west;

	public Analysis analy;
	
	public ArrayList<Double[]> data_list = new ArrayList<Double[]>();
	
	/**
	 * 	Constructor for Pie Viewer which creates the pie Viewer for panel
	 * @param _west JPanel for the JFrame
	 * @param _analy Analysis object 
	 */
	public PieViewer(JPanel _west, Analysis _analy) {
		west = _west;

		analy = _analy;
		// Different way to create pie chart

		/*

		 * var dataset = new DefaultPieDataset(); dataset.setValue("Unemployed", 3.837);

		 * dataset.setValue("Employed", 96.163);

		 * 

		 * JFreeChart pieChart = ChartFactory.createPieChart("Women's Unemployment",

		 * dataset, true, true, false);

		 */



		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(3.946, "Unemployed", "Men");

		dataset.addValue(96.054, "Employed", "Men");

		dataset.addValue(3.837, "Unemployed", "Women");

		dataset.addValue(96.163, "Employed", "Women");



		JFreeChart pieChart = ChartFactory.createMultiplePieChart("Unemployment: Men vs Women", dataset,

				TableOrder.BY_COLUMN, true, true, false);



		ChartPanel chartPanel = new ChartPanel(pieChart);

		chartPanel.setPreferredSize(new Dimension(400, 300));

		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		chartPanel.setBackground(Color.white);

		west.add(chartPanel);

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
