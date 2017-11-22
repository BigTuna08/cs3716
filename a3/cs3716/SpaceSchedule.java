import java.util.Collection;

/*
 * Represents the schedule of a space
 * Contains a list of time periods which the
 * space is available for.
 */
public class SpaceSchedule {
	
	/*
	 * add an event to the schedule
	 */
	public void addEvent(Event e) {
		
	}
	
	/*
	 * return collection of all events for the space
	 */
	public Collection<Event> getEvents() {
		return null;
	}
	
	
	/*
	 * returns collection of all time periods for the space
	 */
	public Collection<TimePeriod> getTimeSlots() {
		return null;
	}
	
	/*
	 * returns collection of empty time periods for the space
	 */
	public Collection<TimePeriod> getEmptyTimeSlots() {
		return null;
	}
}
