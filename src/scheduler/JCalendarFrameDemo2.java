package scheduler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import de.costache.calendar.JCalendar;
import de.costache.calendar.events.IntervalChangedEvent;
import de.costache.calendar.events.IntervalChangedListener;
import de.costache.calendar.events.IntervalSelectionEvent;
import de.costache.calendar.events.IntervalSelectionListener;
import de.costache.calendar.events.ModelChangedEvent;
import de.costache.calendar.events.ModelChangedListener;
import de.costache.calendar.events.SelectionChangedEvent;
import de.costache.calendar.events.SelectionChangedListener;
import de.costache.calendar.model.CalendarEvent;
import de.costache.calendar.model.EventType;
import de.costache.calendar.ui.strategy.DisplayStrategy.Type;
import de.costache.calendar.util.CalendarUtil;

/**
 * @author costache
 * 
 */
public class JCalendarFrameDemo2 extends JFrame {

	/**
	 * 
	 */




	private JCalendar jCalendar;
	private JSplitPane content;




	public JCalendarFrameDemo2() {

		initGui();


	}

	private void initGui() {



		jCalendar = new JCalendar();
		jCalendar.setPreferredSize(new Dimension(1024, 768));
		jCalendar.getConfig().setAllDayPanelVisible(false);
		//jCalendar.getConfig().setHolidays(Arrays.asList(new Date()));

		//content = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		//content.add(jCalendar);
		JTabbedPane jtp=new JTabbedPane();
		jtp.add("empty", new JLabel(""));
		jtp.add("empty",jCalendar);
		add(jtp);
		pack();
		//this.getContentPane().add(content, BorderLayout.CENTER);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.pack();

	}



	public static void main(final String[] args) throws MalformedObjectNameException, NullPointerException,
			InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
		Locale.setDefault(Locale.ENGLISH);
		final JCalendarFrameDemo2 frameTest = new JCalendarFrameDemo2();
		frameTest.setVisible(true);
	}
}
