package scheduler;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/*
 * represents the schedules of all spaces at the school
 */
public class MasterSchedule implements Serializable, Observable, Transient{
	static MasterSchedule instance=null;
	transient ArrayList<Observer> subscribers;

	static MasterSchedule getInstance(){
		if(instance==null) {
			try{
				instance=(MasterSchedule)Unserializer.fromFile("masterschedule.data");
			}catch(Exception e) {
				instance=new MasterSchedule();
			}
		}
		return instance;
	}
	private Collection<Space> spaces;
	public void initTransient() {
		subscribers=new ArrayList();
	}

	private MasterSchedule() {
		spaces = new ArrayList<>();
		initTransient();
	}
	
	/*
	 * add a new space to schedule
	 */
	public void addSpace(Space s) {
		
		spaces.add(s);
		updateAll();
	}
	
	/*
	 * remove space from schedule 
	 */
	public void removeSpace(Space s) {
		spaces.remove(s);
		updateAll();

	}
	
	/*
	 * add event to schedule
	 */
	public void addEvent(Event e) {
		updateAll();

	}
	
	/*
	 * remove event from schedule
	 */
	public void removeEvent(Event e) {
		updateAll();

	}
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("spaces:\n");
		for(Space s:spaces) {
			sb.append(s.toString()+"\n");
		}
		sb.append("\n");
		return sb.toString();
	}
	@Override
	public void subscribe(Observer o) {
		
		if(!subscribers.contains(o)) {//idempotent
			subscribers.add(o);
		}
	}
	private void updateAll() {
		for(Observer o:subscribers) {
			o.update();
		}
	}
	public void addTime(Space s, TimePeriod tp) {
		s.schedule.addTime(tp);
		updateAll();
	}
	public void removeTime(Space s, TimePeriod tp) {
		s.schedule.removeTime(tp);
		updateAll();

	}
	public Collection<Space> getSpaces() {
		return spaces;
	}

	public void addBlackout(Space s, TimePeriod tp) {
		s.schedule.addBlackout(tp);
		updateAll();
	}

	public void deleteSpace(Space s) {
		//TODO? make sure this doesn't violate a billion constraints
		spaces.remove(s);
		updateAll();
	}
}
