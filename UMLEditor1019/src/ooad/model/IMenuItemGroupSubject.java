package ooad.model;

/**
 * menu item group click event
 * @author daitor
 *
 */
public interface IMenuItemGroupSubject {
	void registerMenuItemGroupObserver(IMenuItemGroupObserver observer);
	void notifyMenuItemGroupChange();
}
