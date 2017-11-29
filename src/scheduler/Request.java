package scheduler;
import java.io.Serializable;
import java.util.ArrayList;

/*
 * used to store all information required to request a room
 */
public class Request implements Serializable{

	public Request(String name, String description, String evtName, String desc, ArrayList<EventTimeSpaceProposal> requestAlternatives) {
		this.setUsername(name);
		this.contact=contact;
		this.name = name;
		this.description = description;
		this.requestAlternatives = requestAlternatives;
	}
	//private Stakeholder issuer;
	private String username;//name of issuer
	private String contact;//email
	private String name;//name of event
	private int daysPerWeek;
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
	public ArrayList<EventTimeSpaceProposal> getRequestAlternatives() {
		return requestAlternatives;
	}
	public void setRequestPeriods(ArrayList<EventTimeSpaceProposal> requestAlternatives) {
		this.requestAlternatives = requestAlternatives;
	}
	private String description;//comment
	

	ArrayList<EventTimeSpaceProposal> requestAlternatives;
	private boolean highPriority = false;
	
	private boolean acceptOtherTimes;
	private boolean acceptOtherDates;
	private boolean acceptOtherRooms;
	public String toString() {
		return "["+getUsername()+"|"+contact+"|"+name+"|"+description+"|"+location.getName()+"|"+requestAlternatives+"]";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
