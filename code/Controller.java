//directs input from the user interface to the back end model
public enum Controller {
	INSTANCE;
	void addSpace(String description) {
		Space s=new Space(description);
		MasterSchedule.INSTANCE.addSpace(s);
	}
	//TODO remove space
	void addBlackOut() {
		
	}
	void addTimePeriod(Space s,TimePeriod t) {
		
	}
	
}
