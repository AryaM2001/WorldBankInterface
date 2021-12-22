package httpTest;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
* Class for viewer Line Graph
 * @author Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 *
 */
public class LineViewer implements Viewer{
	public JPanel west;

	public Analysis analy;
	
	public int from;
	
	public ArrayList<Double[]> data_list = new ArrayList<Double[]>();
	
	/**
	 * Constructor for LineViewer which creates the Line Viewer for panel
	 * @param _west
	 * @param _analy
	 * @throws Exception
	 */
	public LineViewer(JPanel _west, Analysis _analy) throws Exception {
		XYSeriesCollection dataset = new XYSeriesCollection();
		String graphName = "";
		west = _west;

		analy = _analy;
		
		analyizeData();
		int counter = 0;
		from = analy.getFrom();
		if(data_list.size() >= 1) {
			XYSeries series1 = new XYSeries(analy.getType1());
			graphName = analy.getType1();
			System.out.println(graphName);
			for(int x = from; x < from + data_list.get(0).length; x++) {
				//System.out.println("series1.add(" + x + ", " + data_list.get(0)[counter] + ");");
				series1.add(x, data_list.get(0)[counter]);
				counter ++;
			}
			counter = 0;
			dataset.addSeries(series1);
		}
		if(data_list.size() >= 2) {
			graphName = analy.getType1().concat(" vs ").concat(analy.getType2());
			System.out.println(graphName);
			XYSeries series2 = new XYSeries(analy.getType2());

			for(int x = from; x < from + data_list.get(1).length; x++) {
				//System.out.println("series2.add(" + x + ", " + data_list.get(1)[counter] + ");");
				series2.add(x, data_list.get(1)[counter]);
				counter ++;
			}
			counter = 0;
			dataset.addSeries(series2);
		}
		if(data_list.size() == 3) {
			graphName = analy.getType1().concat(" vs ").concat(analy.getType2().concat(" vs ").concat(analy.getType3()));
			System.out.println(graphName);
			XYSeries series3 = new XYSeries(analy.getType3());

			for(int x = from; x < from + data_list.get(0).length; x++) {
				//System.out.println("series3.add(" + x + ", " + data_list.get(2)[counter] + ");");
				series3.add(x, data_list.get(2)[counter]);
				counter ++;
			}

			dataset.addSeries(series3);
		}



		JFreeChart chart = ChartFactory.createXYLineChart(graphName, "Year", "", dataset,

				PlotOrientation.VERTICAL, true, true, false);



		XYPlot plot = chart.getXYPlot();



		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

		renderer.setSeriesPaint(0, Color.RED);

		renderer.setSeriesStroke(0, new BasicStroke(2.0f));



		plot.setRenderer(renderer);

		plot.setBackgroundPaint(Color.white);



		plot.setRangeGridlinesVisible(true);

		plot.setRangeGridlinePaint(Color.BLACK);



		plot.setDomainGridlinesVisible(true);

		plot.setDomainGridlinePaint(Color.BLACK);



		chart.getLegend().setFrame(BlockBorder.NONE);



		chart.setTitle(

				new TextTitle(graphName, new Font("Serif", java.awt.Font.BOLD, 18)));



		ChartPanel chartPanel = new ChartPanel(chart);

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
