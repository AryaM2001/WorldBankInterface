package httpTest;

//te

import com.google.gson.JsonArray;
import java.lang.Math; // Needed to use Math.round()
import java.util.ArrayList;

/**
 *  Class for calculating the analysis of the method 'CO2 emissions and GDP per capita'
 * @author  Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 *
 */
public class CO2vsGDP implements Analysis {
	
	private JsonArray jsonArray;
	private JsonArray jsonArray2;
	private Double[] GDP;
	private Double[] CO2;
	private String country;
	private int from;
	private int to;
	public ArrayList<Double[]> data_list = new ArrayList<Double[]>();

	
	
	/**
	 *  Constructor of CO2VsEnergyUseVsAirPollution. Receives a JSon Array for CO2 data, and GDP data, of which to analyze
	 * @param CO2Emission JsonArray of CO2 emissions for selected years
	 * @param GdpPerCapita JsonArray of GDP per capita for selected years
	 */
	public CO2vsGDP (JsonArray CO2Emission, JsonArray GdpPerCapita ) {
		jsonArray = CO2Emission;
		jsonArray2 = GdpPerCapita;
		
		
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
	 * Method for retrieving the averages for the CO2 emissions calculations
	 * @return Double[] of all CO2 emission calculations
	 * @throws Exception
	 */
	public Double[] getCO2() throws Exception {
		int year;																											//variable to track the current year																											
		double CO2emmision = 0;																						//variable to track the current average forest area																						
		int sizeOfResults = jsonArray.get(1).getAsJsonArray().size(); 
		CO2 = new Double[sizeOfResults];
		country = jsonArray.get(1).getAsJsonArray().get(0).getAsJsonObject()
				.get("country").getAsJsonObject().get("value").getAsString();
		to = jsonArray.get(1).getAsJsonArray().get(0).getAsJsonObject()
				.get("date").getAsInt();
		from = jsonArray.get(1).getAsJsonArray().get(sizeOfResults - 1)
				.getAsJsonObject().get("date").getAsInt();
		
		for (int i = 0; i < sizeOfResults; i++) {
			year = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();
			if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull()) {
				throw new Exception("no data for year: " + year);			}
			else {
				CO2emmision = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
						.getAsDouble();
			}
			CO2[i] = CO2emmision;
		}
		return CO2;	

	}
	
	/**
	 * Method that is used for retrieving user data from their selections for the analysis classes.
	 * @return int 'from' year
	 */
	public int getFrom() {
		return from;
	}
	/**
	 * Method for retrieving the averages for the GDP calculations
	 * @return Double[] of all GDP emission calculations
	 * @throws Exception
	 */
	public Double[] getGDP() throws Exception {
		int year;																											//variable to track the current year																											
		double GDPperCapita = 0;																						//variable to track the current average forest area																						
		int sizeOfResults = jsonArray2.get(1).getAsJsonArray().size(); 
		GDP = new Double[sizeOfResults];
		country = jsonArray2.get(1).getAsJsonArray().get(0).getAsJsonObject()
				.get("country").getAsJsonObject().get("value").getAsString();
		to = jsonArray2.get(1).getAsJsonArray().get(0).getAsJsonObject()
				.get("date").getAsInt();
		from = jsonArray2.get(1).getAsJsonArray().get(sizeOfResults - 1)
				.getAsJsonObject().get("date").getAsInt();
		for (int i = 0; i < sizeOfResults; i++) {
			year = jsonArray2.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();
			if (jsonArray2.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull()) {
				throw new Exception("no data for year: " + year);			}
			else {
				GDPperCapita = jsonArray2.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
						.getAsDouble();
			}
			GDP[i] = GDPperCapita;
		}
		return GDP;	

	}
 
	/**
	 * Method for calculating required variables for CO2vsGDP and identifying required values
	 * for the viewer to create its visualizations.The CO2vsGDP analysis type :
	 * -  CO2 emissions (metric tons per capita) -> CO2data[]
	 * - GDP per capita  -> GDPdata[]
	 * - CO2 vs GDP -> CO2vsGDP[]
	 * @throws Exception
	 */
	public void performAnalysis() throws Exception {
		Double[] CO2data = getCO2();
		Double[] GDPdata = getGDP();
		Double[] CO2vsGDP = new Double [CO2data.length];

		for (int i = 0; i < CO2vsGDP.length; i++) {

			double data = CO2data[i]/GDPdata[i];
			CO2vsGDP[i] = data;
			
			if (CO2vsGDP[i] == 0) {
				System.out.println("Analysis not avaliable for " + ""+ (from + i));
			}
			else {
				System.out.println("ratio of CO2 emission and GDP per capita for " + country  + " in " + (from + i) + " is" + " " + ((CO2vsGDP[i]*100.0)/100.0));
			}		
		}	
		data_list.add(CO2);
		data_list.add(GDP);
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
