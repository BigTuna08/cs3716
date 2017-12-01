package tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import scheduler.Controller;
import scheduler.EventTimeProposal;
import scheduler.MasterSchedule;
import scheduler.Observer;
import scheduler.Request;
import scheduler.RequestQueue;
/** 
 * interface component allowing the principal to approve requests
 * @author ben
 *
 */
public class ApprovalTab extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] cols = { "approved/required days", "user", "event", "desc", "room", "time and day", "action" };

	public ApprovalTab() {
		setLayout(new BorderLayout());
		JPanel grid = new JPanel(new GridLayout(0, cols.length));
		add(grid, BorderLayout.NORTH);
		JPanel outputRow = new JPanel();
		add(outputRow, BorderLayout.SOUTH);
		JLabel output = new JLabel("Welcome principal, these events are awaiting approval");
		output.setForeground(Color.RED);
		output.setFont(new java.awt.Font(null, 0, 24));
		outputRow.add(output);
		Observer updater = new Observer() {

			@Override
			public void update() {
				grid.removeAll();
				Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
				for (String s : cols) {
					JLabel l = new JLabel(s);
					l.setBorder(border);
					grid.add(l);

				}
				Border border1 = BorderFactory.createLineBorder(Color.RED, 1);
				class ProposedTimeWrapper {
					public EventTimeProposal etp;

					public ProposedTimeWrapper(EventTimeProposal etp) {
						this.etp = etp;
					}

					public String toString() {
						return etp.toBriefString();
					}
				}
				for (Request r : RequestQueue.getInstance().getRequests()) {

					JComboBox<ProposedTimeWrapper> alternativeSelector = new JComboBox<>();
					class Populator {
						void populate() {
							alternativeSelector.removeAllItems();
							for (EventTimeProposal etp : r.getRequestAlternatives()) {
								if (!etp.approved)
									alternativeSelector.addItem(new ProposedTimeWrapper(etp));
							}
						}
					}
					Populator populator = new Populator();
					populator.populate();
					JButton approve = new JButton("Approve");
					JButton delete = new JButton("Delete");
					JLabel username = new JLabel(r.getUsername()), name = new JLabel(r.getName()),
							desc = new JLabel(r.getDescription()),
							ratio = new JLabel(r.getNumberAlreadyApproved() + "\\" + r.getDaysRequested()),
							roomLabel = new JLabel(r.getLocation().getName());
					username.setBorder(border1);
					name.setBorder(border1);
					desc.setBorder(border1);
					ratio.setBorder(border1);
					// grid.setBorder(border1);
					roomLabel.setBorder(border1);
					grid.add(ratio);
					grid.add(username);
					grid.add(name);
					grid.add(desc);
					grid.add(roomLabel);
					grid.add(alternativeSelector);
					JPanel buttonPanel = new JPanel();
					buttonPanel.add(approve);
					buttonPanel.add(delete);
					grid.add(buttonPanel);

					approve.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							ProposedTimeWrapper ptw = (ProposedTimeWrapper) alternativeSelector.getSelectedItem();
							EventTimeProposal etp = ptw.etp;
							if (!Controller.INSTANCE.validate(r.getLocation(), etp.period)) {
								output.setText("that suggestion can not be approved" + Controller.lastConflict);
							}
							Controller.INSTANCE.approveTimeSlot(r, etp);
							
							populator.populate();
						}

					});
					delete.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							Controller.deleteRequest(r);
						}

					});
				}

			}
		};
		updater.update();
		RequestQueue.getInstance().subscribe(updater);
	}
}