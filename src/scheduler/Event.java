package scheduler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/*
 * A school event. Occurs during each period present in periods.
 */
public class Event implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//periods in which the event occurs
	ArrayList<TimePeriod> periods = new ArrayList<>();
	//the corresponding request
	Request req;

	public Event(Request req, Collection<TimePeriod> periods) {
		super();
		this.req = req;
		this.periods.addAll(periods);
	}

	public String getContact() {
		return req.contact;
	}

	public String getDescription() {
		return req.getDescription();
	}

	public Space getLocation() {
		return req.getLocation();
	}

	public String getName() {
		return req.getName();
	}

	public String getUsername() {
		return req.getUsername();
	}

	public int hashCode() {
		return req.hashCode();
	}

	public String toString() {
		return req.toString() + "|periods:" + periods;
	}

}
