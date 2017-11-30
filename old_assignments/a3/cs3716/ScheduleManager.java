import java.util.Collection;

/*
 * Controls the MasterSchedule object
 * responsible for creating and changing
 * MasterSchedules
 */
public class ScheduleManager {

	private MasterSchedule schedule = null;
	
	/*
	 * creates a new MasterSchedule to manage
	 */
	public void createSchedule(Collection<Space> spaces) {
		schedule = new MasterSchedule();
		for (Space s: spaces) schedule.addSpace(s);
	}
	
	/*
	 * load a MasterSchedule from text file
	 */
	public void loadSchedule(String scheduleFile) {
		
	}
	
	/*
	 * add a new space to schedule
	 */
	public void addSpace(Space s) {
		schedule.addSpace(s);
	}
	
	/*
	 * remove space from schedule 
	 */
	public void removeSpace(Space s) {
		schedule.removeSpace(s);
	}
	
	/*
	 * add event to schedule
	 */
	public void addEvent(Event e) {
		schedule.addEvent(e);
	}
	
	/*
	 * remove event from schedule
	 */
	public void removeEvent(Event e) {
		schedule.removeEvent(e);
	}
	
	/*
	 * return collection of rooms, which each
	 * contain their own schedules.
	 */
	public MasterSchedule getSchedule() {
		return schedule;
	}
}
