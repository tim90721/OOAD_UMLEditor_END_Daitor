package ooad.model;

/**
 * mode switching event
 * @author daitor
 *
 */
public interface IModeSwitchSubject {
	void registerBtnEnableObserver(IModeSwitchObserver observer);
	void notifyModeChange();
}
