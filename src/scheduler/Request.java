package scheduler;
import java.io.Serializable;
import java.util.ArrayList;

/*
 * used to store all information required to request a room
 */
public class Request implements Serializable{

	public Request(String name, String description, Space location, ArrayList<TimePeriod> requestPeriods) {
		this.name = name;
		this.description = description;
		this.location = location;
		this.requestPeriods = requestPeriods;
	}
	//private Stakeholder issuer;
	private String name;
	private String description;//comment
	
	private Space location;
	ArrayList<TimePeriod> requestPeriods;
	private boolean highPriority = false;
	
	private boolean acceptOtherTimes;
	private boolean acceptOtherDates;
	private boolean acceptOtherRooms;
	public String toString() {
		return "["+name+"|"+description+"|"+location+"|"+requestPeriods+"]";
	}
	
}
