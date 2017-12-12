package ooad.model;

/**
 * when data changed, notify observer to repaint
 * @author daitor
 *
 */
public interface IPaintSubject {
	void registerPaintObserver(IPaintObserver observer);
	void unregisterPaintObserver(IPaintObserver observer);
	void notifyPaintChange();
}
