import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Utils {

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
}
