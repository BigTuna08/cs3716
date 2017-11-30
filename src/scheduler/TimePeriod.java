package scheduler;

import java.io.Serializable;
import java.time.*;

/**
 * A weekly repeating time period which occurs on a set of days for an entire semester
 * and for specified period of time each day
 */
public class TimePeriod implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Semester semester;
	DayMask daysOfWeek;//which days this occurs on
	LocalTime endTime;//applies to each day
	LocalTime startTime;//applies to each day

	public TimePeriod(Semester semester, LocalTime st, LocalTime et, DayMask days) {
		this.semester = semester;

		startTime = st;
		endTime = et;
		daysOfWeek = days;
	}



	//check if this period overlaps another
	public boolean overlaps(TimePeriod t) {
		if (!semester.equals(t.semester))
			return false;
		if (!daysOfWeek.overlap(t.daysOfWeek))
			return false;
		return !startTime.isAfter(t.endTime) && !endTime.isAfter(t.startTime);
	}

	public String toString() {
		return "[" + semester + "|" + startTime + "|" + endTime + "|" + daysOfWeek + "]";
	}

	public String toBriefString() {
		return startTime + "->" + endTime + " on " + daysOfWeek;
	}

	//check if this period a subset of another
	public boolean within(TimePeriod p) {
		short mask1 = daysOfWeek.getByte();
		short mask2 = p.daysOfWeek.getByte();
		short subtraction = (short) (mask1 & ~mask2);
		if (subtraction != 0) {
			
			return false;
		}
		return (startTime.isAfter(p.startTime) || startTime.equals(p.startTime))
				&& (endTime.isBefore(p.endTime) || endTime.equals(p.endTime));

	}
}
