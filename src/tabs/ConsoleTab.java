package tabs;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import scheduler.MasterSchedule;
import scheduler.Observer;
import scheduler.RequestQueue;
/**
 * interface component for textual output of schedule information
 * useful for debugging but also for copy pasting
 * @author ben
 *
 */
public class ConsoleTab extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextArea consoleOutput;

	public ConsoleTab() {
		setLayout(new BorderLayout());
		consoleOutput = new JTextArea();
		Observer consoleOutputUpdater = new Observer() {
			public void update() {
				consoleOutput.setText(MasterSchedule.getInstance().toString() + RequestQueue.getInstance().toString());
			}
		};
		MasterSchedule.getInstance().subscribe(consoleOutputUpdater);
		RequestQueue.getInstance().subscribe(consoleOutputUpdater);

		consoleOutputUpdater.update();

		consoleOutput.setLineWrap(true);
		add(new JScrollPane(consoleOutput));

	}
}