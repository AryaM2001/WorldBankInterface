package httpTest;

import java.util.ArrayList;

import com.google.gson.JsonArray;
/**
 * Method for the analysis type of 'Average Forest Area'.  
 * @author Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 *
 */
public class AverageForestArea implements Analysis{
	
	public static JsonArray jsonArray;
	public Double[] area;
	public String country;
	public int from;
	public int to;
	public ArrayList<Double[]> data_list = new ArrayList<Double[]>();

	
	/**
	 * constructor of the class. the AverageForestArea class receives a JSon Array for which to analyze
	 * @param jsonArr JSonArray of user selected data
	 */
	public AverageForestArea(JsonArray jsonArr) {
		jsonArray = jsonArr;
	}
	
	/**
	 * Method that is used for retrieving user data from their selections for the analysis classes.
	 * @return ArrayList<Double[]> user data 
	 */
	public ArrayList<Double[]> getdata() {
		return data_list;
	
	}
	
	/**
	 * Processes the JSon Array. This entails calculating required variables and identifying required values
	 * for the viewer to create its visualizations
	 * the AverageForestArea analysis type requires no calculations. Only for the following values to be identified:
	 * - average forest area for each specified year -> area[]
	 * - country for which the data is on            -> country
	 * - start date from which data is pulled        -> from
	 * - end date from which data is pulled          -> to
	 * @throws Exception
	 */
	public void performAnalysis() throws Exception {
		
		int year;																											//variable to track the current year																											
		double averageForestArea = 0;																						//variable to track the current average forest area																						
		double cummulativeForestArea = 0;
		int sizeOfResults = jsonArray.get(1).getAsJsonArray().size();
		area = new Double[sizeOfResults];
		country = jsonArray.get(1).getAsJsonArray().get(0).getAsJsonObject()
				.get("country").getAsJsonObject().get("value").getAsString();
		to = jsonArray.get(1).getAsJsonArray().get(0).getAsJsonObject()
				.get("date").getAsInt();
		from = jsonArray.get(1).getAsJsonArray().get(sizeOfResults - 1)
				.getAsJsonObject().get("date").getAsInt();
		
		for (int i = 0; i < sizeOfResults; i++) {
			year = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();
			if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull()) {
				throw new Exception("no data for year: " + year);
			}
			else {
				averageForestArea = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
						.getAsDouble();
			}
			area[i] = averageForestArea;
				

			System.out.println("Average forest area for : " + year + " is " + averageForestArea);
			cummulativeForestArea = cummulativeForestArea + averageForestArea;
		}
		System.out.println(
				"The average average forest area over the selected years is " + cummulativeForestArea / sizeOfResults);
		data_list.add(area);
	}

	/**
	 * Method that is used for retrieving the from date from the user selection for the analysis classes.
	 * @return int from date
	 */
	public int getFrom() {
		return from;
	}
	public String getType1() {
		// TODO Auto-generated method stub
		return "Average Forest Area";
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
