package httpTest;

import javax.swing.JPanel;

import com.google.gson.JsonArray;
/**
 * Class for implementing viewers
 * @author Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 *
 */
public class ViewerFactory {
	/**
	 * Method for making viewers in list selected
	 * @param newViewerType String
	 * @param west JPanel
	 * @param _analy Analysis
	 * @return Viewer
	 * @throws Exception
	 */
	public Viewer makeViewer(String newViewerType, JPanel west, Analysis _analy) throws Exception {

		if(newViewerType.equals("Time Series Chart")) {
			return new TimeSeriesViewer(west, _analy);
		}
		else if(newViewerType.equals("Line Chart")) {
			return new LineViewer(west, _analy);
		}
		else if(newViewerType.equals("Bar Chart")) {
			return new BarViewer(west, _analy);
		}
		else if(newViewerType.equals("Pie Chart")) {
			return new PieViewer(west, _analy);
		}
		else if(newViewerType.equals("Report")) {
			return new ReportViewer(west, _analy);
		}
		else {
			return null;
		}
	}
	/**
	 * Method for making viewers in list selected
	 * @param newViewerType String
	 * @param west JPanel
	 * @param _analy Analysis
	 * @param selChoices
	 * @return Viewer
	 * @throws Exception
	 */
	public Viewer makeViewer(String newViewerType, JPanel west, Analysis _analy, Selection selChoices) {		
		if(newViewerType.equals("Scatter Chart")) {
			return new ScatterViewer(west, _analy, selChoices);
		}
		else {
			return null;
		}
	}
}
