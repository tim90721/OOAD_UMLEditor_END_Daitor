package ooad.model;

/**
 * menu item save file event
 * @author daitor
 *
 */
public interface ISaveFileSubject {
	void registerSaveObserver(ISaveFileObserver observer);
	void notifySaveFile();
}
