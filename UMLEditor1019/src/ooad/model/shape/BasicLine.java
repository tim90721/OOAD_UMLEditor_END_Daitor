package ooad.model.shape;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * general data method for line
 * @author daitor
 *
 */
public abstract class BasicLine extends AbstractShape implements IBasicLine{
	private int _offset = 10;
	protected double _distance;
	protected Direction _direction;
	protected int _difX, _difY;
	protected double _angle;
	private int _mouseEndX;
	private int _mouseEndY;
	protected int _rotateEndX, _rotateEndY;
	private int _hashNumber;
	
	/**
	 * constructor
	 */
	public BasicLine(){
		_direction = Direction.VERTICAL;
		_isLine = true;
		_hashNumber = (int)(Math.random() * 10000);
	}
	
	/**
	 * draw line
	 * @param g graphic object for painting
	 */
	@Override
	public void drawShape(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(5));
		g2.rotate(getAngle(), getStartX(), getStartY());
		g2.drawLine(getStartX(), getStartY(), _rotateEndX, _rotateEndY);
		g2.setStroke(new BasicStroke(1));
		g2.rotate(-1 * getAngle(), getStartX(), getStartY());
	}
	
	/**
	 * set middle location
	 */
	@Override
	public void setMiddle() {
		setDifX(_startX - _endX);
		setDifY(_startY - _endY);
	}
	
	/**
	 * set direction
	 * @param direction direction, vertical or horizontal
	 */
	@Override
	public void setDirection(Direction direction){
		_direction = direction;
	}
	
	/**
	 * get direction
	 * @return direction
	 */
	@Override
	public Direction getDirection(){
		return _direction;
	}
	
	/**
	 * set difference x 
	 * @param difX difference in x direction
	 */
	@Override
	public void setDifX(int difX){
		_difX = difX;
	}
	
	/**
	 * set difference y 
	 * @param difY difference in y direction
	 */
	@Override
	public void setDifY(int difY){
		_difY = difY;
	}
	
	/**
	 * get difference x
	 * @return difference x in x direction
	 */
	@Override
	public int getDifX(){
		return _difX;
	}
	
	/**
	 * get difference y 
	 * @return difference y in y direction
	 */
	@Override
	public int getDifY(){
		return _difY;
	}

	/**
	 * set mouse end x, y location
	 * @param x mouse x location
	 * @param y mouse y location
	 */
	@Override
	public void setMouseEndXY(int x, int y) {
		_mouseEndX = x;
		_mouseEndY = y;
	}

	/**
	 * get mouse end x location
	 * @return mouse end x location
	 */
	@Override
	public int getMouseEndX() {
		return _mouseEndX;
	}

	/**
	 * get mouse end y location
	 * @return mouse end y location
	 */
	@Override
	public int getMouseEndY() {
		return _mouseEndY;
	}

	/**
	 * set angle 
	 * @param angle rotate angle
	 */
	@Override
	public void setAngle(double angle) {
		_angle = angle;
	}

	/**
	 * set angle based on difference x and y
	 * @param difX difference in x direction
	 * @param difY difference in y direction
	 */
	@Override
	public void setAngle(int difX, int difY) {
		_angle = Math.atan2(difY, difX);
	}

	/**
	 * get angle
	 * @return angle rotate angle
	 */
	@Override
	public double getAngle() {
		return _angle;
	}

	/**
	 * set end position and calculate difference in x and y direction, angle, distance
	 * @param endX end x position
	 * @param endY end y position
	 */
	@Override
	public void setEnd(int endX, int endY) {
		setDifX(getMouseEndX() - getStartX());
		setDifY(getMouseEndY() - getStartY());
		setAngle(getDifX(), getDifY());
		_distance = getDistance(getMouseEndX(), getMouseEndY(), getStartX(), getStartY());
		setRotateXY(getStartX() + (int)_distance, getStartY());
		setEndX(endX);
		setEndY(endY);
	}
	
	protected void setRotateXY(int x, int y) {
		_rotateEndX = x;
		_rotateEndY = y;
	}
	
	protected int getRotateX() {
		return _rotateEndX;
	}
	
	protected int getRotateY() {
		return _rotateEndY;
	}
	
//	/**
//	 * do nothing for this method
//	 */
//	@Override
//	public void setSelected(boolean isSelect) {
//	}

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
	public void movePos(int difX, int difY) {
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public boolean isGrouped() {
		return false;
	}
	
	/**
	 * check this line is equals to the object line
	 * @return if is equal, return true
	 */
	@Override
	public boolean equals(Object obj) {
		IShape line = (IShape)obj;
		if(line.hashCode() == hashCode())
			return true;
		return false;
	}

	/**
	 * get hash code
	 * @return hash code
	 */
	@Override
	public int hashCode() {
		return _hashNumber;
	}
}

enum Direction{
	VERTICAL,
	HORIZONTAL
}
