package ooad.model;

/**
 * event for mouse releasing event in class graph and use case mode
 * @author daitor
 *
 */
public interface IPopMsgSubject {
	void registerPopMsgObserver(IPopMsgObserver observer);
	void unregisterPopMsgObserver(IPopMsgObserver observer);
	void notifyPopMsgObserver();
}
