package scheduler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The set of requests waiting for approval
 * @author ben
 *
 */
public class RequestQueue implements Serializable, Transient, Observable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static RequestQueue instance;//singleton

	public static RequestQueue getInstance() {
		if (instance == null) {
			try {
				instance = (RequestQueue) Unserializer.fromFile("requestqueue.data");
			} catch (Exception e) {
				instance = new RequestQueue();
			}
		}
		return instance;
	}

	//waiting requests
	private ArrayList<Request> requests = new ArrayList<Request>();
	transient ArrayList<Observer> subscribers;

	public RequestQueue() {
		initTransient();
	}

	//add a request
	public void add(Request r) {
		requests.add(r);
		publish();
	}

	public ArrayList<Request> getRequests() {
		return requests;
	}

	@Override
	public void initTransient() {
		subscribers = new ArrayList<Observer>();
	}

	//notify observers
	public void publish() {
		for (Observer o : subscribers) {
			o.update();
		}
	}

	//remove a request (possibly because it was made into an event)
	public void removeRequest(Request r) {
		requests.remove(r);
		publish();
	}

	@Override
	public void subscribe(Observer o) {
		subscribers.add(o);
	}

	public String toString() {
		return "Requests" + Utils.stringifyCollection(requests);
	}

	//create and store a full event
	public void approveEvent(Request r, Collection<TimePeriod> period) {
		Event e = new Event(r, period);
		r.getLocation().addEvent(e);
		RequestQueue.getInstance().removeRequest(r);
	}

	//mark one alternative time for an request as approved
	public boolean approveTimeSlot(Request r, EventTimeProposal time) {
		time.approved = true;
		
		r.setNumberAlreadyApproved(r.getNumberAlreadyApproved() + 1);
		if (r.getNumberAlreadyApproved() == r.getDaysRequested()) {
			
			ArrayList<TimePeriod> periods = new ArrayList<>();
			
			for (EventTimeProposal etp : r.getRequestAlternatives()) {
				
				if (etp.approved) {
					periods.add(etp.period);
				}
			}
			Event e = new Event(r, periods);
			
			r.getLocation().addEvent(e);
			RequestQueue.getInstance().removeRequest(r);
		}
		publish();
		MasterSchedule.getInstance().updateAll();
		return true;

	}

}
