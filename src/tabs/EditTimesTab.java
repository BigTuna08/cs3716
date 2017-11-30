package tabs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import scheduler.Controller;
import scheduler.MasterSchedule;
import scheduler.Observer;
import scheduler.Space;
import scheduler.TimePeriod;
/**
 * interface component for removing predefined room availabilities
 * @author ben
 *
 */
public class EditTimesTab extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	RoomSelectorComponent roomSel = new RoomSelectorComponent();

	public EditTimesTab() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel top = new JPanel();
		top.add(roomSel);
		add(top);
		JPanel bottom = new JPanel();
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
		add(bottom);

		Observer updater = new Observer() {
			public void update() {
				bottom.removeAll();
				Space s = roomSel.getRoom();
				if (s == null)
					return;// no room selected
				
				for (TimePeriod t : s.getSchedule().availabilities) {
					JPanel row = new JPanel();
					row.add(new JLabel(t.toString()));
					JButton remove = new JButton("delete");
					remove.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							
							Controller.INSTANCE.removeTimePeriod(s, t);
						}
					});
					row.add(remove);
					bottom.add(row);
				}
				updateUI();
				;
			}
		};
		MasterSchedule.getInstance().subscribe(updater);
		roomSel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				

				updater.update();
			}
		});
		updater.update();
	}
}