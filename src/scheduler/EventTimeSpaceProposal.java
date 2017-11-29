package scheduler;

public class EventTimeSpaceProposal {
	public Space room;
	public EventTimeSpaceProposal(Space room, TimePeriod period) {
		super();
		this.room = room;
		this.period = period;
	}
	public TimePeriod period;
	public boolean approved;
}
