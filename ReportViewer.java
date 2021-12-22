package httpTest;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 * Class for viewer Report Viewer
 * @author Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 *
 */
public class ReportViewer implements Viewer{
	public JPanel west;

	public Analysis analy;
	
	public ArrayList<Double[]> data_list = new ArrayList<Double[]>();
	
	/**
	 * Constructor of Report Viewer. 
	 * @param _west JPanel
	 * @param _analy Analysis
	 * @throws Exception
	 */
	public ReportViewer(JPanel _west, Analysis _analy) throws Exception {
		west = _west;

		analy = _analy;
		analy.performAnalysis();
		data_list = analy.getdata();

		
		String analysis, reportMessage, reportMessage2;

		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);

		

		analysis =  "\n ============================== \n" ;



		reportMessage = "";



		reportMessage2 = "";



		report.setText(reportMessage);
		JScrollPane outputScrollPane = new JScrollPane(report);
		west.add(outputScrollPane);

	}
	
	/**
	 * Method for calculating the analysis
	 * @throws Exception
	 */
	public void analyizeData() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
