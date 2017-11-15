import javax.swing.JFrame;

/*
 * Runs the project.
 * Currently we have implemented frames for creating request and schedule
 * Run with command line argument 'r' to create request
 * Run with command line argument 's' to create Schedule
 */
public class Runner {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Requires Command Line Argument!!\n'r' for request creator\n's' for schedule creator");
			System.exit(0);
		}
		
		if (args[0].equals("r") ) {
			JFrame frame = new RequestCreatorFrame("data/rooms.txt");
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		else if (args[0].equals("c")) {
			JFrame frame = new ScheduleCreatorFrame();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		else {
			System.out.println("Invalid command line input!!! Use: \n'r' for request creator\n's' for schedule creator");
		}
		
	}

}
