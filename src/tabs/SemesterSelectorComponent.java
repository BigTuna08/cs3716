package tabs;

import javax.swing.JComboBox;

import scheduler.MasterSchedule;
import scheduler.Observer;
import scheduler.Semester;
import scheduler.SemesterManager;

/**
 * interface component to select a semester
 * @author ben
 *
 */
class SemesterSelectorComponent extends JComboBox<Semester> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SemesterSelectorComponent() {
		super();
		Observer roomSelectorUpdater = new Observer() {
			public void update() {
				Semester x = getSemester();
				removeAllItems();
				for (Semester s : SemesterManager.INSTANCE.getSemesters()) {
					addItem(s);
					if (x == s) {// restore previous selection
						setSelectedItem(s);
					}
				}
			}
		};
		roomSelectorUpdater.update();

		MasterSchedule.getInstance().subscribe(roomSelectorUpdater);
	}

	Semester getSemester() {
		return (Semester) getSelectedItem();
	}
}