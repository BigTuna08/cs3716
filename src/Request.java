/*
 * used to store all information required to request a room
 */
public class Request {

	private Stakeholder issuer;
	private Space location;
	private TimePeriod requestPeriod;
	private String comment;
	private boolean highPriority = false;
	
	private boolean acceptOtherTimes;
	private boolean acceptOtherDates;
	private boolean acceptOtherRooms;
	
}
