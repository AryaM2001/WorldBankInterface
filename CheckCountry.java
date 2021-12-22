package httpTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * Class for checking through the validity and options for the analysis and viewers. 
 * Provides methods of checking the country, the date range, and the appropriate viewers. 
 * @author Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 *
 */
public class CheckCountry{
	HashMap<String, String> code_dic;
	HashMap<String, ArrayList<String>> urls;
	
	/**
	 * Constructor for the CheckCountry class. Implements a dictionary of country codes from a text document. 
	 */
	public CheckCountry() {
	code_dic = new HashMap<String, String>();
	String[] temp;
	String st;
	File file = new File("CountryCode.txt");
	try {
		Scanner fs = new Scanner(file);
		while(fs.hasNextLine()) {
			temp = fs.nextLine().split(",");
			code_dic.put(temp[0], temp[1]);
		}
		} catch (FileNotFoundException e) {
		System.out.println("File not Found.");
		}
	}
	/**
	 * Method for returning the dictionary of available countries and their respective codes for the WorldBank Database. 
	 * @return HashMap<String, String> Country Dictionary 
	 */
	public HashMap<String, String> getDic() {
		return code_dic;
	}
	/**
	 * Method for returning the country code assigned within the dictionary of countries
	 * @param country String value of country to find code for
	 * @return String value of the code for country passed through
	 */
	public String getCountCode(String country) {
		if(code_dic.get(country) == null) {
			return "No Country found";
		}
		else {
			return code_dic.get(country);
		}
	}
	/**
	 * Method for returning the list of countries that have at least on data value for the respective method that was passed through.
	 * @param method String of the method that the countries must have data for
	 * @return Vector<String> of the available countries for analysis as they have at least one data value.
	 */
	public Vector<String> getValidCountry(String method) {
		urls = new HashMap<String, ArrayList<String>>();
		ArrayList<String> temp = new ArrayList<String>();
		Vector<String> countryList = new Vector<String>();
		String country, totals;
		
		for(String i: code_dic.keySet()) {

			if(method.equals("CO2 emissions vs Energy use vs PM2.5 air pollution")) {
				ArrayList<String> holder = new ArrayList<String>();		

				holder.add(String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", code_dic.get(i), "EN.ATM.CO2E.PC", 2010, 2020));

				holder.add(String.format(

						"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", code_dic.get(i), "EG.USE.PCAP.KG.OE", 2010, 2020));

				holder.add(String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", code_dic.get(i), "EN.ATM.PM25.MC.M3", 2010, 2020));
				urls.put(i, holder);

			}
			else if(method.equals("Average Forest area")) {
				ArrayList<String> holder = new ArrayList<String>();		

				holder.add(String.format(

						"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", code_dic.get(i), "AG.LND.FRST.ZS", 2010, 2020));
				urls.put(i, holder);

			}

			else if(method.equals("PM2.5 air pollution vs Forest Area")) {
				ArrayList<String> holder = new ArrayList<String>();		

				holder.add(String.format(

						"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", code_dic.get(i), "EN.ATM.PM25.MC.M3", 2010, 2020));

				holder.add(String.format(

						"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", code_dic.get(i), "AG.LND.FRST.ZS", 2010, 2020));
				urls.put(i, holder);

			}

			else if(method.equals("Current health expenditure per capita vs Mortality rate, infant")) {
				ArrayList<String> holder = new ArrayList<String>();		

				holder.add(String.format(

						"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", code_dic.get(i), "SH.XPD.CHEX.PC.CD", 2010, 2020));

				holder.add(String.format(

						"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", code_dic.get(i), "SP.DYN.IMRT.IN", 2010, 2020));
				urls.put(i, holder);

			}

			else if(method.equals("Ratio of Hospital Beds vs Current Health Expenditure per 1000 people")) {
				ArrayList<String> holder = new ArrayList<String>();		

				holder.add(String.format(

						"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", code_dic.get(i), "SH.MED.BEDS.ZS", 2010, 2020));

				holder.add(String.format(

						"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", code_dic.get(i), "SH.XPD.CHEX.GD.ZS", 2010, 2020));
				urls.put(i, holder);

			}

			else if(method.equals("Average of Government Expenditure on Education, total")) {
				ArrayList<String> holder = new ArrayList<String>();		

				holder.add(String.format(

						"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", code_dic.get(i), "SE.XPD.TOTL.GD.ZS", 2010, 2020));
				urls.put(i, holder);

			}

			else if(method.equals("Ratio of Government Expenditure on Education vs Current Health Expenditure")) {
				ArrayList<String> holder = new ArrayList<String>();		

				holder.add(String.format(

						"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", code_dic.get(i), "SE.XPD.TOTL.GD.ZS", 2010, 2020));

				holder.add(String.format(

						"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", code_dic.get(i), "SH.XPD.CHEX.GD.ZS", 2010, 2020));
				urls.put(i, holder);

			}
			else if(method.equals("Ratio of CO2 emissions and GDP per capita")){
				ArrayList<String> holder = new ArrayList<String>();		

				holder.add(String.format(

						"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", code_dic.get(i), "SE.XPD.TOTL.GD.ZS", 2010, 2020));

				holder.add(String.format(

						"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", code_dic.get(i), "SH.XPD.CHEX.GD.ZS", 2010, 2020));			
				urls.put(i, holder);
			}
			else {

				return countryList;
			}
		}
		
		for(String k: urls.keySet()) {
			ArrayList<JsonArray> jsonArray = new ArrayList<JsonArray>();
			try {
				country = k;
				temp = urls.get(country);
				for(int m = 0;m < temp.size(); m++ ) {
					URL url = new URL(temp.get(m));
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
						for(int j=0; j < jsonArray.size(); j++) {
							for(int l=0; l< 11; l++) {
								if (jsonArray.get(0).getAsJsonArray().get(1).getAsJsonArray().get(1).getAsJsonObject().get("value") != null) {
									country = jsonArray.get(0).getAsJsonArray().get(1).getAsJsonArray().get(1).getAsJsonObject().get("country").getAsJsonObject().get("value").getAsString();
									if(!countryList.contains(country)) {
										countryList.add(country);
									}
								}
							}
						}
					}
				}
			} catch (IOException e) {

				// TODO Auto-generated catch block e.printStackTrace();
			}
		}
		return countryList;

	}
	/**
	 *  Method for calculating if the selected years by the user are valid for analysis purposes. If not, it extends a message to the user to alert of invalid time frame.
	 * @param method String of selected user method
	 * @param count String of the selected user country
	 * @param from String of the selected 'from' year
	 * @param to String of the selected 'to' year
	 * @return Boolean of whether the range selected is valid or not.
	 */
	public Boolean getValidYears(String method, String count, String from, String to){
		urls = new HashMap<String, ArrayList<String>>();
		ArrayList<String> temp = new ArrayList<String>();
		Boolean yearsList = true;
		String country, totals;
		
		country = code_dic.get(count);

		if(method.equals("CO2 emissions vs Energy use vs PM2.5 air pollution")) {
			ArrayList<String> holder = new ArrayList<String>();		

			holder.add(String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", country, "EN.ATM.CO2E.PC", from, to));

			holder.add(String.format(

					"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", country, "EG.USE.PCAP.KG.OE", from, to));

			holder.add(String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", country, "EN.ATM.PM25.MC.M3", from, to));
				urls.put(count, holder);

			}
		else if(method.equals("Average Forest area")) {
			ArrayList<String> holder = new ArrayList<String>();		

			holder.add(String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", country, "AG.LND.FRST.ZS", from, to));
				urls.put(count, holder);

			}

		else if(method.equals("PM2.5 air pollution vs Forest Area")) {
			ArrayList<String> holder = new ArrayList<String>();		

			holder.add(String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", country, "EN.ATM.PM25.MC.M3", from, to));

			holder.add(String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", country, "AG.LND.FRST.ZS", from, to));
			urls.put(count, holder);
		}

		else if(method.equals("Current health expenditure per capita vs Mortality rate, infant")) {
			ArrayList<String> holder = new ArrayList<String>();		

			holder.add(String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", country, "SH.XPD.CHEX.PC.CD", from, to));

			holder.add(String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", country, "SP.DYN.IMRT.IN", from, to));
			urls.put(count, holder);
		}
		else if(method.equals("Ratio of Hospital Beds vs Current Health Expenditure per 1000 people")) {
			ArrayList<String> holder = new ArrayList<String>();		

			holder.add(String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", country, "SH.MED.BEDS.ZS", from, to));

			holder.add(String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", country, "SH.XPD.CHEX.GD.ZS", from, to));
			urls.put(count, holder);
			}

		else if(method.equals("Average of Government Expenditure on Education, total")) {
				ArrayList<String> holder = new ArrayList<String>();		

				holder.add(String.format(

						"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", country, "SE.XPD.TOTL.GD.ZS", from, to));
				urls.put(count, holder);

			}

		else if(method.equals("Ratio of Government Expenditure on Education vs Current Health Expenditure")) {
				ArrayList<String> holder = new ArrayList<String>();		

				holder.add(String.format(

						"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", country, "SE.XPD.TOTL.GD.ZS", from, to));

				holder.add(String.format(

						"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", country, "SH.XPD.CHEX.GD.ZS", from, to));
				urls.put(count, holder);

			}
		else if(method.equals("Ratio of CO2 emissions and GDP per capita")){
				ArrayList<String> holder = new ArrayList<String>();		

				holder.add(String.format(

						"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", country, "SE.XPD.TOTL.GD.ZS", from, to));

				holder.add(String.format(

						"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", country, "SH.XPD.CHEX.GD.ZS", from, to));			
				urls.put(count, holder);
			}
		else {

				return yearsList;
		}
		
		ArrayList<JsonArray> jsonArray = new ArrayList<JsonArray>();
		try {
			temp = urls.get(count);
			for(int m = 0;m < temp.size(); m++ ) {
				URL url = new URL(temp.get(m));
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
					for(int j=0; j < jsonArray.size(); j++) {
						boolean te = jsonArray.get(0).getAsJsonArray().get(1).getAsJsonArray().get(j).getAsJsonObject().get("value").isJsonNull();
						if (te) {
							System.out.println("here");
							yearsList = false;
						}
					}
				}
			}
		} catch (IOException e) {

				// TODO Auto-generated catch block e.printStackTrace();
		}
		return yearsList;
	}
/**
 * Method for selecting the appropriate viewers for the analysis type selected. 
 * @param method String of selected method for analysis
 * @return ArrayList<String> of proper viewers for the selected analysis
 */
	public ArrayList<String> checkViewers(String method){
		ArrayList<String> availViewers = new ArrayList<String>();
		if(method.equals("CO2 emissions vs Energy use vs PM2.5 air pollution")) {
			availViewers.add("Report");
			availViewers.add("Line Chart");
			availViewers.add("Bar Chart");
			availViewers.add("Scatter Chart");
			return availViewers;
		}
		else if(method.equals("Average Forest area")) {
			availViewers.add("Report");
			availViewers.add("Line Chart");
			availViewers.add("Bar Chart");
			availViewers.add("Scatter Chart");
			availViewers.add("Pie Chart");

			return availViewers;
		}
		else if(method.equals("PM2.5 air pollution vs Forest Area")) {
			availViewers.add("Report");
			availViewers.add("Line Chart");
			availViewers.add("Bar Chart");
			availViewers.add("Scatter Chart");
			return availViewers;
			
		}
		else if(method.equals("Current health expenditure per capita vs Mortality rate, infant")) {
			availViewers.add("Report");
			availViewers.add("Line Chart");
			availViewers.add("Bar Chart");
			availViewers.add("Scatter Chart");
			return availViewers;
		}
		else if(method.equals("Ratio of Hospital Beds vs Current Health Expenditure per 1000 people")) {
			availViewers.add("Report");
			availViewers.add("Line Chart");
			availViewers.add("Bar Chart");
			availViewers.add("Scatter Chart");
			return availViewers;

			}

		else if(method.equals("Average of Government Expenditure on Education, total")) {
			availViewers.add("Report");
			availViewers.add("Line Chart");
			availViewers.add("Bar Chart");
			availViewers.add("Scatter Chart");
			availViewers.add("Pie Chart");

			return availViewers;
			}

		else if(method.equals("Ratio of Government Expenditure on Education vs Current Health Expenditure")) {
			availViewers.add("Report");
			availViewers.add("Report");
			availViewers.add("Line Chart");
			availViewers.add("Bar Chart");
			availViewers.add("Scatter Chart");
			return availViewers;
			}
		else if(method.equals("Ratio of CO2 emissions and GDP per capita")){
			availViewers.add("Report");
			availViewers.add("Line Chart");
			availViewers.add("Bar Chart");
			availViewers.add("Scatter Chart");
			return availViewers;
		}
		else {

			return availViewers;
		}
	}
}
