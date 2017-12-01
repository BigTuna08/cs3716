package scheduler;

import java.io.Serializable;

/**
 * A location that can be requested for an event
 * @author ben
 *
 */
public class Space implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String description;
	String name;//should be unique
	//the schedule of availabilities and events
	private SpaceSchedule schedule=new SpaceSchedule();;

	public Space(String name, String description) {
		this.description = description;
		this.name = name;
	}

	//adds an event to this space
	public void addEvent(Event e) {
		
		getSchedule().addEvent(e);
	}
	
	public String getName() {
		return name;
	}

	public String toString() {
		return name + " | " + description + " | schedule:\n" + getSchedule().toString();
	}

	public SpaceSchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(SpaceSchedule schedule) {
		this.schedule = schedule;
	}
	public boolean equals(Space s) {
		return s.getName().equals(getName())&&s.description.equals(this.description);
	}
}
