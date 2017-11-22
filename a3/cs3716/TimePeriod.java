import java.util.Date;
/*
 * Represents a time period.
 * This accommodates both single and repeating periods.
 * For repeating periods, the start and end dates will be different.
 * For periods that only occur on specific days of the weeks, the 
 * lower 7 bits of the dayOfWeek indicate which days it will occur.
 * start and end time are number of milliseconds since Jan 1st 1970,
 */
public class TimePeriod {
	char dayOfWeek;
	Date startDate;
	Date endDate;
	long startTime;
	long endTime; 
	
	/*
	 * returns event duration in milliseconds
	 */
	public long duration() {
		return endTime-startTime;
	}
	
	/*
	 * returns true if this time period overlaps
	 * given time period, false otherwise.
	 */
	public boolean overlaps(TimePeriod t) {
		return true;
	}
	
}
