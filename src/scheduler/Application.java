package scheduler;

import java.util.Locale;

/**
 * This program is for scheduling semester long school events
 * 
 * @author ben
 *
 */
public class Application {
	public static void main(String[] args) {
		Locale.setDefault(Locale.ENGLISH);
		if (args.length > 0) {
			if (args[0].equals("principal")) {
				new TabbedGUI(TabbedGUI.Access.PRINCIPAL);
			} else {
				
			}
		} else {
			new UserLevelPrompt();
		}
	}
}
