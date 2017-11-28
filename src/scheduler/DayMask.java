package scheduler;
import java.io.Serializable;

//I would like to use an enum for this, but I can find no way to get syntax like
//mask=MONDAY|WEDNESDAY|FRIDAY without defining each as a global variable
public class DayMask implements Serializable{
	short mask;
	static String[] days= {"mon","tues","wed","thurs","fri","sat","sun"};
	public DayMask(boolean m,boolean t,boolean w,boolean r,boolean f,boolean s,boolean u) {
		mask=(short)((m?1:0)+(t?2:0)+(w?4:0)+(r?8:0)+(f?16:0)+(s?32:0)+(u?64:0));
	}
	//get all true or all false day mask
	public DayMask(boolean b) {
		mask=(short)((b)?(~0):0);
	}
	public boolean getDay(String day) {
		for(int i=1;i<=days.length;i++) {
			if(day.equals(days[i])) {
				return 0!=(mask&(1<<i));
			}
		}
		throw new RuntimeException("not a day, check days list");
	}
//	public void setDay(String day) {
//		for(int i=1;i<=days.length;i++) {
//			if(day.equals(days[i])) {
//				return 0!=(mask&(1<<i));
//			}
//		}
//		throw new RuntimeException("not a day, check days list");
//	}
	//set day flag where day is an int between 0 and 6 inclusive
	public void setDay(int day) {
		mask|=1<<day;
	}
	public String toString() {
		char[] oneLetter= {'m','t','w','r','f','s','u'};
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<days.length;i++) {
			if(0!=(mask&(1<<i)))
				sb.append(oneLetter[i]);
		}
		return sb.toString();
	}
}


