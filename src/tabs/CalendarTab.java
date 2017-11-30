package tabs;

import javax.swing.JPanel;

import scheduler.GraphicalCalendar;
/**
 * interface component holding the GraphicalCalendar
 * @author ben
 *
 */
public class CalendarTab extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GraphicalCalendar cal = new GraphicalCalendar();

	public CalendarTab() {
		add(cal);
	}
}