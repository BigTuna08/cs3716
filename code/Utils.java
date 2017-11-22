import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Utils {

	private final static Color DEFAULT_BUTTON_COLOR = Color.GRAY;
	private final static Color ACTIVE_BUTTON_COLOR = Color.GREEN;
	
	public static Collection<String> loadListFromFile(String fileLocation) {
		ArrayList<String> theList = new ArrayList<>();
		
		try {
			Scanner in = new Scanner(new File(fileLocation));
			
			while (in.hasNextLine()) {
				theList.add(in.nextLine());
			}
			
			in.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Error reading in " + fileLocation);
		}
		return theList;
	}
	
	public static JPanel buildButtonSet(int cols, ActionListener listener, Collection<String> labels) {
		JPanel newPanel = new JPanel(new GridLayout(0, cols));
		for (String lbl: labels) {
			JButton b = new JButton(lbl);
			b.setBackground(DEFAULT_BUTTON_COLOR);
			b.addActionListener(listener);
			newPanel.add(b);
		}
		return newPanel;
	}
}
