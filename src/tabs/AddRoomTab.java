package tabs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import scheduler.Controller;
/**
 * interface component for defining new rooms
 * @author ben
 *
 */
public class AddRoomTab extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddRoomTab() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel addRoomPanelRow1 = new JPanel();
		JPanel addRoomPanelRow2 = new JPanel();
		JPanel row3 = new JPanel();
		row3.add(new JLabel("delete rooms"));
		add(addRoomPanelRow1);
		add(addRoomPanelRow2);
		add(row3);
		add(new RoomDeletionPane());
		roomNameField = new JTextField(10);
		roomDescriptionField = new JTextField(20);
		roomSubmitBtn = new JButton("add room");
		roomSubmitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.INSTANCE.addSpace(roomNameField.getText(), roomDescriptionField.getText());
			}
		});
		addRoomPanelRow1.add(new JLabel("name:"));
		addRoomPanelRow1.add(roomNameField);
		addRoomPanelRow1.add(new JLabel("description:"));
		addRoomPanelRow1.add(roomDescriptionField);
		addRoomPanelRow2.add(roomSubmitBtn);

	}

	JTextField roomNameField, roomDescriptionField;
	JButton roomSubmitBtn;
}