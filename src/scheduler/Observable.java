package scheduler;
/**
 * Observable from the observer pattern
 * @author ben
 *
 */
public interface Observable {
	public void subscribe(Observer o);
}
