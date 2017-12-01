package scheduler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import de.costache.calendar.JCalendar;
import de.costache.calendar.model.CalendarEvent;
import de.costache.calendar.model.EventType;
import de.costache.calendar.util.CalendarUtil;
import tabs.RoomSelectorComponent;
/**
 * A calendar that displays availabilities and events for a particular room
 * @author ben
 *
 */
public class GraphicalCalendar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JCalendar cal;
	RoomSelectorComponent rsc = new RoomSelectorComponent();

	public GraphicalCalendar() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		cal = new JCalendar();
		cal.setPreferredSize(new Dimension(1024, 768));
		cal.getConfig().setAllDayPanelVisible(false);
		JPanel menu = new JPanel();
		menu.add(new JLabel("select a room:"));
		menu.add(rsc);
		add(menu);
		add(cal);
		rsc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for (CalendarEvent ce : cal.getCalendarEvents()) {
					cal.removeCalendarEvent(ce);
				}
				Space room = rsc.getRoom();
				if (room == null)
					return;
				

				// add availabilities
				EventType availEvent = new EventType();
				availEvent.setName("availability");
				for (TimePeriod t : room.getSchedule().availabilities) {
					LocalDate firstDay = t.semester.start;
					int firstDayNum = firstDay.getDayOfWeek().getValue();
					firstDayNum--;
					for (int i = 0; i < 7; i++) {
						if (t.daysOfWeek.getDay(Utils.days[i])) {
							// populate semester with events on this day
							LocalDate currDay = firstDay.plusDays(i - firstDayNum);
							while (currDay.isBefore(t.semester.end)) {
								Date d1 = createCalendarDate(currDay, t.startTime);
								Date d2 = createCalendarDate(currDay, t.endTime);
								CalendarEvent ce = new CalendarEvent("avail", d1, d2, availEvent);
								cal.addCalendarEvent(ce);
								currDay = currDay.plusWeeks(1);
							}
						}

					}
				}
				// add events
				EventType evtType = new EventType();
				evtType.setBackgroundColor(Color.RED);
				for (Event evt : room.getSchedule().events) {
					

					for (TimePeriod tp : evt.periods) {
						

						int day = 0;
						for (int i = 0; i < 7; i++)
							if (tp.daysOfWeek.getDay(Utils.days[i]))
								day = i;
						LocalDate firstDay = tp.semester.start;
						int firstDayNum = firstDay.getDayOfWeek().getValue();
						firstDayNum--;
						LocalDate currDay = firstDay.plusDays(day - firstDayNum);
						while (currDay.isBefore(tp.semester.end)) {
							Date d1 = createCalendarDate(currDay, tp.startTime);
							Date d2 = createCalendarDate(currDay, tp.endTime);
							CalendarEvent ce = new CalendarEvent(evt.getName(), d1, d2, evtType);
							cal.addCalendarEvent(ce);
							currDay = currDay.plusWeeks(1);
						}
					}
				}

			}

		});
		if (rsc.getRoom() != null)
			rsc.setSelectedIndex(0);// fires action
		Semester s = SemesterManager.INSTANCE.getSemesters().get(0);
		LocalDate sd = s.start;
		cal.setSelectedDay(CalendarUtil.createDate(sd.getYear(), sd.getMonth().getValue(), 1, 0, 0, 0, 0));

	}
	
	//utility function to translate time.localdate to the old date format
	public Date createCalendarDate(LocalDate d, LocalTime t) {
		return CalendarUtil.createDate(d.getYear(), d.getMonthValue(), d.getDayOfMonth(), t.getHour(), t.getMinute(), 0,
				0);
	}

}
