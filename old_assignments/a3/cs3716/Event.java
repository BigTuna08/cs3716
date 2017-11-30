import java.util.Collection;

/*
 * Used for representing an event. After a request has been approved
 * an event is created and added to the SpaceSchedule for a space 
 * The event occurs during all periods present in times.
 */
public class Event {
	String stakeHolderID;
	Collection<TimePeriod> times;
	Space location;

}
