import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/*
 * represents the schedules of all spaces at the school
 */
public class MasterSchedule implements Serializable{
	static MasterSchedule instance=null;
	static MasterSchedule getInstance(){
		if(instance==null) {
			try {
				ObjectInputStream r=new ObjectInputStream(new FileInputStream("database"));
				instance=(MasterSchedule)r.readObject();
				r.close();
			} catch (Exception e) {
				instance=new MasterSchedule();
			}
		}
		return instance;
	}
	private Collection<Space> spaces;
	
	private MasterSchedule(MasterSchedule s) {
		this.spaces=s.spaces;
	}
	private MasterSchedule() {
		spaces = new ArrayList<>();
	}
	
	/*
	 * add a new space to schedule
	 */
	public void addSpace(Space s) {
		spaces.add(s);
	}
	
	/*
	 * remove space from schedule 
	 */
	public void removeSpace(Space s) {
		spaces.remove(s);
	}
	
	/*
	 * add event to schedule
	 */
	public void addEvent(Event e) {
		
	}
	
	/*
	 * remove event from schedule
	 */
	public void removeEvent(Event e) {
		
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
}
