package ooad.model.shape;

import java.awt.Graphics;

/**
 * general method for shape
 * @author daitor
 *
 */
public abstract class AbstractShape implements IShape, IGroupable{
	int _depth;
	protected int _startX, _startY;
	protected int _endX, _endY;
	private int _middelX, _middelY;
	protected boolean _isSelected = false;
	protected int _selectRectwidth = 10;
	protected boolean _isLine = false;
	protected String _name;
	
	/**
	 * set shape is select or not
	 */
	@Override
	public void setSelected(boolean isSelect) {
		_isSelected = isSelect;
	}

	/**
	 * get this shape is select or not
	 * @return is selected
	 */
	@Override
	public boolean isSelected() {
		return _isSelected;
	}

	/**
	 * set start x
	 * @param x x location
	 */
	@Override
	public void setStartX(int x) {
		_startX = x;
	}

	/**
	 * set end x
	 * @param x x location
	 */
	@Override
	public void setEndX(int x) {
		_endX = x;
	}

	/**
	 * set start y 
	 * @param y y location
	 */
	@Override
	public void setStartY(int y) {
		_startY = y;
	}

	/**
	 * set end y
	 * @param y y location
	 */
	@Override
	public void setEndY(int y) {
		_endY = y;
	}
	
	/**
	 * set start location
	 * @param startX x location
	 * @param startY y location
	 */
	@Override
	public void setStart(int startX, int startY) {
		setStartX(startX);
		setStartY(startY);
	}

	/**
	 * set end location
	 * @param endX x location
	 * @param endY y location
	 */
	@Override
	public void setEnd(int endX, int endY) {
		setEndX(endX);
		setEndY(endY);
	}

	/**
	 * set middle location
	 * @param middleX x location
	 * @param middleY y location
	 */
	@Override
	public void setMiddle(int middleX, int middleY) {
		setMiddleX(middleX);
		setMiddleY(middleY);
	}

	/**
	 * get start x location
	 * @return start x location
	 */
	@Override
	public int getStartX() {
		return _startX;
	}

	/**
	 * get end x location
	 * @return end x location
	 */
	@Override
	public int getEndX() {
		return _endX;
	}

	/**
	 * get start y location
	 * @return start y location
	 */
	@Override
	public int getStartY() {
		return _startY;
	}

	/**
	 * get end y location
	 * @return end y location
	 */
	@Override
	public int getEndY() {
		return _endY;
	}

	/**
	 * set middle x location
	 * @param x x location
	 */
	@Override
	public void setMiddleX(int x) {
		_middelX = x;
	}

	/**
	 * set middle y location
	 * @param y y location
	 */
	@Override
	public void setMiddleY(int y) {
		_middelY = y;
	}

	/**
	 * get middle x location
	 * @return middle x location
	 */
	@Override
	public int getMiddleX() {
		return _middelX;
	}

	/**
	 * get middle y location
	 * @return middle y location
	 */
	@Override
	public int getMiddleY() {
		return _middelY;
	}

	/**
	 * set shape coordinate 
	 * @param startX start x location
	 * @param startY start y location
	 * @param endX end x location
	 * @param endY end y location
	 */
	@Override
	public void setCoordinate(int startX, int startY, int endX, int endY) {
		setStartX(startX);
		setStartY(startY);
		setEndX(endX);
		setEndY(endY);
	}
	
	/**
	 * draw shape, if shape is selected, draw the focus four side
	 * @param g graphic object for painting
	 */
	@Override
	public void drawShape(Graphics g) {
		if(isSelected()){
			g.fillRect(getMiddleX() - _selectRectwidth / 2, getStartY() - _selectRectwidth, _selectRectwidth, _selectRectwidth);
			g.fillRect(getMiddleX() - _selectRectwidth / 2, getEndY(), _selectRectwidth, _selectRectwidth);
			g.fillRect(getStartX() - _selectRectwidth, getMiddleY() - _selectRectwidth / 2, _selectRectwidth, _selectRectwidth);
			g.fillRect(getEndX(), getMiddleY() - _selectRectwidth / 2, _selectRectwidth, _selectRectwidth);
		}
	}

	/**
	 * check is line enclose
	 * @param line line to check
	 * @param mouseLineX mouse x location
	 * @param mouseLineY mouse y location
	 * @param closeOffset determine how much pixel is meaning close
	 */
	@Override
	public void isLineEnclose(IShape line, int mouseLineX, int mouseLineY, int closeOffset) {
		isLineEnclose(mouseLineX, mouseLineY, closeOffset);
	}
	
	/**
	 * check is line enclose based on mouse end point
	 * @param mouseLineX mouse x location
	 * @param mouseLineY mouse y location
	 * @param closeOffset determine how much pixel is meaning close
	 */
	@Override
	public boolean isLineEnclose(int mouseLineX, int mouseLineY, int closeOffset) {
		if((getStartX() - closeOffset) < mouseLineX && 
				getEndX() + closeOffset > mouseLineX &&
				getStartY() - closeOffset < mouseLineY &&
				getEndY() + closeOffset > mouseLineY){
			setSelected(true);
			return true;
		}
		else{
			setSelected(false);
			return false;
		}
	}

	/**
	 * get distance
	 * @param x1 start x
	 * @param y1 start y
	 * @param x2 end x
	 * @param y2 end y
	 * @return distance
	 */
	@Override
	public double getDistance(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2)
				+ Math.pow(y1 - y2, 2));
	}

	/**
	 * is this shape a line
	 * @return is a line or not
	 */
	@Override
	public boolean isLine() {
		return _isLine;
	}

	/**
	 * get shape name
	 * @return shape name
	 */
	@Override
	public String getShapeName() {
		return _name;
	}

	/**
	 * compare shape method
	 * @param compare object
	 * @return if shape is equal, return true
	 */
	@Override
	public boolean equals(Object obj) {
		IShape shape = (IShape)obj;
		if(shape.getStartX() == getStartX()
				&& shape.getStartY() == getStartY()
				&& shape.getEndX() == getEndX()
				&& shape.getEndY() == getEndY()){
			return true;
		}
		return false;
	}

	/**
	 * check shape is select
	 */
	@Override
	public boolean checkIsSelect(int x1, int y1, int x2, int y2) {
		if (getStartX() > x1 && getStartY() > y1
				&& getEndX() < x2 && getEndY() < y2) 
			return true;
		return false;
	}

	/**
	 * check shape is select
	 */
	@Override
	public boolean checkIsSelect(IShape selectArea) {
		if (getStartX() < selectArea.getStartX() && getEndX() > selectArea.getStartX()
				&& getStartY() < selectArea.getStartY()	&& getEndY() > selectArea.getStartY()) 
			return true;
		return false;
	}

	/**
	 * set shape depth
	 */
	@Override
	public void setDepth(int depth) {
		_depth = depth;
	}

	/**
	 * get shape depth
	 */
	@Override
	public int getDepth() {
		return _depth;
	}
}
