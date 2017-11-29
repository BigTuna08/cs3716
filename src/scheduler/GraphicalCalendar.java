
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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
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
public class GraphicalCalendar extends JPanel {

	private JCalendar cal;
	GraphicalCalendar(){
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

		cal = new JCalendar();
		cal.setPreferredSize(new Dimension(1024, 768));
		cal.getConfig().setAllDayPanelVisible(false);
		
		add(cal);
	

	}

	private void initData() {
/*
		final EventType type1 = new EventType();

		final EventType type2 = new EventType();
		type2.setBackgroundColor(new Color(255, 103, 0, 128));

		final EventType type3 = new EventType();
		type3.setBackgroundColor(new Color(165, 103, 230, 128));

		final EventType[] types = new EventType[3];
		types[0] = type1;
		types[1] = type2;
		types[2] = type3;
		Random r=new Random();
		CalendarEvent calendarEvent;
		for (int i = 0; i < 100000; i++) {
			int hour = r.nextInt(19);
			hour = hour > 17 ? 17 : hour;
			hour = hour < 8 ? 8 : hour;
			final int min = r.nextInt(59);
			final int day = r.nextInt(28);
			final int month = r.nextInt(11);
			final int year = 2010 + r.nextInt(6);
			final Date start = CalendarUtil.createDate(year, month, day, hour, min, 0, 0);
			final Date end = CalendarUtil.createDate(year, month, day, hour + 1 + r.nextInt(4), r.nextInt(59), 0, 0);
			calendarEvent = new CalendarEvent("an event", start, end);
			calendarEvent.setType(types[r.nextInt(3)]);
			calendarEvent.setAllDay(i % 2 == 0);
			jCalendar.addCalendarEvent(calendarEvent);
		}

		Date start = CalendarUtil.createDate(2013, 1, 31, 12, 45, 0, 0);
		Date end = CalendarUtil.createDate(2013, 1, 31, 16, 35, 0, 0);
		calendarEvent = new CalendarEvent("Overlapping", start, end);
		jCalendar.addCalendarEvent(calendarEvent);

		start = CalendarUtil.createDate(2013, 1, 31, 8, 45, 0, 0);
		end = CalendarUtil.createDate(2013, 1, 31, 15, 35, 0, 0);
		calendarEvent = new CalendarEvent("Overlapping 2", start, end);
		jCalendar.addCalendarEvent(calendarEvent);
		*/
	}




}
