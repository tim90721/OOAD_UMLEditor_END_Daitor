package ooad.viewevent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import ooad.model.IEditNameObserver;
import ooad.model.IEditNameSubject;
import ooad.model.IMenuItemGroupObserver;
import ooad.model.IMenuItemGroupSubject;
import ooad.model.IModel;
import ooad.model.IPresentationModel;

/**
 * custom menu event handler getter
 * 
 * @author Daitor
 *
 */
public class CustomMenuHandlerGetter implements IMenuItemGroupSubject,
		IEditNameSubject{
	private IPresentationModel _presentationModel;
	private IModel _model;
	private ArrayList<IMenuItemGroupObserver> _groupObservers;
	private ArrayList<IEditNameObserver> _editNameObservers;

	/**
	 * constructor
	 * 
	 * @param presentationModel
	 *            presentation model
	 */
	public CustomMenuHandlerGetter(IPresentationModel presentationModel) {
		_presentationModel = presentationModel;
		_model = _presentationModel.getModel();
		_groupObservers = new ArrayList<IMenuItemGroupObserver>();
		_editNameObservers = new ArrayList<IEditNameObserver>();
	}

	/**
	 * get group menu event handler
	 * 
	 * @return group menu event handler
	 */
	public CustomMenuHandler getGroupMenuHandler() {
		return new MenuItemGroupHandler(_presentationModel);
	}

	/**
	 * get ungroup menu event handler
	 * 
	 * @return ungroup menu event handler
	 */
	public CustomMenuHandler getUnGroupMenuHandler() {
		return new MenuItemUnGroupHandler(_presentationModel);
	}

	/**
	 * get menu item edit name event handler
	 * 
	 * @return menu item edit name event handler
	 */
	public CustomMenuHandler getEditNameHandler() {
		return new MenuItemEditNameHandler(_presentationModel);
	}

	/**
	 * get menu item new file event handler
	 * 
	 * @return menu item new file event handler
	 */
	public CustomMenuHandler getNewFileHandler() {
		return new MenuItemNewHandler(_presentationModel);
	}
	
	/**
	 * register menu item group click event listener
	 */
	@Override
	public void registerMenuItemGroupObserver(IMenuItemGroupObserver observer) {
		_groupObservers.add(observer);
	}

	/**
	 * notify observer for menu item group click
	 */
	@Override
	public void notifyMenuItemGroupChange() {
		for (IMenuItemGroupObserver observer : _groupObservers)
			observer.updateItem();
	}

	/**
	 * register menu item edit name click event listener
	 */
	@Override
	public void registerEditNameObserver(IEditNameObserver observer) {
		_editNameObservers.add(observer);
	}

	/**
	 * notify menu item edit name click event
	 */
	@Override
	public void notifyEditName() {
		for (IEditNameObserver observer : _editNameObservers)
			observer.editName();
	}

	/**
	 * general operation for custom menu event handler
	 * 
	 * @author Daitor
	 *
	 */
	abstract class CustomMenuHandler implements ActionListener {
		private IPresentationModel _presentationModel;
		private IModel _model;

		public CustomMenuHandler(IPresentationModel presentationModel) {
			_presentationModel = presentationModel;
			_model = _presentationModel.getModel();
		}
	}

	/**
	 * menu item group click event handler
	 * 
	 * @author Daitor
	 *
	 */
	private class MenuItemGroupHandler extends CustomMenuHandler {
		public MenuItemGroupHandler(IPresentationModel presentationModel) {
			super(presentationModel);
		}

		/**
		 * handle menu item group click event
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			_model.groupShapes();
			notifyMenuItemGroupChange();
		}
	}

	/**
	 * menu item ungroup click event handler
	 * 
	 * @author Daitor
	 *
	 */
	private class MenuItemUnGroupHandler extends CustomMenuHandler {
		public MenuItemUnGroupHandler(IPresentationModel presentationModel) {
			super(presentationModel);
		}

		/**
		 * handle menu item ungroup click event
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			_model.unGroupShapes();
			notifyMenuItemGroupChange();
		}
	}

	/**
	 * menu item edit name event handler
	 * 
	 * @author Daitor
	 *
	 */
	private class MenuItemEditNameHandler extends CustomMenuHandler {
		public MenuItemEditNameHandler(IPresentationModel presentationModel) {
			super(presentationModel);
		}

		/**
		 * handle menu item edit name event
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			notifyEditName();
		}
	}

	/**
	 * menu item new file event handler
	 * 
	 * @author daitor
	 *
	 */
	private class MenuItemNewHandler extends CustomMenuHandler {
		public MenuItemNewHandler(IPresentationModel presentationModel) {
			super(presentationModel);
		}

		/**
		 * handle menu item new file event
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			_presentationModel.newCanvas();
		}
	}
}
