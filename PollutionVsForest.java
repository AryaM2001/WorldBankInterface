package httpTest;

import java.util.ArrayList;

import com.google.gson.JsonArray;

/**
 * Class for calculating the analysis of the method 'Pollution Vs Forest'
 * @author Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 *
 */
public class PollutionVsForest implements Analysis{
	
	private JsonArray jsonArray;
	private JsonArray jsonArray2;
	private Double[] forestarea;
	private Double[] polutionExposure;
	private String country;
	private int from;
	private int to;
	public ArrayList<Double[]> data_list = new ArrayList<Double[]>();

	/**
	 *  Constructor of PollutionVsForest. Receives a JSon Array for pollution, and Forest area data, of which to analyze
	 * @param pollution JsonArray
	 * @param forest JsonArray
	 */
	public PollutionVsForest (JsonArray pollution, JsonArray forest ) {
		jsonArray = pollution;
		jsonArray2 = forest;
	}
	/**
	 * Method that is used for retrieving user data from their selections for the analysis classes.
	 * @return int 'from' year
	 */
	public int getFrom() {
		return from;
	}
	
	/**
	 * Processes the JSon Array, calculating required variables and identifying required values
	 * @return Double[] of pollution values
	 * @throws Exception
	 */
	public Double[] getPollution() throws Exception {
		int year;																											//variable to track the current year																											
		double averagPollution = 0;																						//variable to track the current average forest area																						
		int sizeOfResults = jsonArray.get(1).getAsJsonArray().size(); 
		polutionExposure = new Double[sizeOfResults];
		country = jsonArray.get(1).getAsJsonArray().get(0).getAsJsonObject()
				.get("country").getAsJsonObject().get("value").getAsString();
		to = jsonArray.get(1).getAsJsonArray().get(0).getAsJsonObject()
				.get("date").getAsInt();
		from = jsonArray.get(1).getAsJsonArray().get(sizeOfResults - 1)
				.getAsJsonObject().get("date").getAsInt();
		
		for (int i = 0; i < sizeOfResults; i++) {
			year = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();
			if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull()) {
				averagPollution = 0;
			}
			else {
				averagPollution = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
						.getAsDouble();
			}
			polutionExposure[i] = averagPollution;
		}
		return polutionExposure;
		
		
	}
	
	/**
	 * Processes the JSon Array, calculating required variables and identifying required values
	 * @return Double[] of forest values
	 * @throws Exception
	 */
	public Double[] getForest() throws Exception {
		
		int year;																											//variable to track the current year																											
		double averageForestArea = 0;																						//variable to track the current average forest area																						
		double cummulativeForestArea = 0;
		int sizeOfResults = jsonArray2.get(1).getAsJsonArray().size();
		forestarea = new Double[sizeOfResults];
		country = jsonArray2.get(1).getAsJsonArray().get(0).getAsJsonObject()
				.get("country").getAsJsonObject().get("value").getAsString();
		to = jsonArray2.get(1).getAsJsonArray().get(0).getAsJsonObject()
				.get("date").getAsInt();
		from = jsonArray2.get(1).getAsJsonArray().get(sizeOfResults - 1)
				.getAsJsonObject().get("date").getAsInt();
		
		for (int i = 0; i < sizeOfResults; i++) {
			year = jsonArray2.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();
			if (jsonArray2.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull()) {
				throw new Exception("no data for year: " + year);
			}
			else {
				averageForestArea = jsonArray2.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
						.getAsDouble();
			}
			forestarea[i] = averageForestArea;
		}
		return forestarea;
	}
	

	/**
	 * Processes the Double[] data, calculating required variables and identifying required values
	 * for the viewer to create its visualizations.
	 * - Double[] pollutionData
	 * - Double[] forestData
	 */
	public void performAnalysis() throws Exception {
		Double[] pollutionData = getPollution();
		Double[] forestData = getForest();

		for (int i = 0; i < forestData.length; i++) {
			
			if (pollutionData[i] == 0) {
				System.out.println("Analysis not avaliable for " + ""+ (from + i));
			}
			else {
				System.out.println("In " + (from + i) + "the PM2.5 air pollution, mean annual exposure is " + pollutionData[i] + "and the forest area is "+ forestData[i]);
			}		
		}	
		data_list.add(polutionExposure);
		data_list.add(forestarea);
	}
	
	/**
	 *  Method that is used for retrieving user data from their selections for the analysis classes.
	 *
	 * @return ArrayList<Double[]> user data
	 */
	public ArrayList<Double[]> getdata() {
		return data_list;
	}
	public String getType1() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getType2() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getType3() {
		// TODO Auto-generated method stub
		return null;
	}
	public int getTo() {
		// TODO Auto-generated method stub
		return 0;
	}


}
