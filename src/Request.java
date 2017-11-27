import java.io.Serializable;

/*
 * used to store all information required to request a room
 */
public class Request implements Serializable{

	//private Stakeholder issuer;
	private String name;
	
	private Space location;
	private TimePeriod requestPeriod;
	private String comment;
	private boolean highPriority = false;
	
	private boolean acceptOtherTimes;
	private boolean acceptOtherDates;
	private boolean acceptOtherRooms;
	
}
