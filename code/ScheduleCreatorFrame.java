import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;

import javax.swing.*;


/*
 * Ugly code, will be fixed for final implementation 
 */
public class ScheduleCreatorFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 720;
	
	private final Color DEFAULT_BUTTON_COLOR = Color.GRAY;
	private final Color ACTIVE_BUTTON_COLOR = Color.GREEN;
	
	private JPanel actionButtonPanel;
	private JPanel roomInfoPanel;
	private JPanel controlPanel;
	
	private ScheduleManager scheduleManager;
	

	public ScheduleCreatorFrame(ScheduleManager sm) {
		scheduleManager = sm;
		setTitle("Create Schedule");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLayout(new BorderLayout());
		createPanels();
	}
	
	
	public void createPanels() {
		JPanel mainPanel = new JPanel(new GridLayout(0, 1, 0, 50));
		
		ArrayList<String> labels = new ArrayList<>(Arrays.asList("Add New Room", "Edit Rooms", "Display Rooms"));
		actionButtonPanel = buildButtonSet(labels.size(), new RoomButtonListener(),labels);
		mainPanel.add(actionButtonPanel);
		
		roomInfoPanel = new JPanel(new GridLayout(0, 1));
		mainPanel.add(roomInfoPanel);
		add(mainPanel, BorderLayout.CENTER);
		
		controlPanel = new JPanel();
		add(controlPanel, BorderLayout.SOUTH);
	}
	
	private void showAddRoom() {
		roomInfoPanel.removeAll();
		controlPanel.removeAll();
		
		JPanel addPanel = new JPanel(new GridLayout(0, 2)); //main panel which will be displayed
		JPanel pan = new JPanel(new GridLayout(9, 1));  //contains room name and available dates
		JPanel p = new JPanel(new FlowLayout()); //contains a line in pan
		p.add(new JLabel("Room Name: "));
		p.add(new JTextField(20));
		pan.add(p);
		String[] dateTimeLabels = {"Availible From ", "Availible to "};
		for (String label: dateTimeLabels) {
			p = new JPanel(new FlowLayout());
			p.add(new JLabel(label));
			p.add(newDateSelectorPanel());
			pan.add(p);
		}
		addPanel.add(pan);
		
		String[] dayLabels = {"Mon", "Tues", "Weds", "Thurs", "Fri", "Sat", "Sun"};
		pan = new JPanel(new GridLayout(10, 1)); //contains time selector for each day of week
		for (String label: dayLabels) {
			p = new JPanel(new FlowLayout());
			p.add(new JLabel(label+ ": "));
			JComboBox<Date> dateSelect = newTimeSelector();
			dateSelect.setSelectedIndex(32);
			p.add(dateSelect);
			p.add(new JLabel(" to "));
			dateSelect = newTimeSelector();
			dateSelect.setSelectedIndex(80);
			p.add(dateSelect);
			p.add(new JCheckBox("Unavailible"));
			pan.add(p);
		}
		addPanel.add(pan);
		roomInfoPanel.add(addPanel);

		JButton submit = new JButton("Add Room");
		controlPanel.add(submit);
		
		roomInfoPanel.updateUI();
		controlPanel.updateUI();
	}
	
	private void showEditRoom() {
		roomInfoPanel.removeAll();
		controlPanel.removeAll();
		
		JPanel addPanel = new JPanel(new BorderLayout());//main panel to be displayed
		addPanel.add(new JLabel("Current Rooms"),BorderLayout.NORTH);
		Collection<String> rooms = Utils.loadListFromFile("data/rooms.txt");
		JPanel p = new JPanel(new FlowLayout());
		for (String room: rooms){
			p.add(new JCheckBox(room));
		}
		addPanel.add(p, BorderLayout.CENTER);
		roomInfoPanel.add(addPanel);
		roomInfoPanel.updateUI();
		
		controlPanel.add(new JButton("Edit Rooms"));
		controlPanel.add(new JButton("Delete Rooms"));
	}

	private void createDisplayRoom() {
		roomInfoPanel.removeAll();
		controlPanel.removeAll();
		
		JPanel addPanel = new JPanel(new BorderLayout()); //main panel to be displayed
		addPanel.add(new JLabel("Current Rooms"),BorderLayout.NORTH);
		Collection<String> rooms = Utils.loadListFromFile("data/rooms.txt");
		JPanel p = new JPanel(new GridLayout(10, 0));
		for (String room: rooms){
			p.add(new JLabel(room));
		}
		addPanel.add(p, BorderLayout.CENTER);
		roomInfoPanel.add(addPanel);
		roomInfoPanel.updateUI();
	}
	
	private JPanel buildButtonSet(int cols, ActionListener listener, ArrayList<String> labels) {
		JPanel newPanel = new JPanel(new GridLayout(0, cols));
		for (String lbl: labels) {
			JButton b = new JButton(lbl);
			b.setSize(new Dimension(300, 300));
			b.setBackground(DEFAULT_BUTTON_COLOR);
			b.addActionListener(listener);
			newPanel.add(b);
		}
		return newPanel;
	}
	
	
	public JComboBox<Date> newTimeSelector() {
		Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);

        Calendar end = Calendar.getInstance();
        end.set(Calendar.HOUR_OF_DAY, 23);
        end.set(Calendar.MINUTE, 59);
        DefaultComboBoxModel<Date> model = new DefaultComboBoxModel<>();
        do {
            model.addElement((Date) calendar.getTime());
            calendar.add(Calendar.MINUTE, 15);
        } while (calendar.getTime().before(end.getTime()));

        JComboBox<Date> cb = new JComboBox<>(model);
        cb.setRenderer(new DateFormattedListCellRenderer(new SimpleDateFormat("hh:mma")));
        
        return cb;
	}

	public JPanel newDateSelectorPanel() {
		int startMonth = Calendar.SEPTEMBER;
		int startYear = 2017;
		JPanel pan = new JPanel();
		
		Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, startMonth);
        calendar.set(Calendar.YEAR, startYear);

        Calendar end = Calendar.getInstance();
        end.set(Calendar.MONTH, startMonth);
        end.set(Calendar.YEAR, startYear+1);
        DefaultComboBoxModel<Date> model = new DefaultComboBoxModel<>();
        do {
            model.addElement((Date) calendar.getTime());
            calendar.add(Calendar.MONTH, 1);
        } while (calendar.getTime().before(end.getTime()));

        JComboBox<Date> cb = new JComboBox<>(model);
        cb.setRenderer(new DateFormattedListCellRenderer(new SimpleDateFormat("MMMM")));
        
        JComboBox<Integer> cbDate = new JComboBox<>();
        for (int i = 1; i < 32; i++){
        	cbDate.addItem(i);
        }
        
        pan.add(new JLabel("Month:"));
        pan.add(cb);
        pan.add(new JLabel("Date:"));
        pan.add(cbDate);
        
        return pan;
	}
	
	public class DateFormattedListCellRenderer extends DefaultListCellRenderer {

        private DateFormat format;

        public DateFormattedListCellRenderer(DateFormat format) {
            this.format = format;
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof Date) {
                value = format.format((Date) value);
            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }

    }

	
	//fix to not rely on button text
	public class RoomButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e)
	    {
	        Object source = e.getSource();
	        if (source instanceof JButton) {
	            JButton btn = (JButton)source;
	            if (btn.getText().equals("Add New Room")) showAddRoom();
	            if (btn.getText().equals("Edit Rooms")) showEditRoom();
	            if (btn.getText().equals("Display Rooms")) createDisplayRoom();
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

	}
	
}