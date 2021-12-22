package httpTest;

import java.util.*;

import javax.swing.JFrame;

import com.google.gson.JsonArray;

/**
 * Class of Session. 
 * @author Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 *
 */

public class Session {

	GetData data;

	JsonArray datArray;

	AverageForestArea forest;

	AverageGovExpenseOnEdu education;

	CurrHealthvsMort eduVsHealth;

	Scanner sc = new Scanner(System.in);

	String country;

	String from;

	String to;

	String analysisType;

	loginProxy lp; 
	
	/**
	 * Constructor of Session which builds from login
	 * @param condition  int
	 */
	public Session(int condition){
		if(condition == 0) {
			lp = new loginProxy();
	 		lp.initializer();
		}
	}
/**
 * Method to create new viewer for analysis
 * @param viewerSel String
 * @param dataArr String[]
 * @throws Exception 
 */
	public void createNewViewer(String viewerSel, String[] dataArr) throws Exception {
		ViewerManager vm = new ViewerManager();
		ArrayList<JsonArray> array = GetJsonArray(dataArr);
		vm.createNewViewer(viewerSel, dataArr, array);
			
				
	}
	/**
	 * Returns JSon Array of data
	 * @param datalist String[]
	 * @return ArrayList<JsonArray> Jarr
	 */
	public ArrayList<JsonArray> GetJsonArray(String[] datalist) {
		ArrayList<JsonArray> Jarr = new ArrayList<JsonArray>();

		data = new GetData(datalist);
		Jarr = data.getJson();

		return Jarr;

	}
} 