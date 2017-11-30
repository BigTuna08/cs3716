package scheduler;

import java.io.Serializable;

/**
 * A time proposed as one possible period of an event.
 * Essentially a period that remembers whether it is approved.
 * @author ben
 *
 */
public class EventTimeProposal implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//whether the principal has approved this time period for the corresponding event
	public boolean approved;
	public TimePeriod period;

	public EventTimeProposal(TimePeriod period) {
		super();
		approved = false;
		this.period = period;
	}

	public String toString() {
		return period.toString() + " approved?" + approved;
	}

	public String toBriefString() {
		return period.toBriefString();
	}
}
