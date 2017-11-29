package scheduler;
import java.io.Serializable;
import java.util.ArrayList;

/*
 * used to store all information required to request a room
 */
public class Request implements Serializable{

	public Request(String name, String description, String evtName, String desc, Space location, ArrayList<TimePeriod> requestPeriods) {
		this.setUsername(name);
		this.contact=contact;
		this.name = name;
		this.description = description;
		this.location = location;
		this.requestPeriods = requestPeriods;
	}
	//private Stakeholder issuer;
	private String username;//name of issuer
	private String contact;//email
	private String name;//name of event
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
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
	public ArrayList<TimePeriod> getRequestPeriods() {
		return requestPeriods;
	}
	public void setRequestPeriods(ArrayList<TimePeriod> requestPeriods) {
		this.requestPeriods = requestPeriods;
	}
	private String description;//comment
	
	private Space location;
	public Space getLocation() {
		return location;
	}
	public void setLocation(Space location) {
		this.location = location;
	}
	ArrayList<TimePeriod> requestPeriods;
	private boolean highPriority = false;
	
	private boolean acceptOtherTimes;
	private boolean acceptOtherDates;
	private boolean acceptOtherRooms;
	public String toString() {
		return "["+getUsername()+"|"+contact+"|"+name+"|"+description+"|"+location.getName()+"|"+requestPeriods+"]";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
