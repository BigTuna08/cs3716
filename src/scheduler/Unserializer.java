package scheduler;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Unserializer {
	static Transient fromFile(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
			ObjectInputStream r=new ObjectInputStream(new FileInputStream(filename));
			Transient t=(Transient) r.readObject();
			r.close();
			t.initTransient();
			return t;
	}
	
}
