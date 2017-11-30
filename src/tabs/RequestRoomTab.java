package tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import scheduler.Controller;
import scheduler.DayMask;
import scheduler.EventTimeProposal;
import scheduler.Request;
import scheduler.RequestQueue;
import scheduler.Space;
import scheduler.TimePeriod;

/**
 * interface component for creating event requests
 * @author ben
 *
 */
public class RequestRoomTab extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<RoomRequestLineComponent> priorities = new ArrayList<>();
	JTextField name = new JTextField(10);
	JTextField description = new JTextField(20);
	SemesterSelectorComponent ssc = new SemesterSelectorComponent();
	// JDatePicker fromDate;
	// JDatePicker toDate;
	JSpinner timesPerWeek = new JSpinner(new SpinnerNumberModel(1, 1, 7, 1));
	RoomSelectorComponent rsc = new RoomSelectorComponent();

	public RequestRoomTab(String username, String contact) {
		setLayout(new BorderLayout());
		JPanel northPanel = new JPanel();
		northPanel.add(new JLabel("event name:"));
		northPanel.add(name);
		northPanel.add(new JLabel("description:"));
		northPanel.add(description);
		northPanel.add(new JLabel("times per week"));
		northPanel.add(timesPerWeek);
		northPanel.add(new JLabel("Room:"));
		northPanel.add(rsc);
		northPanel.add(new JLabel("Semester:"));
		northPanel.add(ssc);

		add(northPanel, BorderLayout.NORTH);

		JPanel inner = new JPanel();
		inner.setLayout(new GridLayout(10, 1));
		class RowAdder implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i = priorities.size();
				RoomRequestLineComponent rrlc = new RoomRequestLineComponent(i);
				priorities.add(rrlc);
				inner.add(rrlc, i);
				// refit();
				// updateUI();
			}

		}
		JPanel lower = new JPanel();
		lower.setLayout(new BoxLayout(lower, BoxLayout.Y_AXIS));
		JPanel submitBtnRow = new JPanel();
		lower.add(submitBtnRow);
		add(new JScrollPane(inner), BorderLayout.CENTER);
		add(lower, BorderLayout.SOUTH);
		JPanel outputRow = new JPanel();
		lower.add(outputRow);
		JLabel output = new JLabel("please define your request");
		output.setForeground(Color.RED);
		output.setFont(new java.awt.Font(null, 0, 24));
		outputRow.add(output);
		JButton addPriority = new JButton("define more alternatives");
		JButton submitbtn = new JButton("submit");

		submitBtnRow.add(addPriority);
		submitBtnRow.add(submitbtn);
		submitbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String evtName = name.getText();
				String desc = description.getText();
				ArrayList<EventTimeProposal> proposals = new ArrayList<>(priorities.size());
				Space room = rsc.getRoom();

				for (int i = 0; i < priorities.size(); i++) {
					RoomRequestLineComponent rrlc = priorities.get(i);
					if (!rrlc.isActive()) {
						continue;
					}
					LocalTime startTime = LocalTime.of((int) rrlc.shr.getValue(), (int) rrlc.smin.getValue());
					LocalTime endTime = LocalTime.of((int) rrlc.ehr.getValue(), (int) rrlc.emin.getValue());

					DayMask days = new DayMask(false);
					days.setDay(rrlc.daySelector.getSelectedIndex());
					TimePeriod tp = new TimePeriod(ssc.getSemester(), startTime, endTime, days);

					if (!Controller.INSTANCE.validate(room, tp)) {
						output.setText(
								"alternative #" + i + " is invalid, please see schedule " + Controller.lastConflict);
						return;
					}
					EventTimeProposal etsp = new EventTimeProposal(tp);
					proposals.add(etsp);
				}
				int daysRequested = (int) timesPerWeek.getModel().getValue();

				if (proposals.size() < daysRequested) {
					output.setText("You must specify at least " + daysRequested + " possibilities");
					return;
				}

				Request r = new Request(evtName, desc, room, username, contact, daysRequested, proposals);
				
				RequestQueue.getInstance().add(r);
				output.setText("request submitted");
			}

		});
		RowAdder ra = new RowAdder();
		addPriority.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ra.actionPerformed(null);
			}

		});

		for (int i = 0; i < 5; i++)
			ra.actionPerformed(null);
	}
}