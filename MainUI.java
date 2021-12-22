 package httpTest;

 
import java.awt.BasicStroke;

import java.awt.BorderLayout;

import java.awt.Color;

import java.awt.Dimension;

import java.awt.Font;

import java.awt.GridLayout;

import java.util.Vector;

import java.util.ArrayList;

import javax.swing.BorderFactory;

import javax.swing.JButton;

import javax.swing.JComboBox;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;

import org.jfree.chart.ChartPanel;

import org.jfree.chart.JFreeChart;

import org.jfree.chart.axis.CategoryAxis;

import org.jfree.chart.axis.DateAxis;

import org.jfree.chart.axis.NumberAxis;

import org.jfree.chart.block.BlockBorder;

import org.jfree.chart.plot.CategoryPlot;

import org.jfree.chart.plot.PlotOrientation;

import org.jfree.chart.plot.XYPlot;

import org.jfree.chart.renderer.category.BarRenderer;

import org.jfree.chart.renderer.xy.XYItemRenderer;

import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import org.jfree.chart.renderer.xy.XYSplineRenderer;

import org.jfree.chart.title.TextTitle;

import org.jfree.chart.util.TableOrder;

import org.jfree.data.category.DefaultCategoryDataset;

import org.jfree.data.time.TimeSeries;

import org.jfree.data.time.TimeSeriesCollection;

import org.jfree.data.time.Year;

import org.jfree.data.xy.XYSeries;

import org.jfree.data.xy.XYSeriesCollection;
/**
 * 
 * @author bunbu
 *
 */
public class MainUI extends JFrame {


	

	private static final long serialVersionUID = 1L;

	private Selection selChoices;
	private ViewerFactory viewerFact = new ViewerFactory();
	public 				JPanel west = new JPanel();

	private static MainUI instance;

	public static MainUI getInstance() {

		if (instance == null)

			instance = new MainUI();

		return instance;

	}

	public MainUI() {
		// Set window title

				super("Country Statistics");

				// Where selection class call goes

				selChoices = new Selection();

				ArrayList<JComboBox<String>> options = new ArrayList<JComboBox<String>>();

				options = selChoices.getChoices();

				// Create labels

				JLabel chooseCountryLabel = new JLabel("Choose a country: ");

				JLabel from = new JLabel("From: ");

				JLabel to = new JLabel("To: ");

				// Create Top Bar

				JPanel north = new JPanel();

				north.add(chooseCountryLabel);

				north.add(options.get(1));

				north.add(from);

				north.add(options.get(2));

				north.add(to);

				north.add(options.get(3));

				// Bottom Bar

				JButton addView = new JButton("+");

				selChoices.addView(addView);

				JButton removeView = new JButton("-");

				selChoices.removeView(removeView);

				JButton recalculate = new JButton("Recalculate");

				selChoices.recalculation(recalculate);

				JLabel methodLabel = new JLabel("        Choose an analysis method: ");

				JLabel viewsLabel = new JLabel("Available Views: ");

				JPanel south = new JPanel();

				south.add(viewsLabel);

				south.add(options.get(4));

				south.add(addView);

				south.add(removeView);

				south.add(methodLabel);

				south.add(options.get(0));

				south.add(recalculate);

				JPanel east = new JPanel();

				// Set charts region

				west = new JPanel();

				west.setLayout(new GridLayout(2, 0));

				//createCharts(west);

				getContentPane().add(north, BorderLayout.NORTH);

				getContentPane().add(east, BorderLayout.EAST);

				getContentPane().add(south, BorderLayout.SOUTH);


	}

	public JPanel getWestPanel() {
		return west;
	}



}