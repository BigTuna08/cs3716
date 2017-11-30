package scheduler;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A request to schedule a room weekly
 * containing alternative time periods
 * @author ben
 *
 */
public class Request implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;// name of event
	private String description;//comment
	private Space location;

	private String username;//name of issuer
	String contact;//contact info for issuer

	private int daysRequested;//number of days per week this event should occur
	private ArrayList<EventTimeProposal> requestAlternatives;//proposed times
	private int numberAlreadyApproved;//count of principal approved alternatives

	public String toString() {
		return "[" + getUsername() + "|" + contact + "|" + getName() + "|" + getDescription() + "|"
				+ getLocation().getName() + "|" + getRequestAlternatives() + "]";
	}

	public Request(String name, String description, Space location, String username, String contact, int daysRequested,
			ArrayList<EventTimeProposal> requestAlternatives) {
		super();
		this.setName(name);
		this.setDescription(description);
		this.setLocation(location);
		this.setUsername(username);
		this.contact = contact;
		this.setDaysRequested(daysRequested);
		this.setRequestAlternatives(requestAlternatives);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumberAlreadyApproved() {
		return numberAlreadyApproved;
	}

	public void setNumberAlreadyApproved(int numberAlreadyApproved) {
		this.numberAlreadyApproved = numberAlreadyApproved;
	}

	public Space getLocation() {
		return location;
	}

	public void setLocation(Space location) {
		this.location = location;
	}

	public int getDaysRequested() {
		return daysRequested;
	}

	public void setDaysRequested(int daysRequested) {
		this.daysRequested = daysRequested;
	}

	public ArrayList<EventTimeProposal> getRequestAlternatives() {
		return requestAlternatives;
	}

	public void setRequestAlternatives(ArrayList<EventTimeProposal> requestAlternatives) {
		this.requestAlternatives = requestAlternatives;
	}

}
