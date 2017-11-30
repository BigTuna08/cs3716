import java.util.ArrayList;

public class RequestManager {

	private final String SCHEDULE_FILE = "schedule.txt";
	private final String REQUEST_FILE = "requests.txt";
	
	private MasterSchedule schedule=null;
	
	private ArrayList<Request> requests;
	
	public RequestManager() {
		//initialize requests list and schedule
		//from file data
	}
	
	public void processRequests() {
		boolean finishedProcessing = false;
		//loop through requests, auto approve if no conflicts
		//if there is conflict, and one request is higher priority
		//than others, auto approve it as well
		
		//loop through conflicts, and have principle resolve them
		//(principle might not finish processing all
		
		if (!finishedProcessing) {
			//save unresolved requests to REQUEST_FILE
		}
		
		//update schedule file with approved requests
		
	}
	
	
	public void decideRequests() {
		//
	}

	
	
}
