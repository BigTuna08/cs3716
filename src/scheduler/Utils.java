package scheduler;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * common miscellaneous utilities not justifying a separate class
 * @author ben
 *
 */
public class Utils {
	public static String[] days = { "mon", "tues", "wed", "thurs", "fri", "sat", "sun" };
	
	//converts each element of a collection to a string
	public static String stringifyCollection(Collection<?> c) {
		return (String) c.stream().map(e -> e.toString()).collect(Collectors.joining("\n"));
	}
}
