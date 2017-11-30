package scheduler;

import java.io.Serializable;

//I would like to use an enum for this, but I can find no way to get syntax like
//mask=MONDAY|WEDNESDAY|FRIDAY without defining each as a global variable
/**
 * A union of weekdays in one byte
 * @author ben
 *
 */
public class DayMask implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String[] days = { "mon", "tues", "wed", "thurs", "fri", "sat", "sun" };
	short mask;

	//create union of all days or of no days if b is true or false respectively
	public DayMask(boolean b) {
		mask = (short) ((b) ? (~0) : 0);
	}
	
	//create union of days from booleans
	public DayMask(boolean m, boolean t, boolean w, boolean r, boolean f, boolean s, boolean u) {
		mask = (short) ((m ? 1 : 0) + (t ? 2 : 0) + (w ? 4 : 0) + (r ? 8 : 0) + (f ? 16 : 0) + (s ? 32 : 0)
				+ (u ? 64 : 0));
	}
	
	//check if day is in union
	public boolean getDay(String day) {
		for (int i = 0; i < days.length; i++) {
			if (day.equals(days[i])) {
				return 0 != (mask & (1 << i));
			}
		}
		throw new RuntimeException("not a day, check days list");
	}

	//place day in union where day is an integer between 0 and 6 inclusive starting at monday
	public void setDay(int day) {
		mask |= 1 << day;
	}
	
	public String toString() {
		char[] oneLetter = { 'm', 't', 'w', 'r', 'f', 's', 'u' };
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < days.length; i++) {
			if (0 != (mask & (1 << i)))
				sb.append(oneLetter[i]);
		}
		return sb.toString();
	}
	
	public short getByte() {
		return mask;
	}
	
	//checks if this day union overlaps another day union
	public boolean overlap(DayMask o) {
		return 0 != ((mask << 1) & (o.getByte() << 1));
	}
}
