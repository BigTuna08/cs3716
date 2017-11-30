package scheduler;

/**
 * Directs input from the user interface to the back end model.
 * Also does validation (should separate this class into two).
 * @author ben
 *
 */
public enum Controller {
	INSTANCE;//singleton
	
	// TODO, change this, horrible global variable for validation
	public static String lastConflict = "";
	MasterSchedule ms = MasterSchedule.getInstance();

	//black out a period, not implemented
	public void addBlackOut(Space s, TimePeriod tp) {
		ms.addBlackout(s, tp);
	}
	
	//add a room to the master schedule
	public void addSpace(String name, String description) {
		Space s = new Space(name, description);
		MasterSchedule.getInstance().addSpace(s);
	}
	
	//add a time availability to a room
	public void addTimePeriod(Space s, TimePeriod tp) {
		ms.addTime(s, tp);
	}
	
	//deletes a room
	public void deleteSpace(Space s) {
		ms.deleteSpace(s);
	}

	//removes an availability from a room
	public void removeTimePeriod(Space s, TimePeriod tp) {
		ms.removeTime(s, tp);
	}

	//marks a request time slot as approved
	public boolean approveTimeSlot(Request r, EventTimeProposal time) {
		return RequestQueue.getInstance().approveTimeSlot(r, time);

	}

	//checks if t is an available timeperiod for room s
	public boolean validate(Space s, TimePeriod t) {
		// check if t is in availabilities
		boolean withinAvailability = false;
		for (TimePeriod p : s.getSchedule().availabilities) {
			if (t.within(p)) {
				
				withinAvailability = true;
				break;
			}
		}
		if (!withinAvailability) {
			
			return false;
		}
		// check it does not overlap an existing event
		for (Event e : s.getSchedule().events) {
			for (TimePeriod p : e.periods) {
				if (p.overlaps(t)) {
					
					Controller.lastConflict = ": intersects with " + e.getName();
					return false;
				}
			}
		}
		// TODO check overlap with partially approved event (not a reasonable situation)
		return true;
	}
	
	//removes a request from consideration
	public static void deleteRequest(Request r) {
		RequestQueue.getInstance().removeRequest(r);
	}
}
