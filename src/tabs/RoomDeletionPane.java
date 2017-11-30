package tabs;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import scheduler.Controller;
import scheduler.MasterSchedule;
import scheduler.Observer;
import scheduler.Space;
/**
 * interface component for deleting rooms
 * @author ben
 *
 */
public class RoomDeletionPane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	RoomDeletionPane() {
		setLayout(new FlowLayout());
		Observer updater = new Observer() {
			public void update() {
				removeAll();
				for (Space s : MasterSchedule.getInstance().getSpaces()) {
					JButton b = new JButton(s.getName());
					b.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Controller.INSTANCE.deleteSpace(s);
						}
					});
					add(b);
				}
				updateUI();
			}

		};
		updater.update();
		MasterSchedule.getInstance().subscribe(updater);

	}
}