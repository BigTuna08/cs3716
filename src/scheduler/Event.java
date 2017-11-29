package scheduler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/*
 * Used for representing an event. After a request has been approved
 * an event is created and added to the SpaceSchedule for a space 
 * The event occurs during all periods present in times.
 */
public class Event implements Serializable{

	Request req;	
	TimePeriod period;
	public Event(Request req,TimePeriod period) {
		super();
		this.req = req;
		this.period=period;
	}
	public String getContact() {
		return req.getContact();
	}
	public void setContact(String contact) {
		req.setContact(contact);
	}
	public String getName() {
		return req.getName();
	}
	public void setName(String name) {
		req.setName(name);
	}
	public String getDescription() {
		return req.getDescription();
	}
	public void setDescription(String description) {
		req.setDescription(description);
	}
	public ArrayList<TimePeriod> getRequestPeriods() {
		return req.getRequestPeriods();
	}
	public void setRequestPeriods(ArrayList<TimePeriod> requestPeriods) {
		req.setRequestPeriods(requestPeriods);
	}
	public Space getLocation() {
		return req.getLocation();
	}
	public void setLocation(Space location) {
		req.setLocation(location);
	}
	public String toString() {
		return req.toString();
	}
	public String getUsername() {
		return req.getUsername();
	}
	public int hashCode() {
		return req.hashCode();
	}
	public void setUsername(String username) {
		req.setUsername(username);
	}

}
