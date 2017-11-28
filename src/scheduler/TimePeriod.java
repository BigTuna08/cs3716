package scheduler;
import java.io.Serializable;
import java.time.*;
/*
 * Represents a time period.
 * This accommodates both single and repeating periods.
 * For repeating periods, the start and end dates will be different.
 * For periods that only occur on  specific days of the weeks, the 
 * lower 7 bits of the dayOfWeek indicate which days it will occur.
 * start and end time are number of milliseconds since Jan 1st 1970,
 */
public class TimePeriod implements Serializable{
	DayMask daysOfWeek;
	enum Period{
		ONCE,WEEKLY,MONTHLY,ANNUALLY
		 
	}
	Period period;
	LocalDate startDate;//inclusive
	LocalDate endDate;//non inclusive?
	LocalTime startTime;
	LocalTime endTime; 
	
	public TimePeriod(LocalDate sd, LocalDate ed, LocalTime st, LocalTime et, DayMask days, Period p) {
		startDate=sd;
		endDate=ed;
		startTime=st;
		endTime=et;
		daysOfWeek=days;
		period=p;
	}

	/*
	 * returns event duration in milliseconds
	 */
	public long duration() {
		return Duration.between(startTime, endTime).getSeconds();
		
	}
	
	/*
	 * returns true if this time period overlaps
	 * given time period, false otherwise.
	 */
	public boolean overlaps(TimePeriod t) {
		//TODO complete this
		return !startTime.isAfter(t.endTime) && !endTime.isAfter(t.startTime);
	}
	public String toString() {
		return "["+startDate+" "+endDate+" "+startTime+" "+endTime+" "+daysOfWeek+"]";
	}
}
