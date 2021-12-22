package httpTest;



import com.google.gson.JsonArray;

import java.lang.Math;
import java.util.ArrayList;



public class RatHospBedsCurrHealth implements Analysis{

	public JsonArray hospBedsJson;

	public JsonArray healthJson;

	public Double[] hospitalBeds;

	public Double[] health;

	public String country;

	public int from;

	public int to;
	
	public ArrayList<Double[]> data_list = new ArrayList<Double[]>();


		
	public int getFrom() {
		return from;
	}
	//constructor of the class. the CurrHealthvsMort class receives two json objects for which to analyze

	public RatHospBedsCurrHealth(JsonArray hospBeds, JsonArray heal) {

		hospBedsJson = hospBeds;

		healthJson = heal;

	}

		

	/*

	 * Processes the two json objects. This entails calculating required variables and identifying required values

	 * for the viewer to create its visualizations

	 * the AverageForestArea analysis type requires no calculations. Only for the following values to be identified:

	 * - Current health expenditure per capita (current US$) for each specified year -> currHealth

	 * - Mortality rate, infant for each specified year

	 * - country for which the data is on            -> country

	 * - start date from which data is pulled        -> from

	 * - end date from which data is pulled          -> to

	 */

	public void performAnalysis() throws Exception {

		int year;																											//variable to track the current year																											

		double currBeds = 0;	

		double currHealth = 0;

		double currHealthtemp = 0;

		double ratHealth = 0;

		double ratBeds = 0;

		

		int sizeOfResults = hospBedsJson.get(1).getAsJsonArray().size();

		hospitalBeds = new Double[sizeOfResults];

		health = new Double[sizeOfResults];

		country = hospBedsJson.get(1).getAsJsonArray().get(0).getAsJsonObject().get("country").getAsJsonObject().get("value").getAsString();

		to = hospBedsJson.get(1).getAsJsonArray().get(0).getAsJsonObject().get("date").getAsInt();

		from = hospBedsJson.get(1).getAsJsonArray().get(sizeOfResults - 1).getAsJsonObject().get("date").getAsInt();

			

		for (int i = 0; i < sizeOfResults; i++) {

			year = hospBedsJson.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();

			if (hospBedsJson.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull() || healthJson.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull()) {

				throw new Exception("no data for year: " + year);

			}

			else {

				currBeds = hospBedsJson.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").getAsDouble();

				currHealthtemp = healthJson.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").getAsDouble();

				currHealth = currHealthtemp * 1000;

			}

			hospitalBeds[i] = currBeds;

			health[i] = currHealth;

			System.out.println("Health:"+ currHealth);

			System.out.println("Beds:"+ currBeds);



			if(currBeds != 0) {

				ratHealth = currHealth/currBeds;

				ratBeds = currBeds/currBeds;

				System.out.println("Ratio of Hospital beds (per 1,000 people) and Current health expenditure (per 1,000 people) in " + year + " is: " + Math.round(ratBeds) + "vs" + Math.round(ratHealth));

			}else if(currHealth != 0) {

				ratHealth = currHealth/currHealth;

				ratBeds = currBeds/currHealth;

				System.out.println("Ratio of Hospital beds (per 1,000 people) and Current health expenditure (per 1,000 people) in " + year + " is: " + Math.round(ratBeds) + "vs" + Math.round(ratHealth));

			}else {

				System.out.println("Ratio of Hospital beds (per 1,000 people) and Current health expenditure (per 1,000 people) in " + year + " is: " + Math.round(currBeds) + "vs" + Math.round(currHealth));

			}

		}
		data_list.add(hospitalBeds);
		
		data_list.add(health);

	}



	public ArrayList<Double[]> getdata() {
		// TODO Auto-generated method stub
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
