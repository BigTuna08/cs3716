import java.io.Serializable;

/*
 * Used to represent a space which can be booked
 */
public class Space implements Serializable{
	SpaceSchedule schedule;
	String name;
	String description;
	
	public Space(String name,String description) {
		this.description=description;
		this.name=name;
		schedule=new SpaceSchedule();
	}
	
	public String toString() {
		return name+" | "+description+" | schedule:\n"+schedule.toString();
	}
}
