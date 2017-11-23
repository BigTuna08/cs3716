import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JFrame;

/*
 * Runs the project.
 * Currently we have implemented frames for creating request and schedule
 * Run with command line argument 'r' to create request
 * Run with command line argument 's' to create Schedule
 */
public class Runner {

	public static void main(String[] args) {
		
		if (args.length > 0) {
			if (args[0].equals("r") ) {
				JFrame frame = new RequestCreatorFrame("data/rooms.txt");
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			else if (args[0].equals("c")) {
				JFrame frame = new ScheduleCreatorFrame();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}else if(args[0].equals("devel")) {//for testing
				MasterSchedule ms=MasterSchedule.getInstance();
				System.out.println(ms.toString());
				Space s=new Space("the big room","outer space");
				ms.addSpace(s);
				TimePeriod tp=new TimePeriod(LocalDate.of(2017, 1, 1),
						LocalDate.of(2017, 1, 1),
						LocalTime.of(12,30),
						LocalTime.of(22, 30));
				s.schedule.availabilities.add(tp);
				try {
					ObjectOutputStream objectWriter=new ObjectOutputStream(new FileOutputStream("database"));
					objectWriter.writeObject(ms);
					objectWriter.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		else {
			System.out.println("usage: command r\n"
					+ "\topens request creator\n"
					+ "\tcommand c for schedule creator");
		}
		
	}

}
