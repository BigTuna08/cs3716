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
				JFrame frame = new ScheduleCreatorFrame(null);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}else if(args[0].equals("devel")) {
				
			}
		}
		else {
			System.out.println("usage: command r\n\topens request creator\ncommand c for schedule creator");
		}
		
	}

}