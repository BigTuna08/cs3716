package scheduler;
/**
 * Transient objects have fields which should be initialized after they are deserialized
 * @author ben
 *
 */
public interface Transient {
	void initTransient();
}
