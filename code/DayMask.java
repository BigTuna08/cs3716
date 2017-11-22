

//I would like to use an enum for this, but I can find no way to get syntax like
//mask=MONDAY|WEDNESDAY|FRIDAY without defining each as a global variable
public class DayMask {
	short mask;
	static String[] days= {"mon","tues","wed","thurs","fri","sat","sun"};
	public DayMask(boolean m,boolean t,boolean w,boolean r,boolean f,boolean s,boolean u) {
		mask=(short)((m?1:0)+(t?2:0)+(w?4:0)+(r?8:0)+(f?16:0)+(s?32:0)+(u?64:0));
	}
	public boolean getDay(String day) {
		for(int i=1;i<=days.length;i++) {
			if(day.equals(days[i])) {
				return 0!=(mask&(1<<i));
			}
		}
		throw new RuntimeException("not a day, check days list");
	}
}


