package scheduler;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/*
 * Ugly code, will be fixed for final implementation 
 */
public class RequestCreatorFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private RequestCreator requestCreator;
	
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 720;
	
	private Collection<String> rooms;
	private final Color DEFAULT_BUTTON_COLOR = Color.GRAY;
	private final Color ACTIVE_BUTTON_COLOR = Color.GREEN;
	
	
	private JPanel roomSelectButtonPanel;
	private JPanel dateTimeInfoPanel;
	
	
	public RequestCreatorFrame(String roomFileName) {
		requestCreator = new RequestCreator(roomFileName);
		rooms = requestCreator.getRooms();
		setTitle("Request Room");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLayout(new GridLayout(0, 1));
		createPanels();
	}
	
	
	public void createPanels() {
		
		roomSelectButtonPanel = buildButtonSet(3, new RoomButtonListener(),rooms);
		add(roomSelectButtonPanel);
		
		
		JPanel eventRadioButtons = new JPanel();
		String[] eventOptions = {"One Time", "Reoccuring", "Special"};
		ButtonGroup group = new ButtonGroup();
		for (String event: eventOptions) {
			JRadioButton b = new JRadioButton(event);
			group.add(b);
			eventRadioButtons.add(b);
		}
		add(eventRadioButtons);
		
		
		dateTimeInfoPanel = new JPanel(new GridLayout(0, 1));
		String[] dateTimeLabels = {"Start time: ", "End time: ", "(Start) Date: "};
		for (String label: dateTimeLabels) {
			JPanel p = new JPanel(new FlowLayout());
			p.add(new JLabel(label));
			p.add(new JTextField(20));
			dateTimeInfoPanel.add(p);
		}
		JPanel p = new JPanel(new FlowLayout());
		JCheckBox diffTimes = new JCheckBox("Accept bookings for other times");
		p.add(diffTimes);
		JCheckBox diffDays = new JCheckBox("Accept bookings for other days");
		p.add(diffDays);
		JCheckBox diffRooms = new JCheckBox("Accept bookings for other rooms");
		p.add(diffRooms);
		dateTimeInfoPanel.add(p);
		add(dateTimeInfoPanel);
		
		p = new JPanel(new FlowLayout());
		p.add(new JLabel("Comments: "));
		p.add(new JTextField(20));
		add(p);
		
		JButton submit = new JButton("Submit request");
		add(submit);
		
	}
	

	private JPanel buildButtonSet(int cols, ActionListener listener, Collection<String> labels) {
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
	            if (btn.getBackground()==DEFAULT_BUTTON_COLOR) btn.setBackground(ACTIVE_BUTTON_COLOR);
	            else btn.setBackground(DEFAULT_BUTTON_COLOR);
	        }
	    }
	}
	
}