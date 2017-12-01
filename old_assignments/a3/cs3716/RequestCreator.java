import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import javax.swing.JFrame;

/*
 * Used by the Request creator frame to create and manage
 * request objects from user input into the GUI
 */
public class RequestCreator {

	private Collection<String> rooms;
	
	public RequestCreator(String roomFileName) {
		setRooms(loadRoomList(roomFileName));
	}
	
	/*
	 * Likely need requests file as arg also 
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			
		}
		
		
		
	}
	
	/*
	 * loads list of spaces available for booking from a text file
	 * and returns collection containing their names
	 */
	private static Collection<String> loadRoomList(String roomFileLocation) {
		ArrayList<String> rooms = new ArrayList<>();
		
		try {
			Scanner in = new Scanner(new File(roomFileLocation));
			
			while (in.hasNextLine()) {
				rooms.add(in.nextLine());
			}
			
			in.close();
			
		} catch (FileNotFoundException e) {
			
		}
		return rooms;
	}
	
	/*
	 * Creates a request object containing request information
	 */
	public static Request createRequest(TimePeriod requestedPeriod, String requestorID, Collection<String> spaceIDs, boolean highPriority) {
		return null;
	}
	
	/*
	 * Send request to requests file where it will be processed by principle
	 */
	public static void submitRequest(Request r) {
		
	}
	
	/*
	 * Rescinds a request that is awaiting approval
	 */
	public static void rescindRequest(Request r) {
		
	}

	public Collection<String> getRooms() {
		return rooms;
	}

	public void setRooms(Collection<String> rooms) {
		this.rooms = rooms;
	}

}
