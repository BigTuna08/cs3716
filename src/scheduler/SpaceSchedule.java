package scheduler;

import java.util.ArrayList;
import java.util.Collection;

import java.io.Serializable;

/**
 * The schedule of availabilities and events for a space
 * @author ben
 *
 */
public class SpaceSchedule implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//times when this space is available for booking
	public Collection<TimePeriod> availabilities;
	//overrides availabilities with unavailable times
	public Collection<TimePeriod> blackouts;
	//events scheduled in this space
	public Collection<Event> events;

	public SpaceSchedule() {
		blackouts = new ArrayList<TimePeriod>();
		availabilities = new ArrayList<TimePeriod>();
		events = new ArrayList<Event>();
	}

	//schedules a blackout
	public void addBlackout(TimePeriod tp) {
		blackouts.add(tp);
	}

	//schedules an event
	public void addEvent(Event e) {
		events.add(e);
	}
	
	//schedules an availability
	public void addTime(TimePeriod tp) {
		availabilities.add(tp);
	}

	//removes an availability
	public void removeTime(TimePeriod tp) {
		availabilities.remove(tp);
	}

	public String toString() {
		return "Avail:" + Utils.stringifyCollection(availabilities) + "\n evts:" + Utils.stringifyCollection(events);
	}
}
