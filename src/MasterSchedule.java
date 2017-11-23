import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/*
 * represents the schedules of all spaces at the school
 */
public class MasterSchedule implements Serializable, Observable{
	static MasterSchedule instance=null;
	transient ArrayList<Observer> subscribers;

	static MasterSchedule getInstance(){
		if(instance==null) {
			try {
				System.out.println("loading the database...");
				ObjectInputStream r=new ObjectInputStream(new FileInputStream("masterschedule.data"));
				instance=(MasterSchedule)r.readObject();
				r.close();
			} catch (Exception e) {
				System.out.println("failed to load db, or db doesn't exist");
				instance=new MasterSchedule();
			}
			instance.initTransient();
		}
		
		return instance;
	}
	private Collection<Space> spaces;
	public void initTransient() {
		subscribers=new ArrayList();
	}

	private MasterSchedule() {
		spaces = new ArrayList<>();
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
	public Collection<Space> getSpaces() {
		return spaces;
	}
}
