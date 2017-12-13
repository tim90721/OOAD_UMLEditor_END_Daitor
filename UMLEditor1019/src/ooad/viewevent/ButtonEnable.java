package ooad.viewevent;

import javax.swing.JButton;

import ooad.model.IModeSwitchObserver;
import ooad.model.IPresentationModel;

/**
 * handle button should be enabled
 * @author Daitor
 *
 */
public class ButtonEnable implements IModeSwitchObserver{
	private JButton _btnSelect;
	private JButton _btnAssociaLine;
	private JButton _btnGeneralLine;
	private JButton _btnCompositionLine;
	private JButton _btnClass;
	private JButton _btnUseCase;
	private IPresentationModel _presentationModel;
	
	/**
	 * constructor
	 * @param btnSelect select mode button 
	 * @param btnAssociaLine association line mode button
	 * @param btnGeneralLine general line mode button
	 * @param btnCompositionLine composition line mode button
	 * @param btnClass class graph mode button
	 * @param btnUseCase use case mode button 
	 * @param presentationModel presentation model
	 */
	public ButtonEnable(JButton btnSelect, JButton btnAssociaLine, JButton btnGeneralLine,
			JButton btnCompositionLine, JButton btnClass, JButton btnUseCase, IPresentationModel presentationModel) {
		_btnSelect = btnSelect;
		_btnAssociaLine = btnAssociaLine;
		_btnGeneralLine = btnGeneralLine;
		_btnCompositionLine = btnCompositionLine;
		_btnClass = btnClass;
		_btnUseCase = btnUseCase;
		_presentationModel = presentationModel;
		refreshControl();
	}

	/**
	 * handle button click event
	 */
	@Override
	public void updateMode() {
		refreshControl();
	}

	/**
	 * refresh button enable 
	 */
	public void refreshControl(){
		_btnSelect.setEnabled(!_presentationModel.isSelectMode());
		_btnAssociaLine.setEnabled(!_presentationModel.isAssociaLineMode());
		_btnGeneralLine.setEnabled(!_presentationModel.isGeneralLineMode());
		_btnCompositionLine.setEnabled(!_presentationModel.isCompositionLineMode());
		_btnClass.setEnabled(!_presentationModel.isClassMode());
		_btnUseCase.setEnabled(!_presentationModel.isUseCaseMode());
	}
}
