package ooad.model.shape;

import java.awt.Graphics;

/**
 * general method for none shape
 * @author Daitor
 *
 */
public class NoneShape extends AbstractShape{
	
	/**
	 * constructor
	 */
	public NoneShape() {
		_name = "None";
	}
	
	/**
	 * do nothing for this method
	 */
	@Override
	public void drawShape(Graphics g) {
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public void setLineStartPos(IShape line) {
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public void setLineEndPos(IShape line) {
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public boolean isLine() {
		return true;
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public void setSelected(boolean isSelect) {
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public boolean isSelected() {
		return false;
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public void setStartX(int x) {
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public void setEndX(int x) {
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public void setStartY(int y) {
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public void setEndY(int y) {
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public void setStart(int startX, int startY) {
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public void setEnd(int endX, int endY) {
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public void setMiddle(int middleX, int middleY) {
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public int getStartX() {
		return super.getStartX();
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public int getEndX() {
		return super.getEndX();
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public int getStartY() {
		return super.getStartY();
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public int getEndY() {
		return super.getEndY();
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public void setMiddleX(int x) {
		super.setMiddleX(x);
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public void setMiddleY(int y) {
		super.setMiddleY(y);
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public int getMiddleX() {
		return super.getMiddleX();
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public int getMiddleY() {
		return super.getMiddleY();
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public void setCoordinate(int startX, int startY, int endX, int endY) {
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public void isLineEnclose(IShape line, int mouseLineX, int mouseLineY,
			int closeOffset) {
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public boolean isLineEnclose(int mouseLineX, int mouseLineY, int closeOffset) {
		return false;
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public double getDistance(int x1, int y1, int x2, int y2) {
		return super.getDistance(x1, y1, x2, y2);
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public void movePos(int difX, int difY) {
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public boolean isGrouped() {
		return false;
	}
}
