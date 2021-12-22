package httpTest;

import java.util.ArrayList;

import com.google.gson.JsonArray;

/** Class for calculating the analysis of the method ' Government Education Expenses Vs Health Expenses'
* @author Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
*/

public class GovEduExpenseVsHealthExpense implements Analysis{
	
	public JsonArray eduJson;
	public JsonArray healthJson;
	public Double[] education;
	public Double[] health;
	public String country;
	public int from;
	public int to;
	public ArrayList<Double[]> data_list = new ArrayList<Double[]>();

	
	/**
	 * Constructor of the class. the GovEduExpenseVsHealthExpense class receives a JSon Array for which to analyze
	 * @param edu JsonArray of education data 
	 * @param heal JsonArray of education data 
	 */
	public GovEduExpenseVsHealthExpense(JsonArray edu, JsonArray heal) {
		eduJson = edu;
		healthJson = heal;
	}
	
	/**
	 * Method that is used for retrieving user data from their selections for the analysis classes.
	 * @return int 'from' year
	 */
	public int getFrom() {
		return from;
	}

	/**
	 *  Method that is used for retrieving user data from their selections for the analysis classes.
	 *
	 * @return ArrayList<Double[]> user data
	 */
	public ArrayList<Double[]> getdata() {
	
		return data_list;
	}

	/**
	 * Method processes the JSon Array. This entails calculating required variables and identifying required values
	 * for the viewer to create its visualizations
	 * the GovEduExpenseVsHealthExpense analysis type requires the following values to be identified:
	 * - average Education -> averageEdu
	 * - average Health -> averageHealth
	 * - country for which the data is on            -> country
	 * - start date from which data is pulled        -> from
	 * - end date from which data is pulled          -> to
	 */
	public void performAnalysis() throws Exception {
		
		int year;																											//variable to track the current year																											
		double averageEdu = 0;	
		double averageHealth = 0;																				
		double cummulativeEdu = 0;
		double cummulativeHealth = 0;
		int sizeOfResults = eduJson.get(1).getAsJsonArray().size();
		education = new Double[sizeOfResults];
		health = new Double[sizeOfResults];
		country = eduJson.get(1).getAsJsonArray().get(0).getAsJsonObject()
				.get("country").getAsJsonObject().get("value").getAsString();
		to = eduJson.get(1).getAsJsonArray().get(0).getAsJsonObject()
				.get("date").getAsInt();
		from = eduJson.get(1).getAsJsonArray().get(sizeOfResults - 1)
				.getAsJsonObject().get("date").getAsInt();
		
		for (int i = 0; i < sizeOfResults; i++) {
			year = eduJson.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();
			if (eduJson.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull() ||
					healthJson.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull()) {
				throw new Exception("no data for year: " + year);
			}
			else {
				averageEdu = eduJson.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
						.getAsDouble();
				averageHealth = healthJson.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
						.getAsDouble();
			}
			education[i] = averageEdu;
			health[i] = averageHealth;
				

			System.out.println("ratio of government spending on education to health for : " + year + " is " + averageEdu + ":" + averageHealth);
			cummulativeEdu = cummulativeEdu + averageEdu;
			cummulativeHealth = cummulativeHealth + averageHealth;
		}
		System.out.println(
				"The average ratio of government spending on education to health for selected years is " + cummulativeEdu / sizeOfResults + ":" + cummulativeHealth / sizeOfResults);
		data_list.add(education);
		data_list.add(health);
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
