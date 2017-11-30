package scheduler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/*
 * Schedule of all spaces, availabilities, and events
 */
public class MasterSchedule implements Serializable, Observable, Transient {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static MasterSchedule instance = null;//singleton

	public static MasterSchedule getInstance() {
		if (instance == null) {
			try {
				instance = (MasterSchedule) Unserializer.fromFile("masterschedule.data");
			} catch (Exception e) {
				instance = new MasterSchedule();
			}
		}
		return instance;
	}

	
	//available rooms
	private Collection<Space> spaces;
	transient ArrayList<Observer> subscribers;

	private MasterSchedule() {
		spaces = new ArrayList<>();
		initTransient();
	}

	//blacks out a period for a room
	public void addBlackout(Space s, TimePeriod tp) {
		s.getSchedule().addBlackout(tp);
		updateAll();
	}


	//adds a room to the schedule
	public void addSpace(Space s) {
		spaces.add(s);
		updateAll();
	}

	//makes a time available in a room
	public void addTime(Space s, TimePeriod tp) {
		s.getSchedule().addTime(tp);
		updateAll();
	}

	//removes a room from the schedule
	public void deleteSpace(Space s) {
		// TODO? make sure this doesn't violate a billion constraints
		spaces.remove(s);
		updateAll();
	}

	public Collection<Space> getSpaces() {
		return spaces;
	}

	public void initTransient() {
		subscribers = new ArrayList<Observer>();
	}


	//remove room from schedule
	public void removeSpace(Space s) {
		spaces.remove(s);
		updateAll();

	}

	//remove availability from room
	public void removeTime(Space s, TimePeriod tp) {
		s.getSchedule().removeTime(tp);
		updateAll();

	}

	@Override
	public void subscribe(Observer o) {

		if (!subscribers.contains(o)) {// idempotent
			subscribers.add(o);
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("spaces:\n");
		for (Space s : spaces) {
			sb.append(s.toString() + "\n");
		}
		sb.append("\n");
		return sb.toString();
	}
	
	//notify all observers
	public void updateAll() {
		for (Observer o : subscribers) {
			o.update();
		}
	}
}
