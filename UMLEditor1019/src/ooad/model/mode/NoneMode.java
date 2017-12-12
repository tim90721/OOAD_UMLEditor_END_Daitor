package ooad.model.mode;

import java.awt.Graphics;

import ooad.model.shape.IShape;
import ooad.model.shape.IStringField;

/**
 * drawing method for none mode.
 * this class do nothing for drawing
 * @author daitor
 *
 */
public class NoneMode implements IMode{

	/**
	 * drawing method for none mode.
	 * this class override to do nothing
	 */
	@Override
	public void drawing(Graphics g, IShape shape, int mouseX, int mouseY,
			int closeOffset) {
	}

	/**
	 * store shape. 
	 * this class override to do nothing
	 */
	@Override
	public void storeShape(IShape shape) {
	}

	/**
	 * set shape coordinate.
	 * this class override to do nothing
	 */
	@Override
	public void setCoordinate(IShape shape, int x, int y) {
	}

	/**
	 * check shape is select or not.
	 * this class override to do nothing
	 */
	@Override
	public boolean checkIsSelect(IShape selectArea) {
		return false;
	}

	/**
	 * check is line enclose or not.
	 * this class override to do nothing
	 */
	@Override
	public void isLineEnclose(IShape line, int mouseX, int mouseY,
			int closeOffset) {
	}

	/**
	 * add string to shape.
	 * this class override to do nothing
	 */
	@Override
	public void addShapeString(IStringField stringField, String name) {
	}

	/**
	 * set mode.
	 * this class override to do nothing
	 */
	@Override
	public void setMode() {
	}

//	@Override
//	public boolean moveSelectShape(int mouseX, int mouseY) {
//		// TODO Auto-generated method stub
//		return false;
//	}
}
