package httpTest;

import java.util.ArrayList;
/**
 * 
 * Interface for the Analysis class. All analysis classes use this interface. 
 *
 * @author Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 *
 */
public interface Analysis {
	/**
	 * Implementation of the method for the analysis classes using this interface.
	 * @throws Exception
	 */
	void performAnalysis() throws Exception;
	/**
	 * Implementation of the method that is used for retrieving user data from their selections for the analysis classes.
	 * @return ArrayList<Double[]> user data 
	 */
	public ArrayList<Double[]> getdata();
	/**
	 * Implementation of the method that is used for retrieving the from date from the user selection for the analysis classes.
	 * @return int from date
	 */
	public int getFrom();

	/**
	 * Implementation of the method that is used for retrieving  analysis classes.
	 * @return String 
	 */
	public String getType1();

	/**
	 * Implementation of the method that is used for analysis classes.
	 * @return String 
	 */
	public String getType2();
	
	/**
	 * Implementation of the method that is used for analysis classes.
	 * @return String 
	 */
	public String getType3();
	
	/**
	 * Implementation of the method that is used for analysis classes.
	 * @return int 
	 */
	public int getTo();

}
