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
		setRooms(Utils.loadListFromFile((roomFileName)));
	}
	
	/*
	 * Likely need requests file as arg also 
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Requires Command Line argument with location of rooms file");
		}
		System.out.println("loaded rooms from: " + args[0]);
		
		
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
