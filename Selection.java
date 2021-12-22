package httpTest;

import java.awt.BasicStroke;

import java.awt.BorderLayout;

import java.awt.Color;

import java.awt.Dimension;

import java.awt.Font;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.ComponentEvent;

import java.awt.event.ComponentListener;

import java.awt.event.ItemEvent;

import java.awt.event.ItemListener;

import java.util.Vector;

import java.util.ArrayList;

import javax.swing.Action;

import javax.swing.BorderFactory;

import javax.swing.JButton;

import javax.swing.JComboBox;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTextArea;

import static javax.swing.JOptionPane.showMessageDialog;

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

import com.google.gson.JsonArray;
/**
 * Class of Selection. 
 * @author Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 *
 */
public class Selection implements ActionListener, ItemListener {

	ArrayList<JComboBox<String>> options;

	ArrayList<JButton> buttons;

	String countrySel;

	String fromSel;

	String toSel;

	String viewSel;
	
	String viewerSel;

	ArrayList<String> viewSelist;

	String addButt;

	String remButt;

	String analySel;

	ViewerFactory viewerFactory = new ViewerFactory();

	ObserverManager obManager;

	JComboBox<String> methodsList;

	JComboBox<String> countriesList;

	JComboBox<String> fromList;

	JComboBox<String> toList;
	
	JComboBox<String> viewsList;
	//String[] dataArr = new String[4];
	
	JButton recal;

	/**
	 * Constructor of Selection Class. Creates the Comboboxes for the panels and gets data to pass through other classes
	 */
	public Selection() {

		options = new ArrayList<JComboBox<String>>();

		buttons = new ArrayList<JButton>();

		countrySel = null;

		fromSel = null;

		toSel = null;

		viewSel = null;

		viewSelist = new ArrayList<String>();

		analySel = null;

		obManager = ObserverManager.getInstance();

	}
/**
 * Method of getting the available choices for the user
 * @return ArrayList<JComboBox<String>> of data
 */
	public ArrayList<JComboBox<String>> getChoices() {
		while (true) {

			// Add Analysis options

			Vector<String> methodsNames = new Vector<String>();

			methodsNames.add("CO2 emissions vs Energy use vs PM2.5 air pollution");

			methodsNames.add("Average Forest area");

			methodsNames.add("PM2.5 air pollution vs Forest Area");

			methodsNames.add("Current health expenditure per capita vs Mortality rate, infant");

			methodsNames.add("Ratio of Hospital Beds vs Current Health Expenditure per 1000 people");

			methodsNames.add("Average of Government Expenditure on Education, total");

			methodsNames.add("Ratio of Government Expenditure on Education vs Current Health Expenditure");

			methodsNames.add("Ratio of CO2 emissions and GDP per capita");

			methodsList = new JComboBox<String>(methodsNames);
			methodsList.addActionListener(this);

			analySel = (String) methodsList.getSelectedItem();

			options.add(methodsList);

			// Country Choices

			CheckCountry countryCheck = new CheckCountry();
			Vector<String> countriesNames = countryCheck.getValidCountry(analySel);
			if (countriesNames.isEmpty()) {
				showMessageDialog(null, "There are no available countries for viewer chosen.");
				continue; // Jump to beginning
			} else {
				countriesList = new JComboBox<String>(countriesNames);
				countriesList.addActionListener(this);

				countrySel = (String) countriesList.getSelectedItem();
				options.add(countriesList);
			}

			// Date Choices

			Vector<String> years = new Vector<String>();
			for (int i = 2021; i >= 2010; i--) {

				years.add("" + i);

			}

			fromList = new JComboBox<String>(years);
			fromList.addActionListener(this);

			toList = new JComboBox<String>(years);
			toList.addActionListener(this);

			options.add(fromList);
			options.add(toList);

			while (true) {
				fromSel = (String) fromList.getSelectedItem();

				toSel = (String) toList.getSelectedItem();
	
				// Viewer Choices

				Vector<String> viewsNames = new Vector<String>();

				 viewsNames.add("Pie Chart");
				 
				 viewsNames.add("Time Series Chart");

				 viewsNames.add("Line Chart");

				 viewsNames.add("Bar Chart");

				 viewsNames.add("Scatter Chart");

				viewsNames.add("Report");

				viewsList = new JComboBox<String>(viewsNames);
				viewsList.addActionListener(this);

				options.add(viewsList);

				return options;

			}
		}
	}


/**
 * Method that Adds Viewers selected
 * @param adds Jbutton 
 */
	public void addView(JButton adds) {

		adds.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {

				viewSelist.add(viewSel);

			}

		});

	}
	
	/**
	 * Method that removes Viewers selected
	 * @param rems Jbutton 
	 */
	public void removeView(JButton rems) {

		rems.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {

				if (viewSelist.contains(viewSel)) {

					viewSelist.remove(viewSel);

				} else {

					showMessageDialog(null, "This Viewer was not previously chosen.");

				}

			}

		});

	}

/**
 * Method for the recalculation button 
 * @param recalculate Jbutton 
 */
	public void recalculation(JButton recalculate) {

		recal = recalculate;
		recal.addActionListener(this);

	}
	/**
	 * Method for the get country  
	 * @return String countrySel
	 */
	public String getCountry() {

		return countrySel;

	}
	/**
	 * Method for the get analysis  
	 * @return String analSel
	 */
	public String getAnalysis() {

		return analySel;

	}

	/**
	 * Method for the get 'from'  
	 * @return String fromSel
	 */
	public String getFrom() {

		return fromSel;

	}
	/**
	 * Method for the get 'to'  
	 * @return String toSel
	 */
	public String getTo() {

		return toSel;

	}
	/**
	 * Method for the get viewer  
	 * @return String viewerSel
	 */
	public String getViewer()
	{
		return viewerSel;
	}
	/**
	 * Method for the get viewer list  
	 * @return ArrayList<String> viewSelist
	 */
	public ArrayList<String> getViewers() {

		return viewSelist;

	}

	public void componentResized(ComponentEvent e) {

		// TODO Auto-generated method stub

	}

	public void componentMoved(ComponentEvent e) {

		// TODO Auto-generated method stub

	}

	public void componentShown(ComponentEvent e) {

		// TODO Auto-generated method stub

	}

	public void componentHidden(ComponentEvent e) {

		// TODO Auto-generated method stub

	}

	public void itemStateChanged(ItemEvent e) {

		// TODO Auto-generated method stub

	}
/**
 * Method for when the buttons are interacted with by the user
 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == countriesList) {
			JComboBox comboBox = (JComboBox) e.getSource();
			countrySel = (String) comboBox.getSelectedItem();
		}

		else if (e.getSource() == fromList) {
			JComboBox comboBox = (JComboBox) e.getSource();
			fromSel = (String) comboBox.getSelectedItem();
			
		}

		else if (e.getSource() == toList) {
			JComboBox comboBox = (JComboBox) e.getSource();
			toSel = (String) comboBox.getSelectedItem();
			CheckCountry countryCheck = new CheckCountry();
			Boolean test = countryCheck.getValidYears(analySel, countrySel, fromSel, toSel);
			if(!test) {
				showMessageDialog(null, "This Viewer was not previously chosen.");
			}
		}
		
		else if (e.getSource() == viewsList) {
			JComboBox comboBox = (JComboBox) e.getSource();
			viewerSel = (String) comboBox.getSelectedItem();
		}

		else if (e.getSource() == methodsList) {
			JComboBox comboBox = (JComboBox) e.getSource();

			analySel = (String) comboBox.getSelectedItem();
		}

		else if (e.getSource() == recal) {
			String[] dataArr = { countrySel, fromSel, toSel, analySel };
			try {
				JPanel west = new JPanel();
				west.setLayout(new GridLayout(2, 0));
				obManager.notifyObserver(viewerSel, dataArr);
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

	}
}
