import java.io.Serializable;
import java.util.ArrayList;

public class RequestQueue implements Serializable, Transient{
	static RequestQueue instance;
	static RequestQueue getInstance() {
		if(instance==null) {
			try{
				instance=(RequestQueue)Unserializer.fromFile("requests.data");
			}catch(Exception e) {
				instance=new RequestQueue();
			}
		}
		return instance;
	}
	
	private ArrayList<Request> requests;
	
	public RequestQueue() {
		
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

	@Override
	public void initTransient() {
		// TODO Auto-generated method stub
		
	}

	
	
}
