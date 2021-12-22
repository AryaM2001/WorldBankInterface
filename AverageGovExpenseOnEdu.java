package httpTest;

import java.util.ArrayList;

import com.google.gson.JsonArray;
/**
 * Class for calculating the analysis of the method 'Average Government Spending of Education'
 * @author Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 *
 */
public class AverageGovExpenseOnEdu implements Analysis{
	
	public static JsonArray jsonArray;
	public Double[] expense;
	public String country;
	public int from;
	public int to;
	public ArrayList<Double[]> data_list = new ArrayList<Double[]>();

	
	
	/**
	 * Constructor of the AverageGovExpenseOnEdu. Receives a JSon Array for which to analyze
	 * @param jsonArr JSon Array of user selected data
	 */
	public AverageGovExpenseOnEdu(JsonArray jsonArr) {
		jsonArray = jsonArr;
	}
	
	/**
	 * Processes the JSon Array, calculating required variables and identifying required values
	 * for the viewer to create its visualizations.
	 * The AverageGovExpenseOnEdu analysis type requires no calculations. Only for the following values to be identified:
	 * - average government expense on education for each specified year -> expense[]
	 * - country for which the data is on            -> country
	 * - start date from which data is pulled        -> from
	 * - end date from which data is pulled          -> to
	 */
	public void performAnalysis() throws Exception {
		
		int year;																											//variable to track the current year																											
		double averageExpense = 0;																						//variable to track the current average forest area																						
		double cummulativeExpense = 0;
		int sizeOfResults = jsonArray.get(1).getAsJsonArray().size();
		expense = new Double[sizeOfResults];
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
				averageExpense = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
						.getAsDouble();
			}
			expense[i] = averageExpense;
				

			System.out.println("Average expense on education for : " + year + " is " + averageExpense);
			cummulativeExpense = cummulativeExpense + averageExpense;
		}
		System.out.println(
				"The average average expense on education over the selected years is " + cummulativeExpense / sizeOfResults);
		data_list.add(expense);
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
