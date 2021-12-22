package httpTest;



import java.io.IOException;

import java.net.HttpURLConnection;

import java.net.URL;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.Hashtable;

import java.util.Scanner;

import com.google.gson.JsonArray;

import com.google.gson.JsonParser;


/**
 * Class for retrieving the data from user selections 
 * @author Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 *
 */
public class GetData {

	String countryCode;

	HashMap<String, String> code_dic;

	ArrayList<JsonArray> jsonArray;

	
/**
 * Constructor of GetData Class. Creates a JSon Array for the data selectedS passed as through the parameter.
 * @param dataInfo String[] of user selected data
 */
	public GetData(String[] dataInfo) {

		String country = dataInfo[0];

		String from = dataInfo[1];

		String to = dataInfo[2];

		String analysisType = dataInfo[3];	

		jsonArray = new ArrayList<JsonArray>();
		CheckCountry count = new CheckCountry();
		String countryCode = count.getCountCode(country);
		

		ArrayList<String> urls = new ArrayList<String>();

		

		if(analysisType.equals("CO2 emissions vs Energy use vs PM2.5 air pollution")) {

			urls.add(String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", countryCode, "EN.ATM.CO2E.PC", from, to));

			urls.add(String.format(

					"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", countryCode, "EG.USE.PCAP.KG.OE", from, to));

			urls.add(String.format(

					"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", countryCode, "EN.ATM.PM25.MC.M3", from, to));

		}

		else if(analysisType.equals("Average Forest area")) {

			urls.add(String.format(

					"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", countryCode, "AG.LND.FRST.ZS", from, to));
		}

		else if(analysisType.equals("PM2.5 air pollution vs Forest Area")) {

			urls.add(String.format(

					"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", countryCode, "EN.ATM.PM25.MC.M3", from, to));

			urls.add(String.format(

					"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", countryCode, "AG.LND.FRST.ZS", from, to));
		}

		else if(analysisType.equals("Current health expenditure per capita vs Mortality rate, infant")) {

			urls.add(String.format(

					"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", countryCode, "SH.XPD.CHEX.PC.CD", from, to));

			urls.add(String.format(

					"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", countryCode, "SP.DYN.IMRT.IN", from, to));
		}

		else if(analysisType.equals("Ratio of Hospital Beds vs Current Health Expenditure per 1000 people")) {

			urls.add(String.format(

					"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", countryCode, "SH.MED.BEDS.ZS", from, to));

			urls.add(String.format(

					"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", countryCode, "SH.XPD.CHEX.GD.ZS", from, to));
		}

		else if(analysisType.equals("Average of Government Expenditure on Education, total")) {

			urls.add(String.format(

					"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", countryCode, "SE.XPD.TOTL.GD.ZS", from, to));

		}

		else if(analysisType.equals("Ratio of Government Expenditure on Education vs Current Health Expenditure")) {

			urls.add(String.format(

					"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", countryCode, "SE.XPD.TOTL.GD.ZS", from, to));

			urls.add(String.format(

					"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", countryCode, "SH.XPD.CHEX.GD.ZS", from, to));

		}

		else if(analysisType.equals("Ratio of CO2 emissions and GDP per capita")){

			urls.add(String.format(

					"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", countryCode, "EN.ATM.CO2E.PC", from, to));
			

			urls.add(String.format(

					"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", countryCode, "NY.GDP.PCAP.CD", from, to));

		}


		

		for(int i = 0; i < urls.size(); ++i) {

			try {

				URL url = new URL(urls.get(i));

				HttpURLConnection conn = (HttpURLConnection) url.openConnection();

				conn.setRequestMethod("GET");

				conn.connect();

				int responsecode = conn.getResponseCode();

				if (responsecode == 200) {

					String inline = "";

					Scanner sc = new Scanner(url.openStream());

					while (sc.hasNext()) {

						inline += sc.nextLine();

					}

					sc.close();

					jsonArray.add(new JsonParser().parse(inline).getAsJsonArray());

				}

			} catch (IOException e) {

			}

		}

		return;

	}

	
	/**
	 * Method for returning created JSon Array of data points
	 * @return ArrayList<JsonArray> of data points
	 */
	public ArrayList<JsonArray> getJson() {

		return jsonArray;

	}



}
