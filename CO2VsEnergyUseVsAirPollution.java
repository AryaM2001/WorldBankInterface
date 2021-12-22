package httpTest;


import java.util.ArrayList;

import com.google.gson.JsonArray;
/**
 * Class for calculating the analysis of the method ' CO2 Vs Energy Use Vs Air Pollution'
 * @author Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 *
 */
public class CO2VsEnergyUseVsAirPollution implements Analysis {

	public JsonArray CO2Json;
	public JsonArray EnergyJson;
	public JsonArray AirPollutionJson;

	public Double[] CO2;
	public Double[] energy;
	public Double[] pollution;
	
	public ArrayList<Double[]> data_list = new ArrayList<Double[]>();


	public String country;
	public int from;
	public int to;
	
/**
 * Constructor of CO2VsEnergyUseVsAirPollution. Receives a JSon Array for CO2 data, Energy Use data, and Air Pollution data, of which to analyze
 * @param Co2 JsonArray of Selected CO2 data for selected years
 * @param energyUse JsonArray of Selected Energy Use data for selected years
 * @param airPollution JsonArray of Selected Air Pollution data for selected years
 */
	public CO2VsEnergyUseVsAirPollution(JsonArray Co2, JsonArray energyUse, JsonArray airPollution) {
		CO2Json = Co2;
		EnergyJson = energyUse;
		AirPollutionJson = airPollution;
		
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
	 * Processes the JSon Array, calculating required variables and identifying required values
	 * for the viewer to create its visualizations.
	 * The CO2VsEnergyUseVsAirPollution analysis type requires no calculations. Only for the following values to be identified:
	 * -  CO2 emissions (metric tons per capita) -> CO2[]
	 * - Energy use (kg of oil equivalent per capita)-> energy[]
	 * - PM2.5 air pollution, mean annual exposure-> pollution[]
	 * - country for which the data is on            -> country
	 * - start date from which data is pulled        -> from
	 * - end date from which data is pulled          -> to
	 */
	public void performAnalysis() throws Exception {
		int year;
		int sizeOfResults = CO2Json.get(1).getAsJsonArray().size();

		country = CO2Json.get(1).getAsJsonArray().get(0).getAsJsonObject().get("country").getAsJsonObject().get("value")
				.getAsString();

		to = CO2Json.get(1).getAsJsonArray().get(0).getAsJsonObject().get("date").getAsInt();
		from = CO2Json.get(1).getAsJsonArray().get(sizeOfResults - 1).getAsJsonObject().get("date").getAsInt();

		CO2 = new Double[sizeOfResults];
		energy = new Double[sizeOfResults];
		pollution = new Double[sizeOfResults];

		for (int i = 0; i < sizeOfResults; i++) {
			year = CO2Json.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();
			if (CO2Json.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull()
					|| EnergyJson.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull()
					|| AirPollutionJson.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull()) {
				throw new Exception("no data for year: " + year);
			} else {
				CO2[i] = CO2Json.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").getAsDouble();
				energy[i] = EnergyJson.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").getAsDouble();
				pollution[i] = AirPollutionJson.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
						.getAsDouble();

			}
			System.out.println("year : 		" + year);
			System.out.println("CO2 : 		" + CO2[i]);
			System.out.println("Energy : 	" + energy[i]);
			System.out.println("Pollution : " + pollution[i]);

		}
		data_list.add(CO2);
		data_list.add(energy);
		data_list.add(pollution);
	}
	/**
	 * Method that is used for retrieving user data from their selections for the analysis classes.
	 * @return int 'from' year
	 */
	public int getFrom() {
		return from;
	}

	/**
	 * Method that is used for retrieving user data from their selections for the analysis classes.
	 * @return int 'to' year
	 */
	public int getTo()
	{
		return to;
	}
	
	public String getType1()
	{
		return "CO2";
	}
	
	public String getType2()
	{
		return "Energy Use";
	}
	
	public String getType3()
	{
		return "Air Pollution";
	}
}