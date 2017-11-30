import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/*
 * Ugly code, will be fixed for final implementation 
 */
public class ScheduleCreatorFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 720;
	
	private ArrayList<Space> spaceList;
	private final Color DEFAULT_BUTTON_COLOR = Color.GRAY;
	private final Color ACTIVE_BUTTON_COLOR = Color.GREEN;
	
	private JPanel actionButtonPanel;
	private JPanel roomInfoPattern;
	
	private JPanel addPanel;
	

	public ScheduleCreatorFrame() {
		spaceList = new ArrayList<>();
		setTitle("Create Schedule");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLayout(new GridLayout(0, 1));
		createPanels();
	}
	
	
	public void createPanels() {
		
		ArrayList<String> labels = new ArrayList<>(Arrays.asList("add room", "remove room", "view rooms"));
		actionButtonPanel = buildButtonSet(3, new RoomButtonListener(),labels);
		add(actionButtonPanel);
		
		
		roomInfoPattern = new JPanel(new GridLayout(0, 1));
		add(roomInfoPattern);

		
		JButton submit = new JButton("Submit request");
		add(submit);
		
	}
	
	private void createRemoveRoom() {
		roomInfoPattern.removeAll();
		addPanel = new JPanel(new GridLayout(0, 2));
		String[] dateTimeLabels = {"Room Name: "};
		JPanel pan = new JPanel(new GridLayout(0, 1));
		for (String label: dateTimeLabels) {
			JPanel p = new JPanel(new FlowLayout());
			p.add(new JLabel(label));
			p.add(new JTextField(20));
			pan.add(p);
		}
		addPanel.add(pan);
		roomInfoPattern.add(addPanel);
		roomInfoPattern.updateUI();
	}
	
	private void createAddRoom() {
		roomInfoPattern.removeAll();
		addPanel = new JPanel(new GridLayout(0, 2));
		String[] dateTimeLabels = {"Room Name: ", "Availible From date: ", "Availible to date: "};
		JPanel pan = new JPanel(new GridLayout(0, 1));
		for (String label: dateTimeLabels) {
			JPanel p = new JPanel(new FlowLayout());
			p.add(new JLabel(label));
			p.add(new JTextField(20));
			pan.add(p);
		}
		addPanel.add(pan);
		
	
		String[] dayLabels = {"Mon", "Tues", "Weds", "Thurs", "Fri", "Sat", "Sun"};
		pan = new JPanel(new GridLayout(0, 1));
		for (String label: dayLabels) {
			JPanel p = new JPanel(new FlowLayout());
			p.add(new JLabel(label+ ": "));
			p.add(new JTextField(20));
			pan.add(p);
		}
		addPanel.add(pan);
		System.out.println("ADDED");
		roomInfoPattern.add(addPanel);
		roomInfoPattern.updateUI();
	}

	private JPanel buildButtonSet(int cols, ActionListener listener, ArrayList<String> labels) {
		JPanel newPanel = new JPanel(new GridLayout(0, cols));
		for (String lbl: labels) {
			JButton b = new JButton(lbl);
			b.setBackground(DEFAULT_BUTTON_COLOR);
			b.addActionListener(listener);
			newPanel.add(b);
		}
		return newPanel;
	}
	
	
	public class RoomButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e)
	    {
	        Object source = e.getSource();
	        if (source instanceof JButton) {
	            JButton btn = (JButton)source;
	            if (btn.getText().equals("add room")) createAddRoom();
	            if (btn.getText().equals("remove room")) createRemoveRoom();
	            if (btn.getText().equals("view rooms")) createDisplayRoom();
	            if (btn.getBackground()==DEFAULT_BUTTON_COLOR) {
	            	btn.setBackground(ACTIVE_BUTTON_COLOR);
	            	for (Component c: actionButtonPanel.getComponents()) {
	            		if (c instanceof JButton && !c.equals(btn)) {
	            			c.setBackground(DEFAULT_BUTTON_COLOR);
	            		}
	            	}
	            }
	            else {
	            	btn.setBackground(DEFAULT_BUTTON_COLOR);
	            }
	        }
	    }

		private void createDisplayRoom() {
			roomInfoPattern.removeAll();
			addPanel = new JPanel(new GridLayout(0, 2));
			addPanel.add(new JLabel("No rooms to display"));
			roomInfoPattern.add(addPanel);
			roomInfoPattern.updateUI();
			
		}
	}
	
}