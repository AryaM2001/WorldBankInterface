// This interface handles adding, deleting and updating
// all observers 
package httpTest;

/**
 * 
 * Interface for the Subject class. All subject classes use this interface. 
 *
 * @author Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 *
 */
public interface Subject {
	/**
	 * Implementation of the method for the Subject classes using this interface.
	 *  @param o Obeserver
	 */
	public void register(Observer o);
	/**
	 * Implementation of the method for the Subject classes using this interface.
	 * @param o Obeserver
	 */
	public void unregister(Observer o);
	/**
	 * Implementation of the method for the Subject classes using this interface.
	 * @param o Obeserver
	 */
	public void notifyObserver(String viewerSel, String[] dataArr) throws Exception;
	
}