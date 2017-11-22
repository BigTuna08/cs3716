import java.util.ArrayList;
import java.util.Collection;

/*
 * represents the schedules of all spaces at the school
 */
public enum MasterSchedule {
	INSTANCE;
	private Collection<Space> spaces;
	
	
	private MasterSchedule() {
		spaces = new ArrayList<>();
	}
	
	/*
	 * add a new space to schedule
	 */
	public void addSpace(Space s) {
		spaces.add(s);
	}
	
	/*
	 * remove space from schedule 
	 */
	public void removeSpace(Space s) {
		spaces.remove(s);
	}
	
	/*
	 * add event to schedule
	 */
	public void addEvent(Event e) {
		
	}
	
	/*
	 * remove event from schedule
	 */
	public void removeEvent(Event e) {
		
	}
	
}
