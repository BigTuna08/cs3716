import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import javax.swing.JFrame;

public class RequestApprover {

	Collection<Request> requests;
	
	public RequestApprover(String requestFileLocation) {
		requests = loadRequests(requestFileLocation);
	}
	
	/*
	 * Approves all requests which have no conflicts
	 */
	public static void autoApproveRequests() {
		
	}
	
	/*
	 * returns true if more requests need approval,
	 * false otherwise
	 */
	public boolean hasNextRequest() {
		return true;
	}
	
	/*
	 * removes and returns next request from the 
	 * collection of requests
	 */
	public Request nextRequest() {
		return null;
	}
	
	/*
	 * loads submitted requests from text file
	 */
	private static Collection<Request> loadRequests(String requestFileLocation) {
		ArrayList<Request> requests = new ArrayList<>();
		
		try {
			Scanner in = new Scanner(new File(requestFileLocation));
			
			while (in.hasNextLine()) {
				// create request object from file
			}
			
			in.close();
			
		} catch (FileNotFoundException e) {
			
		}
		return requests;
	}
	
	/*
	 * Notifies stakeholder their request was approved
	 * and sends info to schedule manager to create event
	 */
	public void approveRequest(Request r) {
		
	}
	
	/*
	 * Notifies stakeholer their request was denied
	 */
	public void denyRequest(Request r) {
		
	}
	
	

}
