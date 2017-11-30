package scheduler;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * manages the set of school semesters
 * currently these are hard coded
 * @author ben
 *
 */
public enum SemesterManager {
	INSTANCE;
	private ArrayList<Semester> semesters = new ArrayList<Semester>();

	private SemesterManager() {
		String[] semesterNames = { "winter-18", "summer-18", "fall-18" };
		LocalDate[] sdates = { LocalDate.of(2018, 1, 2), LocalDate.of(2018, 5, 2), LocalDate.of(2018, 9, 2) };
		LocalDate[] edates = { LocalDate.of(2018, 5, 1), LocalDate.of(2018, 9, 1), LocalDate.of(2019, 1, 1) };
		for (int i = 0; i < semesterNames.length; i++) {
			getSemesters().add(new Semester(semesterNames[i], sdates[i], edates[i]));
		}
	}

	public ArrayList<Semester> getSemesters() {
		return semesters;
	}

	public void setSemesters(ArrayList<Semester> semesters) {
		this.semesters = semesters;
	}
}
