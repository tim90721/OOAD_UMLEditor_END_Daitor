package ooad.model.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

/**
 * general method for area shape, 
 * including class graph, use case, string field.
 * @author daitor
 *
 */
public abstract class AbstractAreaShape extends AbstractShape implements
		IAreaShape {
	protected int _widthOffset;
	protected int _heightOffset;
	protected CloseSide _side;
	protected ArrayList<StoredLine> _startLines;
	protected ArrayList<StoredLine> _endLines;
	protected ArrayList<StringField> _strings;

	/**
	 * constructor
	 */
	public AbstractAreaShape() {
		_side = CloseSide.None;
		_startLines = new ArrayList<StoredLine>();
		_endLines = new ArrayList<StoredLine>();
		_strings = new ArrayList<StringField>();
	}
	
	/**
	 * set area width
	 * @param width area's width
	 */
	public void setWidth(int width) {
		_widthOffset = width;
		setStartX(getStartX());
	}

	/**
	 * set area height
	 * @param height area's height
	 */
	public void setHeight(int height) {
		_heightOffset = height;
		setStartY(getStartY());
	}

	/**
	 * get width
	 * @return width
	 */
	public int getWidth() {
		return _widthOffset;
	}

	/**
	 * get height
	 * @return height
	 */
	public int getHeight() {
		return _heightOffset;
	}

	/**
	 * set start x location, set end x location based on width,
	 * and set area middle x
	 * @param x x location
	 */
	@Override
	public void setStartX(int x) {
		super.setStartX(x);
		setEndX(getStartX() + _widthOffset);
		setMiddleX(getStartX() + (getEndX() - getStartX()) / 2);
	}

	/**
	 * set start y location, set end y location based on height,
	 * and set area middle y
	 * @param y y location
	 */
	@Override
	public void setStartY(int y) {
		super.setStartY(y);
		setEndY(getStartY() + _heightOffset);
		setMiddleY(getStartY() + (getEndY() - getStartY()) / 2);
	}
	
	/**
	 * set start x, y location, 
	 * and configure connected start and end lines position
	 * @param startX start x location
	 * @param startY start y location
	 */
	@Override
	public void setStart(int startX, int startY) {
		super.setStart(startX, startY);
		configStartLinePos();
		configEndLinePos();
		_side = CloseSide.None;
	}
	
	/**
	 * configure connected start lines position
	 */
	private void configStartLinePos(){
		StoredLine storedLine;
		for(int i = 0; i < _startLines.size(); i++){
			storedLine = _startLines.get(i);
			IShape line = storedLine.getLine();
			_side = storedLine.getCloseSide();
			_startLines.remove(i);
			setLineStartPos(line);
		}
	}
	
	/**
	 * configure connected end lines position
	 */
	private void configEndLinePos(){
		StoredLine storedLine;
		for(int i = 0; i < _endLines.size(); i++){
			storedLine = _endLines.get(i);
			IShape line = storedLine.getLine();
			_side = storedLine.getCloseSide();
			_endLines.remove(i);
			setLineEndPos(line);
		}
	}

	/**
	 * set area shape's coordinate, and set middleX and middleY
	 * @param startX start x location
	 * @param startY start y location
	 * @param endX end x location
	 * @param endY end y location
	 */
	@Override
	public void setCoordinate(int startX, int startY, int endX, int endY) {
		super.setCoordinate(startX, startY, startX + _widthOffset, startY
				+ _heightOffset);
		setMiddleX(getStartX() + (getEndX() - getStartX()) / 2);
		setMiddleY(getStartY() + (getEndY() - getStartY()) / 2);
	}

	/**
	 *  check is line enclose
	 *  @param line line to check
	 *  @param mouseLineX mouse x location
	 *  @param mouseLineY mouse y location
	 *  @param closeOffset determine how much pixel is meaning close
	 */
	@Override
	public void isLineEnclose(IShape line, int mouseLineX, int mouseLineY, int closeOffset) {
		super.isLineEnclose(line, mouseLineX, mouseLineY, closeOffset);
		if(isSelected()){
			findCloseSide(mouseLineX, mouseLineY, closeOffset);
			boolean hasStoreInStart = false;
			for (StoredLine storedLine : _startLines) {
				IShape shapeLine = storedLine.getLine();
				if(shapeLine.equals(line))
					hasStoreInStart = true;
			}
			if(!hasStoreInStart)
				setLineEndPos(line);
		}
		else
			_side = CloseSide.None;
	}

	/**
	 * check is line enclose
	 * @param mouseLineX mouse x location
	 * @param mouseLineY mouse y location
	 * @param closeOffset determine how much pixel is meaning close
	 * @return if there is any line enclose the shape, return ture
	 */
	@Override
	public boolean isLineEnclose(int mouseLineX, int mouseLineY, int closeOffset) {
		super.isLineEnclose(mouseLineX, mouseLineY, closeOffset);
		if(isSelected()){
			findCloseSide(mouseLineX, mouseLineY, closeOffset);
			return true;
		}
		else{
			_side = CloseSide.None;
			return false;
		}
	}

	/**
	 * set close side
	 * @param side line enclose side
	 */
	@Override
	public void setCloseSide(CloseSide side) {
		_side = side;
	}

	/**
	 * get close side 
	 * @return close side
	 */
	@Override
	public CloseSide getCloseSide() {
		return _side;
	}

	/**
	 * find close side
	 * @param mouseLineX mouse x location
	 * @param mouseLineY mouse y location
	 * @param closeOffset determine how much pixel is meaning close
	 */
	private void findCloseSide(int mouseLineX, int mouseLineY, int closeOffset){
		ArrayList<Double> distances = new ArrayList<Double>();
		double difNorth = getDistance(mouseLineX, mouseLineY, getMiddleX(), getStartY());
		double difSouth = getDistance(mouseLineX, mouseLineY, getMiddleX(), getEndY());
		double difWest = getDistance(mouseLineX, mouseLineY, getStartX(), getMiddleY());
		double difEast = getDistance(mouseLineX, mouseLineY, getEndX(), getMiddleY());
		distances.add(difNorth);
		distances.add(difSouth);
		distances.add(difWest);
		distances.add(difEast);
		int minIndex = distances.indexOf(Collections.min(distances));
		switch (minIndex) {
		case 0:
			_side = CloseSide.North;
			break;
		case 1:
			_side = CloseSide.South;
			break;
		case 2:
			_side = CloseSide.West;
			break;
		case 3:
			_side = CloseSide.East;
		default:
			break;
		}
	}
	
	/**
	 * set line start position
	 * @param line line need to set start position
	 */
	@Override
	public void setLineStartPos(IShape line) {
		switch (getCloseSide()) {
		case North:
			line.setStart(getMiddleX(), getStartY());
			break;
		case South:
			line.setStart(getMiddleX(), getEndY());
			break;
		case West:
			line.setStart(getStartX(), getMiddleY());
			break;
		case East:
			line.setStart(getEndX(), getMiddleY());
			break;
		default:
			break;
		}
		if(getCloseSide() != CloseSide.None) {
			line.setEnd(((IBasicLine)line).getMouseEndX(), ((IBasicLine)line).getMouseEndY());
			storeStartLine(line, getCloseSide());
		}
	}

	/**
	 * set line end position
	 * @param line line need to set end position
	 */
	@Override
	public void setLineEndPos(IShape line) {
		switch (getCloseSide()) {
		case North:
			((IBasicLine)line).setMouseEndXY(getMiddleX(), getStartY());
			line.setEnd(getMiddleX(), getStartY());
			break;
		case South:
			((IBasicLine)line).setMouseEndXY(getMiddleX(), getEndY());
			line.setEnd(getMiddleX(), getEndY());
			break;
		case West:
			((IBasicLine)line).setMouseEndXY(getStartX(), getMiddleY());
			line.setEnd(getStartX(), getMiddleY());
			break;
		case East:
			((IBasicLine)line).setMouseEndXY(getEndX(), getMiddleY());
			line.setEnd(getEndX(), getMiddleY());
			break;
		default:
			break;
		}
		if(getCloseSide() != CloseSide.None) {
			storeEndLine(line, getCloseSide());
		}
	}
	
	/**
	 * store connected line to start line
	 * @param line line start from this area shape
	 * @param closeSide close side
	 */
	private void storeStartLine(IShape line, CloseSide closeSide) {
		_startLines.add(new StoredLine(line, closeSide));
	}
	
	/**
	 * store connected line to end line
	 * @param line line end to this area shape
	 * @param closeSide close side
	 */
	private void storeEndLine(IShape line, CloseSide closeSide) {
		for(int i = 0; i < _endLines.size(); i++){
			IShape compareLine = _endLines.get(i).getLine();
			if(compareLine.equals(line))
				_endLines.remove(i);
		}
		_endLines.add(new StoredLine(line, closeSide));
	}

	/**
	 * draw shape. 
	 * if there is a marked close side, draw that side to red.
	 * @param g graphic for painting
	 */
	@Override
	public void drawShape(Graphics g) {
		super.drawShape(g);
		g.setColor(Color.red);
		if(isSelected()) {
			switch (_side) {
			case North:
				g.fillRect(getMiddleX() - _selectRectwidth / 2, getStartY() - _selectRectwidth, _selectRectwidth, _selectRectwidth);
				break;
			case South:
				g.fillRect(getMiddleX() - _selectRectwidth / 2, getEndY(), _selectRectwidth, _selectRectwidth);
				break;
			case West:
				g.fillRect(getStartX() - _selectRectwidth, getMiddleY() - _selectRectwidth / 2, _selectRectwidth, _selectRectwidth);
				break;
			case East:
				g.fillRect(getEndX(), getMiddleY() - _selectRectwidth / 2, _selectRectwidth, _selectRectwidth);
				break;
			default:
				break;
			}
		}
		g.setColor(Color.BLACK);
	}

	/**
	 * moving shape position by difference to mouse moving distance
	 * @param difX direction x moving distance
	 * @param difY direction y moving distance
	 */
	@Override
	public void movePos(int difX, int difY) {
		setStart(_startX - difX, _startY - difY);
	}

	/**
	 * check this shape is in a specific point area or not
	 * @param x1 up left x point
	 * @param y1 up left y point
	 * @param x2 down right x point
	 * @param y2 down right y point
	 * @return is shape's start and end point are in x1~x2 and y1~y2, return true
	 */
	@Override
	public boolean checkIsSelect(int x1, int y1, int x2, int y2) {
		if (getStartX() > x1 && getStartY() > y1
				&& getEndX() < x2 && getEndY() < y2) 
			return true;
		return false;
	}

	/**
	 * check this is in the select area or not
	 * @param selectArea area set in select mode
	 * @return if shape is in the select area, return true
	 */
	@Override
	public boolean checkIsSelect(IShape selectArea) {
		if (getStartX() < selectArea.getStartX() && getEndX() > selectArea.getStartX()
				&& getStartY() < selectArea.getStartY()	&& getEndY() > selectArea.getStartY()) 
			return true;
		return false;
	}

	/**
	 * get this shape is grouped or not 
	 * @return is grouped or not
	 */
	@Override
	public boolean isGrouped() {
		return false;
	}
}

enum CloseSide{
	North,
	South,
	West,
	East,
	None
}
