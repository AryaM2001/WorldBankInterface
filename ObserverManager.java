package httpTest;

import java.util.ArrayList;

import com.google.gson.JsonArray;
/**
 * Class for updating the observers 
 * @author Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 * 
 *
 */
public class ObserverManager implements Subject{

	private static ArrayList<Observer> observers;
	private String[] dataArr;
	private static ObserverManager instance;
	
	/**
	 * Method for updating observers
	 * @return
	 */
	public static ObserverManager getInstance() {

		if (instance == null)
			observers = new ArrayList<Observer>();
			instance = new ObserverManager();



		return instance;

	}
	/**
	 * Method for registering observers
	 * @param o Observer object
	 */
	public void register(Observer o) {
		// TODO Auto-generated method stub
		observers.add(o);
	}
	/**
	 * Method for unregistering observers
	 * @param o Observer object
	 */
	public void unregister(Observer o) {
		// TODO Auto-generated method stub
		observers.remove(observers.indexOf(o));
	}
	/**
	 * Method for notifying observers
	 * @param viewerSel
	 * @param dataArr
	 * @throws Exception
	 */
	public void notifyObserver(String viewerSel, String[] dataArr) throws Exception {
		// TODO Auto-generated method stub
		for(Observer observer : observers) {
			observer.update(viewerSel, dataArr);
		}
	}

}
