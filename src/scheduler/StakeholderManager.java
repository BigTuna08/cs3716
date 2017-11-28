package scheduler;
/*
 * All stakeholder data is stored in a text file
 * This class is responsible for controlling that file
 * 
 * May change methods to static for final implementation
 */
public class StakeholderManager {

	private String stakeholderInfoFileLoc;

	public StakeholderManager(String stakeholderInfoFileLoc) {
		this.stakeholderInfoFileLoc = stakeholderInfoFileLoc;
	}
	
	/*
	 * update stakeholder file to contain info for new stakeholder
	 */
	public void createNewStakeholder(String id, double timeAllotment) {//also need dates they get preferred treatment
		
	}
	
	/*
	 * removes stakeholder info from stakeholder file
	 */
	public void removeStakeholder(String stakkholderID) {
		
	}
	
	/*
	 * edits stakeholder file to adjust booking time allotment
	 * for a stakeholder. Negative value for adjustment will 
	 * remove time.
	 */
	public void adjustTimeAllotment(String stakeholderID, double adjustment) {
		
	}
	
	/*
	 * Sends email message to stakeholder
	 */
	public void notifyStakeholder(String stakeholderID, String message) {
		
	}
	
}
