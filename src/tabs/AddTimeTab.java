package tabs;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import scheduler.Controller;
import scheduler.DayMask;
import scheduler.Semester;
import scheduler.Space;
import scheduler.TimePeriod;
import scheduler.Utils;
/**
 * interface component for defining room availabilities
 * @author ben
 *
 */
public class AddTimeTab extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JCheckBox[] dayCheckBoxes = new JCheckBox[7];
	// JSpinner syear,smonth,sday,eyear,emonth,eday,
	JSpinner shr, smin, ehr, emin;
	// JDatePicker start,end;
	SemesterSelectorComponent ssc = new SemesterSelectorComponent();
	RoomSelectorComponent roomSel = new RoomSelectorComponent();

	ButtonGroup roomActionGrp;

	public AddTimeTab() {

		setLayout(new GridLayout(0, 1));
		JPanel roomSelectorPanel = new JPanel();
		add(roomSelectorPanel, BorderLayout.NORTH);

		roomSelectorPanel.add(new JLabel("Room:"));
		roomSelectorPanel.add(roomSel);
		roomSelectorPanel.add(new JLabel("Action:"));

		JRadioButton roomActionAddAvail = new JRadioButton("add availability");
		roomActionAddAvail.setSelected(true);
		JRadioButton roomActionBlackOut = new JRadioButton("blackout(not implemented)");
		roomActionGrp = new ButtonGroup();

		roomActionGrp.add(roomActionAddAvail);
		roomActionGrp.add(roomActionBlackOut);
		roomSelectorPanel.add(roomActionAddAvail);
		roomSelectorPanel.add(roomActionBlackOut);

		JPanel dayCheckPanel = new JPanel();
		add(dayCheckPanel);
		for (int i = 0; i < 7; i++) {
			JLabel l = new JLabel(Utils.days[i]);
			dayCheckPanel.add(l);
			dayCheckBoxes[i] = new JCheckBox();
			dayCheckBoxes[i].setSelected(true);
			dayCheckPanel.add(dayCheckBoxes[i]);
		}

		/*
		 * syear=new JSpinner(new SpinnerNumberModel(2017,1900,3000,1)); eyear=new
		 * JSpinner(new SpinnerNumberModel(2018,1900,3000,1)); smonth=new JSpinner(new
		 * SpinnerNumberModel(1,1,12,1)); emonth=new JSpinner(new
		 * SpinnerNumberModel(1,1,12,1)); sday=new JSpinner(new
		 * SpinnerNumberModel(1,1,31,1)); eday=new JSpinner(new
		 * SpinnerNumberModel(1,1,31,1));
		 */
		shr = new JSpinner(new SpinnerNumberModel(9, 0, 23, 1));
		ehr = new JSpinner(new SpinnerNumberModel(10, 0, 23, 1));
		smin = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
		emin = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));

		JPanel dtPanel1 = new JPanel();
		JPanel dtPanel2 = new JPanel();
		JPanel dtPanel3 = new JPanel();
		JPanel dtPanel4 = new JPanel();
		add(dtPanel1, BorderLayout.NORTH);
		add(dtPanel2, BorderLayout.NORTH);
		add(dtPanel3, BorderLayout.NORTH);
		add(dtPanel4, BorderLayout.NORTH);

		dtPanel1.add(new JLabel("Semester:"));
		dtPanel1.add(ssc, BorderLayout.NORTH);

		dtPanel3.add(new JLabel("time range (HH-MM 24 hr clock)"));
		dtPanel3.add(shr);
		dtPanel3.add(smin);
		dtPanel3.add(new JLabel(" to "));
		dtPanel3.add(ehr);
		dtPanel3.add(emin);

		JButton roomTimeBtn = new JButton("enter period");
		roomTimeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalTime stime = LocalTime.of((int) shr.getValue(), (int) smin.getValue());
				LocalTime etime = LocalTime.of((int) ehr.getValue(), (int) emin.getValue());
				DayMask dm = new DayMask(false);
				for (int i = 0; i < 7; i++) {
					if (dayCheckBoxes[i].isSelected()) {
						dm.setDay(i);
					}
				}
				Semester sem = ssc.getSemester();
				TimePeriod tp = new TimePeriod(sem, stime, etime, dm);
				Space s = roomSel.getRoom();
				if (roomActionAddAvail.isSelected()) {
					Controller.INSTANCE.addTimePeriod(s, tp);
				} else if (roomActionBlackOut.isSelected()) {
					Controller.INSTANCE.addBlackOut(s, tp);
				}
			}
		});
		dtPanel4.add(roomTimeBtn);
	}
}