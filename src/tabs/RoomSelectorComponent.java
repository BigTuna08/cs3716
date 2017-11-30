package tabs;

import javax.swing.JComboBox;
import scheduler.MasterSchedule;
import scheduler.Observer;
import scheduler.Space;
import tabs.RoomSelectorComponent.SpaceWrapper;
/**
 * interface component to select a room
 * @author ben
 *
 */
public class RoomSelectorComponent extends JComboBox<SpaceWrapper> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static class SpaceWrapper {
		public Space s;

		public SpaceWrapper(Space sp) {
			s = sp;
		}

		public String toString() {
			return s.getName();
		}
	};

	public RoomSelectorComponent() {
		super();
		Observer roomSelectorUpdater = new Observer() {
			public void update() {
				Space x = getRoom();
				removeAllItems();
				for (Space s : MasterSchedule.getInstance().getSpaces()) {
					SpaceWrapper o = new SpaceWrapper(s);
					addItem(o);
					if (x == s) {// restore previous selection
						setSelectedItem(o);
					}
				}
			}
		};
		roomSelectorUpdater.update();

		MasterSchedule.getInstance().subscribe(roomSelectorUpdater);
	}

	public Space getRoom() {
		Object o = getSelectedItem();
		if (o != null) {
			return ((SpaceWrapper) getSelectedItem()).s;
		} else {
			return null;
		}
	}
}