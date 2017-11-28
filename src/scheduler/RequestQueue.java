package scheduler;
import java.io.Serializable;
import java.util.ArrayList;

public class RequestQueue implements Serializable, Transient, Observable{
	static RequestQueue instance;
	static RequestQueue getInstance() {
		if(instance==null) {
			try{
				instance=(RequestQueue)Unserializer.fromFile("requestqueue.data");
			}catch(Exception e) {
				instance=new RequestQueue();
			}
		}
		return instance;
	}
	public void add(Request r) {
		requests.add(r);
		publish();
	}
	private ArrayList<Request> requests=new ArrayList<Request>();
	
	public RequestQueue() {
		initTransient();
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
		subscribers=new ArrayList();
	}

	public String toString() {
		return "Requests"+Utils.stringifyCollection(requests);
	}
	transient ArrayList<Observer> subscribers;
	@Override
	public void subscribe(Observer o) {
		subscribers.add(o);
	}
	public void publish() {
		for(Observer o:subscribers) {
			o.update();
		}
	}
	
}
