package ooad.viewevent;

import javax.swing.JMenuItem;

import ooad.model.IMenuItemGroupObserver;
import ooad.model.IPresentationModel;

/**
 * handle menu item enable status
 * @author Daitor
 *
 */
public class MenuItemEnable implements IMenuItemGroupObserver{
	private JMenuItem _itemGroup;
	private JMenuItem _itemUngroup;
	private JMenuItem _itemEditName;
	private JMenuItem _itemAddNewName;
	private IPresentationModel _presentationModel;

	/**
	 * constructor
	 * @param itemGroup menu item group	
	 * @param itemUngroup menu item ungroup
	 * @param itemEditName menu item edit name
	 * @param itemAddNewName menu item add new name
	 * @param presentationModel presentation model
	 */
	public MenuItemEnable(JMenuItem itemGroup, JMenuItem itemUngroup,
			JMenuItem itemEditName, JMenuItem itemAddNewName,
			IPresentationModel presentationModel) {
		_itemGroup = itemGroup;
		_itemUngroup = itemUngroup;
		_itemEditName = itemEditName;
		_itemAddNewName = itemAddNewName;
		_presentationModel = presentationModel;
	}

	/**
	 * whenever any menu item click
	 */
	@Override
	public void updateItem() {
		refreshControl();
	}
	
	/**
	 * refresh menu item enable status
	 */
	private void refreshControl() {
		_itemGroup.setEnabled(_presentationModel.canGroup()); 
		_itemUngroup.setEnabled(_presentationModel.canUngroup());
		_itemEditName.setEnabled(_presentationModel.canEditName());
		_itemAddNewName.setEnabled(_presentationModel.canEditName());
	}
}
