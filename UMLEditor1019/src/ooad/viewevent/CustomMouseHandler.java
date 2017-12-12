package ooad.viewevent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ooad.model.DrawMode;
import ooad.model.IModel;
import ooad.model.IPopMsgObserver;
import ooad.model.IPopMsgSubject;
import ooad.model.IPresentationModel;

/**
 * general method for getting custom mouse event handler
 * @author Daitor
 *
 */
public class CustomMouseHandler implements IPopMsgSubject{
	private IPresentationModel _presentationModel;
	private IModel _model;
	private ArrayList<IPopMsgObserver> _observers;
	
	/**
	 * constructor
	 * @param presentationModel presentation model
	 */
	public CustomMouseHandler(IPresentationModel presentationModel) {
		_presentationModel = presentationModel;
		this._model = _presentationModel.getModel();
		this._observers = new ArrayList<IPopMsgObserver>();
	}
	
	/**
	 * get mouse pressed event handler
	 * @return mouse pressed event handler
	 */
	public CustomMousePressedHandler getPressedHandler() {
		return new CustomMousePressedHandler(_presentationModel);
	}
	
	/**
	 * get mouse dragging event handler
	 * @return mouse dragging event handler
	 */
	public CustomMouseDraggedHandler getDraggedHandler() {
		return new CustomMouseDraggedHandler(_presentationModel);
	}
	
	/**
	 * get mouse release event handler
	 * @return mouse release event handler
	 */
	public CustomMouseReleaseHandler getReleasedEvent() {
		return new CustomMouseReleaseHandler(_presentationModel);
	}
	
	/**
	 * register for pop up message box observer.
	 * for class graph and use case mode.
	 */
	@Override
	public void registerPopMsgObserver(IPopMsgObserver observer) {
		_observers.add(observer);
	}

	/**
	 * unregister for pop up message box observer
	 */
	@Override
	public void unregisterPopMsgObserver(IPopMsgObserver observer) {
		_observers.remove(observer);
	}

	/**
	 * notify when mouse release when drawing mode is class graph and use case
	 */
	@Override
	public void notifyPopMsgObserver() {
		for (IPopMsgObserver observer : _observers) 
			observer.updatePopMsg();
	}

	/**
	 * mouse pressed event handler
	 * @author Daitor
	 *
	 */
	private class CustomMousePressedHandler extends MouseAdapter{
		private IPresentationModel _presentationModel;
		private IModel _model;
		
		public CustomMousePressedHandler(IPresentationModel presentationModel) {
			_presentationModel = presentationModel;
			this._model = _presentationModel.getModel();
		}
		
		/**
		 * handle mouse press event
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			_model.setMousePressed(true);
			_model.setMouseMoving(false);
			_model.newShape();
			_model.setMousePos(e.getX(), e.getY());
		}
	}

	/**
	 * mouse dragged and moving event handler
	 * @author Daitor
	 *
	 */
	private class CustomMouseDraggedHandler extends MouseAdapter{
		private IPresentationModel _presentationModel;
		private IModel _model;
		
		public CustomMouseDraggedHandler(IPresentationModel presentationModel) {
			_presentationModel = presentationModel;
			this._model = _presentationModel.getModel();
		}

		/**
		 * handle mouse dragging event
		 */
		@Override
		public void mouseDragged(MouseEvent e) {
			_model.setMousePos(e.getX(), e.getY());
			_model.setMouseDragging(true);
		}

		/**
		 * handle mouse moving event
		 */
		@Override
		public void mouseMoved(MouseEvent e) {
			super.mouseMoved(e);
			if(!_model.isMouseDragging()){
				if(!_model.isMouseMoving())
					_model.newShape(DrawMode.NONE);
				_model.setMouseMoving(true);
				_model.setMousePos(e.getX(), e.getY());
			}
		}
	}

	/**
	 * mouse release event handle 
	 * @author Daitor
	 *
	 */
	private class CustomMouseReleaseHandler extends MouseAdapter{
		private IPresentationModel _presentationModel;
		private IModel _model;
		
		public CustomMouseReleaseHandler(IPresentationModel presentationModel) {
			_presentationModel = presentationModel;
			this._model = _presentationModel.getModel();
		}
		
		/**
		 * handle mouse release event
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
			_model.setMousePressed(false);
			_model.setMousePos(e.getX(), e.getY());
			notifyPopMsgObserver();
		}
	}
}

