package scheduler;

import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import tabs.AddRoomTab;
import tabs.AddTimeTab;
import tabs.ApprovalTab;
import tabs.ConsoleTab;
import tabs.EditTimesTab;
import tabs.RequestRoomTab;
/**
 * The main interface to the program
 * @author ben
 *
 */
class TabbedGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//user can login as regular user or as principal
	enum Access {
		PRINCIPAL, LOW;
	}

	JPanel main = new JPanel();
	JTabbedPane tabs = new JTabbedPane();

	public TabbedGUI(Access level) {
		this(level, "", "");
	}
	public TabbedGUI(String name, String contact) {
		this(Access.LOW, name, contact);
	}
	private TabbedGUI(Access level, String name, String contact) {
		super((level == Access.PRINCIPAL) ? "Schedule Creation" : "Schedule Request");
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//write all application data to disk on exit
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				
				try {
					ObjectOutputStream objectWriter = new ObjectOutputStream(
							new FileOutputStream("masterschedule.data"));
					objectWriter.writeObject(MasterSchedule.getInstance());
					ObjectOutputStream objectWriter1 = new ObjectOutputStream(
							new FileOutputStream("requestqueue.data"));
					objectWriter1.writeObject(RequestQueue.getInstance());
					objectWriter.close();
					objectWriter1.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		this.add(tabs);
		tabs.addTab("Calendar", new tabs.CalendarTab());
		if (level == Access.PRINCIPAL) {
			tabs.addTab("Define Rooms", new AddRoomTab());
			tabs.addTab("Create Availabilities", new AddTimeTab());
			tabs.addTab("Cancel Availabilities", new EditTimesTab());
			tabs.addTab("Approve Requests", new ApprovalTab());
			tabs.addTab("Create a Request", new RequestRoomTab("principal", "principal's office"));
		} else {
			tabs.addTab("Create a Request", new RequestRoomTab(name, contact));
		}
		tabs.addTab("Textual Printout", new ConsoleTab());

		this.pack();
		this.setVisible(true);

	}

}