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
	private int _selectStartX, _selectStartY;
	
	/**
	 * constructor
	 */
	public SelectShape() {
		_name = "Select";
	}
	
	/**
	 * draw select shape
	 */
	@Override
	public void drawShape(Graphics g) {
		configCoordinate();
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1));
		g2.drawRect(_selectStartX, _selectStartY, getEndX() - _selectStartX,
				getEndY() - _selectStartY);
	}
	
	/**
	 * get select start x location
	 * @return select start x location
	 */
	public int getSelectStartX(){
		return _selectStartX;
	}
	
	/**
	 * get select start y location
	 * @return select start y location
	 */
	public int getSelectStartY(){
		return _selectStartY;
	}
	
	/**
	 * configure coordinate let start point begin at up left
	 * and end point at down right
	 */
	private void configCoordinate(){
		_selectStartX = _startX;
		_selectStartY = _startY;
		int temp;
		if(_startX > _endX){
			temp = _endX;
			_endX = _selectStartX;
			_selectStartX = temp;
		}
		if(_startY > _endY){
			temp = _endY;
			_endY = _selectStartY;
			_selectStartY = temp;
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
