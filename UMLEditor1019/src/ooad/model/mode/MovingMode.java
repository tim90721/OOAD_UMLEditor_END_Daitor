package ooad.model.mode;

import java.awt.Graphics;

import ooad.model.DrawMode;
import ooad.model.IModel;
import ooad.model.shape.IShape;

/**
 * drawing method for moving mode
 * @author daitor
 *
 */
public class MovingMode extends AbstractMode{
	
	/**
	 * constructor
	 * @param model model
	 */
	public MovingMode(IModel model) {
		super(model);
	}

	/**
	 * add shape string.
	 * this mode override to do nothing
	 */
	@Override
	public void addShapeString(String name) {
	}

	/**
	 * set mode
	 * this mode override to do nothing
	 */
	@Override
	public void setMode() {
	}

	/** 
	 * drawing method for moving method
	 * @param g graphic object for painting
	 * @param shape shape for painting, moving
	 * @param mouseX mouse x location 
	 * @param mousey mouse y location
	 * @param closeOffset define how much pixel is meaning close
	 */
	@Override
	public void drawing(Graphics g, IShape shape, int mouseX, int mouseY,
			int closeOffset) {
		if(_model.isMousePressed()){
			int difX = _model.getPrevMouseX() - mouseX;
			int difY = _model.getPrevMouseY() - mouseY;
			for (IShape selectShape : _model.getSelectShapes()) 
				selectShape.movePos(difX, difY);
			for (IShape storeShape : _model.getStoreShapes())
				storeShape.drawShape(g);
			_model.setPrevMousePos(mouseX, mouseY);
		}
		else {
			_model.setUserMode(DrawMode.SELECT);
			_model.newShape(DrawMode.NONE);
			_model.setMouseDragging(false);
		}
	}
}
