package httpTest;



import java.util.ArrayList;

import com.google.gson.JsonArray;

/**
 * Class for calculating the analysis of the method ' Current health expenditure vs Mortality Rate'
 * @author Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 *
 */
public class CurrHealthvsMort implements Analysis{

	public JsonArray mortJson;

	public JsonArray healthJson;

	public Double[] mortality;

	public Double[] health;

	public String country;

	public int from;

	public int to;
	
	public ArrayList<Double[]> data_list = new ArrayList<Double[]>();


	

	/**
	 * Constructor of CurrHealthvsMort,  receives two JSon Arrays for which to analyze
	 * @param mort JsonArray of Selected mortality data for selected years
	 * @param heal JsonArray of Selected health expenditure data for selected years
	 */

	public CurrHealthvsMort(JsonArray mort, JsonArray heal) {

		mortJson = mort;

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

	 * The method processes using the two JSon Arrays. This entails calculating required variables and identifying required values for the viewer to create its visualizations

	 * the CurrHealthvsMort analysis type requires no calculations. Only for the following values to be identified:

	 * - Current health expenditure per capita (current US$) for each specified year -> currHealth

	 * - Mortality rate, infant for each specified year -> currMort

	 * - country for which the data is on            -> country

	 * - start date from which data is pulled        -> from

	 * - end date from which data is pulled          -> to
	 * @throws Exception

	 */

	public void performAnalysis() throws Exception {

		int year;																											//variable to track the current year																											

		double currMort = 0;	

		double currHealth = 0;																				



		

		int sizeOfResults = mortJson.get(1).getAsJsonArray().size();

		mortality = new Double[sizeOfResults];

		health = new Double[sizeOfResults];

		country = mortJson.get(1).getAsJsonArray().get(0).getAsJsonObject()

				.get("country").getAsJsonObject().get("value").getAsString();

		to = mortJson.get(1).getAsJsonArray().get(0).getAsJsonObject()

				.get("date").getAsInt();

		from = mortJson.get(1).getAsJsonArray().get(sizeOfResults - 1)

				.getAsJsonObject().get("date").getAsInt();

		

		for (int i = 0; i < sizeOfResults; i++) {

			year = mortJson.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();

			if (mortJson.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull() || healthJson.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull()) {

				throw new Exception("no data for year: " + year);

			}

			else {

				currMort = mortJson.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").getAsDouble();

				currHealth = healthJson.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").getAsDouble();

			}

			mortality[i] = currMort;

			health[i] = currHealth;

			

			System.out.println("Current health expenditure per capita (current US$) vs Mortality rate, infant (per 1,000 live births) in " + year + " is: " + currHealth + " vs " + currMort);

		}
		data_list.add(mortality);
		
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
