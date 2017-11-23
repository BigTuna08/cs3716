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
	LocalDate startDate;//inclusive
	LocalDate endDate;//non inclusive?
	LocalTime startTime;
	LocalTime endTime; 
	
	public TimePeriod(LocalDate of, LocalDate of2, LocalTime of3, LocalTime of4) {
		startDate=of;
		endDate=of2;
		startTime=of3;
		endTime=of4;
		daysOfWeek=new DayMask();
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
		return !startTime.isAfter(t.endTime) && !endTime.isAfter(t.startTime);
	}
	
}
