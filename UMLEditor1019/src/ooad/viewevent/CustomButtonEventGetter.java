package ooad.viewevent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import ooad.model.IModeSwitchObserver;
import ooad.model.IModeSwitchSubject;
import ooad.model.IModel;
import ooad.model.IPaintObserver;
import ooad.model.IPresentationModel;

/**
 * custom button event getter
 * @author Daitor
 *
 */
public class CustomButtonEventGetter implements IModeSwitchSubject{
	private IPresentationModel _presentationModel;
	private IModel _model;
	private IModeSwitchObserver _oBtnEnable;
	
	/**
	 * constructor
	 * @param presentationModel presentation model
	 */
	public CustomButtonEventGetter(IPresentationModel presentationModel){
		this._presentationModel = presentationModel;
		this._model = this._presentationModel.getModel();
	}
	
	/**
	 * register listener
	 */
	@Override
	public void registerBtnEnableObserver(IModeSwitchObserver observer){
		_oBtnEnable = observer;
	}
	
	/**
	 * notify button click event
	 */
	@Override
	public void notifyModeChange(){
		_oBtnEnable.updateMode();;
	}
	
	/**
	 * get select button click event handler
	 * @return select button click event handler
	 */
	public BtnSelectClickEvent getSelectClickEvent(){
		return new BtnSelectClickEvent(_presentationModel);
	}
	
	/**
	 * get association line button click event handler
	 * @return association line button click event handler
	 */
	public BtnAssociaLineClickEvent getAssociaLineClickEvent(){
		return new BtnAssociaLineClickEvent(_presentationModel);
	}
	
	/**
	 * get general line click button event handler
	 * @return general line click button event handler
	 */
	public BtnGeneralLineClickEvent getGeneralLineClickEvent(){
		return new BtnGeneralLineClickEvent(_presentationModel);
	}
	
	/**
	 * get composition line button click event handler
	 * @return composition line button click event handler
	 */
	public BtnCompositionLineClickEvent getCompositionLineClickEvent(){
		return new BtnCompositionLineClickEvent(_presentationModel);
	}
	
	/**
	 * get class graph button click event handler
	 * @return class graph button click event handler
	 */
	public BtnClassModeClickEvent getClassModeClickEvent(){
		return new BtnClassModeClickEvent(_presentationModel);
	}
	
	/**
	 * get use class button click event handler
	 * @return use class button click event handler
	 */
	public BtnUseCaseModeClickEvent getUseCaseModeClickEvent(){
		return new BtnUseCaseModeClickEvent(_presentationModel);
	}
	
	/**
	 * general custom button event handler
	 * @author Daitor
	 *
	 */
	class CustomBtnEvent implements ActionListener{
		private IPresentationModel _presentationModel;
		private IModel _model;
		
		public CustomBtnEvent(IPresentationModel presentationModel){
			this._presentationModel = presentationModel;
			this._model = this._presentationModel.getModel();
		}

		/**
		 * whenever an event refresh control
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			_presentationModel.refreshButtonState();
		}
	}
	
	/**
	 * button select click event handler
	 * @author Daitor
	 *
	 */
	private class BtnSelectClickEvent extends CustomBtnEvent{
		
		public BtnSelectClickEvent(IPresentationModel presentationModel) {
			super(presentationModel);
		}
		
		/**
		 * handle select button click
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			_presentationModel.setSelectMode(true);
			notifyModeChange();
		}
	}
	
	/**
	 * button association line click event
	 * @author Daitor
	 *
	 */
	private class BtnAssociaLineClickEvent extends CustomBtnEvent{
		
		public BtnAssociaLineClickEvent(IPresentationModel presentationModel){
			super(presentationModel);
		}

		/**
		 * handle association line button click
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			_presentationModel.setAssociaLineMode(true);
			notifyModeChange();
		}
	}
	
	/**
	 * button general line click event handler
	 * @author Daitor
	 *
	 */
	private class BtnGeneralLineClickEvent extends CustomBtnEvent{
		
		public BtnGeneralLineClickEvent(IPresentationModel presentationModel){
			super(presentationModel);
		}

		/**
		 * handle general line button click event
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			_presentationModel.setGeneralLineMode(true);
			notifyModeChange();
		}
	}
	
	/**
	 * button composition line click event handler
	 * @author Daitor
	 *
	 */
	private class BtnCompositionLineClickEvent extends CustomBtnEvent{
		
		public BtnCompositionLineClickEvent(IPresentationModel presentationModel){
			super(presentationModel);
		}

		/**
		 * handle composition line click event
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			_presentationModel.setCompositionLineMode(true);
			notifyModeChange();
		}
	}
	
	/**
	 * button class graph mode click event handler
	 * @author Daitor
	 *
	 */
	private class BtnClassModeClickEvent extends CustomBtnEvent{
		
		public BtnClassModeClickEvent(IPresentationModel presentationModel){
			super(presentationModel);
		}

		/**
		 * handle class graph mode click event
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			_presentationModel.setClassMode(true);
			notifyModeChange();
		}
	}
	
	/**
	 * button use case mode click event handler
	 * @author Daitor
	 *
	 */
	private class BtnUseCaseModeClickEvent extends CustomBtnEvent{
		
		public BtnUseCaseModeClickEvent(IPresentationModel presentationModel){
			super(presentationModel);
		}

		/**
		 * handle use case mode click event
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			_presentationModel.setUseCaseMode(true);
			notifyModeChange();
		}
	}
}

