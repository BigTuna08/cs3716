package scheduler;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A school year semester
 * @author ben
 *
 */
public class Semester implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	//first day of semester
	LocalDate start;
	//last day of semester
	LocalDate end;

	public Semester(String name, LocalDate sdate, LocalDate edate) {
		super();
		this.name = name;
		this.start = sdate;
		this.end = edate;
	}

	public boolean equals(Semester o) {
		return o.name.equals(name);
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}
}
