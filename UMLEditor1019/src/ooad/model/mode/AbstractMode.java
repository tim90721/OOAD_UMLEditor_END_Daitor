package ooad.model.mode;

import java.awt.Graphics;

import ooad.model.IModel;
import ooad.model.shape.IShape;

/**
 * general mode method
 * @author daitor
 *
 */
public abstract class AbstractMode implements IMode{
	protected IModel _model;
	protected boolean _hasSelectShape;
	
	/**
	 * constructor
	 * @param model model
	 */
	public AbstractMode(IModel model) {
		this._model = model;
		_hasSelectShape = false;
	}
	
	/**
	 * store shape
	 * @param shape shape to store
	 */
	@Override
	public void storeShape(IShape shape) {
		if(shape.getShapeName() != "None")
			_model.storeShape(shape);
	}

	/**
	 * set shape select status to false
	 * @param selectArea area set in select mode
	 */
	@Override
	public boolean checkIsSelect(IShape selectArea) {
		setShapesSelected(false);
		return false;
	}

	/**
	 * set shape select status to false
	 * @param line current drawing line
	 * @param mouseX mouse x location
	 * @param mouseY mouse y location
	 * @param closeOffset define how much pixel is meaning close 
	 */
	@Override
	public void isLineEnclose(IShape line, int mouseX, int mouseY, int closeOffset) {
		if(!_model.isMouseMoving())
			setShapesSelected(false);
	}
	
	/**
	 * set shape coordinate 
	 * @param shape shape need to set
	 * @param mouseX mouse x location
	 * @param mouseY mouse y location
	 */
	@Override
	public void setCoordinate(IShape shape, int mouseX, int mouseY) {
		if(!_model.isMouseDragging())
			shape.setCoordinate(mouseX, mouseY, mouseX, mouseY);
	}
	
	/**
	 * set shapes select status to false
	 * @param select shape status
	 */
	private void setShapesSelected(boolean select){
		for (IShape shape : _model.getStoreShapes())
			shape.setSelected(false);
	}

	/**
	 * general drawing method 
	 * @param g graphic object for painting
	 * @param shape shape for painting
	 * @param mouseX mouse x location 
	 * @param mousey mouse y location
	 * @param closeOffset define how much pixel is meaning close
	 */
	@Override
	public void drawing(Graphics g, IShape shape, int mouseX, int mouseY,
			int closeOffset) {
		setCoordinate(shape, mouseX, mouseY);
		isLineEnclose(shape, mouseX, mouseY, closeOffset);
		if (!_model.isMousePressed() && !_model.isMouseMoving()) {
			_hasSelectShape = checkIsSelect(shape);
			_model.setMouseDragging(false);
		}
	}
//
//	/**
//	 * 
//	 */
//	@Override
//	public boolean moveSelectShape(int mouseX, int mouseY) {
//		return false;
//	}
}
