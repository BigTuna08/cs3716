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
public class Application  {
	public static void main(String[] args) {
		if (args.length > 0) {
			if (args[0].equals("r") ) {
				JFrame frame = new RequestCreatorFrame("data/rooms.txt");
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}else if (args[0].equals("c")) {
				JFrame frame = new ScheduleCreatorFrame();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}else if(args[0].equals("devel")) {//for testing
				new TestingFrame();
			}
		}else {
			System.out.println("usage: command r\n"
					+ "\topens request creator\n"
					+ "\tcommand c for schedule creator");
		}
		
		
		
	}

}
