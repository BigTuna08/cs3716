//directs input from the user interface to the back end model
public enum Controller{
	INSTANCE;
	MasterSchedule ms=MasterSchedule.getInstance();
	void addSpace(String name,String description) {
		Space s=new Space(name,description);
		MasterSchedule.getInstance().addSpace(s);
	}
	void addBlackOut(Space s, TimePeriod tp) {
		s.schedule.blackouts.add(tp);
	}
	void addTimePeriod(Space s,TimePeriod tp) {
		ms.addTime(s,tp);
	}
	
}
