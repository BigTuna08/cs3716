package scheduler;
import java.util.ArrayList;
import java.util.Collection;

import java.io.Serializable;
/*
 * Represents the schedule of a space
 * Contains a list of time periods which the
 * space is available for.
 */
public class SpaceSchedule implements Serializable{
	public Collection<TimePeriod > blackouts;
	public Collection<TimePeriod > availabilities;
	public Collection<Event > events;
	public SpaceSchedule() {
		blackouts=new ArrayList();
		availabilities=new ArrayList();
		events=new ArrayList();
	}
	public String toString() {
		return Utils.stringifyCollection(availabilities);
	}
	public void addTime(TimePeriod tp) {
		availabilities.add(tp);
	}
	public void addBlackout(TimePeriod tp) {
		blackouts.add(tp);
	}
	public void removeTime(TimePeriod tp) {
		availabilities.remove(tp);
		
	}
}
