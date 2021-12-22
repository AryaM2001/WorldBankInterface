package httpTest;

import java.util.ArrayList;

/**
 * Class for calculating of the method 'DropdownObserver'
 * @author Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 *
 */
public class DropdownObserver implements Observer {
	
	String[] dataArr;
	Session ses;
	
	/**
	 * Constructor for DropdownObserver, which creates a Session Object
	 * @param _ses Session object 
	 */
	public DropdownObserver(Session _ses) {
		ses = _ses;
	}
	
	/**
	 * Method for updating and creating a new viewer with the data passed. 
	 * @param viewerSel String value of viewer selected
	 * @param dataArr String[] value of the data selected
	 */
	public void update(String viewerSel, String[] dataArr) throws Exception {
		ses.createNewViewer(viewerSel, dataArr);
	}

}
