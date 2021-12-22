package httpTest;
/**
 * Interface for the Observer class. All Observer classes use this interface. 
 *
 * @author Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 */
public interface Observer {

	/**
	 * Implementation of the update method for the observer classes using this interface.
	 * @param viewerSel
	 * @param dataArr
	 * @throws Exception
	 */
	 void update(String viewerSel, String[] dataArr) throws Exception;
	

}
