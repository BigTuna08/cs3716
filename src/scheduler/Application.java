package scheduler;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

import javax.swing.JFrame;

/*
 * 
 * 
 * Run with command line argument 's' to create Schedule
 */
public class Application  {
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.ENGLISH);	
		if (args.length > 0) {
			if(args[0].equals("principal")) {
				new TabbedGUI(TabbedGUI.Access.PRINCIPAL);
			}else{
				System.out.println("usage: command principal\n"+"opens principal interface\n");
			}
		}else{
			
			new UserLevelPrompt();
		}
	}
}
