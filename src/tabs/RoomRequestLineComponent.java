package tabs;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import scheduler.Utils;
/**
 * interface component for a defining a single request alternative time proposal
 * @author ben
 *
 */
public class RoomRequestLineComponent extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// RoomSelectorComponent rsc=new RoomSelectorComponent();
	JSpinner shr, ehr, smin, emin;
	// JDatePicker sdate,edate;
	// JComboBox PeriodSel=new JComboBox();
	JComboBox<String> daySelector = new JComboBox<>();
	JCheckBox ignoreMe = new JCheckBox();

	RoomRequestLineComponent(int i) {
		// for(Period P:TimePeriod.Period.values()) {
		// PeriodSel.addItem(P);
		// }
		// sdate=new JDatePicker(new Date(118,1,1));
		// edate=new JDatePicker(new Date(119,1,1));

		for (String d : Utils.days) {
			daySelector.addItem(d);
		}
		if (i != 0)
			ignoreMe.setSelected(true);
		add(new JLabel("disregard this entry:"));
		add(ignoreMe);
		shr = new JSpinner(new SpinnerNumberModel(9, 0, 23, 1));
		ehr = new JSpinner(new SpinnerNumberModel(10, 0, 23, 1));
		smin = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
		emin = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));

		add(new JLabel("#" + i + ":"));

		add(daySelector);
		// add(new JLabel("in room:"));
		// add(rsc);

		add(new JLabel("from (HH-MM)"));
		add(shr);
		add(smin);
		add(new JLabel("to"));
		add(ehr);
		add(emin);

	}

	public boolean isActive() {
		return !ignoreMe.isSelected();
	}
}