package ooad.model.shape;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * general method for select shape
 * @author Daitor
 *
 */
public class SelectShape extends AbstractShape{
//	private int _selectStartX, _selectStartY;
	private Point _selectStartPoint;
	
	/**
	 * constructor
	 */
	public SelectShape() {
		super();
		_name = "Select";
		_selectStartPoint = new Point();
	}
	
	/**
	 * draw select shape
	 */
	@Override
	public void drawShape(Graphics g) {
		configCoordinate();
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1));
//		g2.drawRect(_selectStartX, _selectStartY, getEndX() - _selectStartX,
//				getEndY() - _selectStartY);
		g2.drawRect(getSelectStartX(), getSelectStartY(),
				getEndX() - getSelectStartX(),
				getEndY() - getSelectStartY());
	}
	
	/**
	 * get select start x location
	 * @return select start x location
	 */
	public int getSelectStartX(){
//		return _selectStartX;
		return _selectStartPoint.getX();
	}
	
	/**
	 * get select start y location
	 * @return select start y location
	 */
	public int getSelectStartY(){
//		return _selectStartY;
		return _selectStartPoint.getY();
	}
	
	/**
	 * configure coordinate let start point begin at up left
	 * and end point at down right
	 */
	private void configCoordinate(){
//		_selectStartX = _startX;
//		_selectStartY = _startY;
		_selectStartPoint.setX(getStartX());
		_selectStartPoint.setY(getStartY());
		int temp;
		if(getSelectStartX() > getEndX()){
			temp = getEndX();
//			_endX = getSelectStartX();
//			_selectStartX = temp;
			_endPoint.setX(getSelectStartX());
			_selectStartPoint.setX(temp);
		}
		if(getSelectStartY() > getEndY()){
			temp = getEndY();
//			_endY = getSelectStartY();
//			_selectStartY = temp;
			_endPoint.setY(getSelectStartY());
			_selectStartPoint.setY(temp);
		}
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
