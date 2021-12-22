package httpTest;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.google.gson.JsonArray;
/**
 * Class for ViewerManager which extends JFrame and builds the viewers for MainUi
 *  @author Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 *
 */
public class ViewerManager extends JFrame{
	
	/**
	 * Method for Creating new viewer
	 * @param viewerSel String
	 * @param dataArr String[]
	 * @param array ArrayList<JsonArray>
	 * @throws Exception
	 */
	public void createNewViewer(String viewerSel, String[] dataArr, ArrayList<JsonArray> array) throws Exception
	{
		JPanel newPanel = new JPanel();
		newPanel.setLayout(new GridLayout(2, 0));

		ViewerFactory viewerFactory = new ViewerFactory();


		if (dataArr[3].equals("CO2 emissions vs Energy use vs PM2.5 air pollution")) {

			CO2VsEnergyUseVsAirPollution analysis_1 = new CO2VsEnergyUseVsAirPollution(array.get(0), array.get(1), array.get(2));

			Viewer newViewer = viewerFactory.makeViewer(viewerSel, newPanel,
					analysis_1);

		}
		
		else if (dataArr[3].equals("Average Forest area")) {

			AverageForestArea analysis_1 = new AverageForestArea(array.get(0));

			Viewer newViewer = viewerFactory.makeViewer(viewerSel, newPanel,
					analysis_1);

			try {
				analysis_1.performAnalysis();

			} catch (Exception ex) {
				// TODO Auto-generated catch block
				System.out.println("Analysis cant be preformed");
				
			}
		}
		
		else if (dataArr[3].equals("PM2.5 air pollution vs Forest Area")) {

			PollutionVsForest analysis_1 = new PollutionVsForest(array.get(0), array.get(1));

			Viewer newViewer = viewerFactory.makeViewer(viewerSel, newPanel,
					analysis_1);

			try {
				analysis_1.performAnalysis();

			} catch (Exception ex) {
				// TODO Auto-generated catch block
				System.out.println("Analysis cant be preformed");

			}
		}
		
		else if (dataArr[3].equals("Current health expenditure per capita vs Mortality rate, infant")) {

			CurrHealthvsMort analysis_1 = new CurrHealthvsMort(array.get(0), array.get(1));

			Viewer newViewer = viewerFactory.makeViewer(viewerSel, newPanel,
					analysis_1);

			try {
				analysis_1.performAnalysis();

			} catch (Exception ex) {
				// TODO Auto-generated catch block
				System.out.println("Analysis cant be preformed");

			}
		}
		
		else if (dataArr[3].equals("Ratio of Hospital Beds vs Current Health Expenditure per 1000 people")) {

			RatHospBedsCurrHealth analysis_1 = new RatHospBedsCurrHealth(array.get(0), array.get(1));

			Viewer newViewer = viewerFactory.makeViewer(viewerSel, newPanel,
					analysis_1);

			try {
				analysis_1.performAnalysis();

			} catch (Exception ex) {
				// TODO Auto-generated catch block
				System.out.println("Analysis cant be preformed");

			}
		}
		
		else if (dataArr[3].equals("Average of Government Expenditure on Education, total")) {

			AverageGovExpenseOnEdu analysis_1 = new AverageGovExpenseOnEdu(array.get(0));

			Viewer newViewer = viewerFactory.makeViewer(viewerSel, newPanel,
					analysis_1);

			try {
				analysis_1.performAnalysis();

			} catch (Exception ex) {
				// TODO Auto-generated catch block
				System.out.println("Analysis cant be preformed");

			}
		}
		
		else if (dataArr[3].equals("Ratio of Government Expenditure on Education vs Current Health Expenditure")) {

			GovEduExpenseVsHealthExpense analysis_1 = new GovEduExpenseVsHealthExpense(array.get(0), array.get(1));

			Viewer newViewer = viewerFactory.makeViewer(viewerSel, newPanel,
					analysis_1);

			try {
				analysis_1.performAnalysis();

			} catch (Exception ex) {
				// TODO Auto-generated catch block
				System.out.println("Analysis cant be preformed");

			}
		}
		
		else if (dataArr[3].equals("Ratio of CO2 emissions and GDP per capita")) {

			CO2vsGDP analysis_1 = new CO2vsGDP(array.get(0), array.get(1));

			BarViewer newViewer = (BarViewer) viewerFactory.makeViewer(viewerSel, newPanel,
					analysis_1);

			try {
				analysis_1.performAnalysis();

			} catch (Exception ex) {
				// TODO Auto-generated catch block
				System.out.println("Analysis cant be preformed");

			}
		}
		getContentPane().add(newPanel, BorderLayout.WEST);
	}

	
}
