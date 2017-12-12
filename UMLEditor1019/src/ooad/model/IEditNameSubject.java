package ooad.model;

/**
 * edit name event
 * @author daitor
 *
 */
public interface IEditNameSubject {
	void registerEditNameObserver(IEditNameObserver observer);
	void notifyEditName();
}
