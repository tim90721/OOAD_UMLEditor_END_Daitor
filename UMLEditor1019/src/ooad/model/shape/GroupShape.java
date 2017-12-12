package ooad.model.shape;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * data method for group shape
 * @author daitor
 *
 */
public class GroupShape extends AbstractAreaShape implements IGroupShape{
	private ArrayList<IShape> _groupShapes;
	
	/**
	 * constructor
	 */
	public GroupShape() {
		_groupShapes = new ArrayList<IShape>();
		_name = "GroupShape";
	}
	
	/**
	 * this method don't do anything
	 */
	@Override
	public void addShapeString(IStringField stringField, String name) {
	}

	/**
	 * add shape to group
	 * @param shape shape to save to group
	 */
	@Override
	public void addShapeToGroup(IShape shape) {
		_groupShapes.add(shape);		
	}

	/**
	 * get stored shape by index
	 * @param index stored shape index
	 */
	@Override
	public IShape getStoredShape(int index) {
		return _groupShapes.get(index);
	}

	/**
	 * get stored shape count
	 */
	@Override
	public int getShapeCount() {
		return _groupShapes.size();
	}

	/**
	 * moving shape position by difference x and y
	 */
	@Override
	public void movePos(int difX, int difY) {
		for (IShape shape : _groupShapes) 
			shape.movePos(difX, difY);
	}

	/**
	 * draw shape stored in group
	 * @param g graphic object for painting
	 */
	@Override
	public void drawShape(Graphics g) {
		for (IShape shape : _groupShapes) 
			shape.drawShape(g);
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public void setWidth(int width) {
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public void setHeight(int height) {
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public int getWidth() {
		return 0;
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public int getHeight() {
		return 0;
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
	public void setStartY(int y) {
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
	public void setCoordinate(int startX, int startY, int endX, int endY) {
	}
	
	/**
	 * do nothing for this method
	 */
	@Override
	public void isLineEnclose(IShape line, int mouseLineX, int mouseLineY, int closeOffset) {
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
	public void setCloseSide(CloseSide side) {
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public CloseSide getCloseSide() {
		return CloseSide.None;
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
	 * set select attribute for shape store in group
	 */
	@Override
	public void setSelected(boolean isSelect) {
		for (IShape shape : _groupShapes) 
			shape.setSelected(isSelect);
	}

	/**
	 * get group shape is select or not
	 */
	@Override
	public boolean isSelected() {
		return _groupShapes.get(0).isSelected();
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
	public void setEndY(int y) {
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
		return 0;
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public int getEndX() {
		return 0;
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public int getStartY() {
		return 0;
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public int getEndY() {
		return 0;
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public void setMiddleX(int x) {
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public void setMiddleY(int y) {
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public int getMiddleX() {
		return 0;
	}

	/**
	 * do nothing for this method
	 */
	@Override
	public int getMiddleY() {
		return 0;
	}

	/**
	 * get distance 
	 * @param x1 start x
	 * @param y1 start y
	 * @param x2 end x
	 * @param y2 end y
	 */
	@Override
	public double getDistance(int x1, int y1, int x2, int y2) {
		return super.getDistance(x1, y1, x2, y2);
	}

	/**
	 * check is line or not
	 */
	@Override
	public boolean isLine() {
		return false;
	}

	/**
	 * get shape name
	 * @return shape name
	 */
	@Override
	public String getShapeName() {
		return super.getShapeName();
	}


	/**
	 * check compare obj is equal to this shape
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	/**
	 * check shape is select by points
	 * @param x1 start x
	 * @param y1 start y
	 * @param x2 end x
	 * @param y2 end y
	 */
	@Override
	public boolean checkIsSelect(int x1, int y1, int x2, int y2) {
		boolean hasSelectShape = false;
		for (IShape shape : _groupShapes) 
			if(shape.checkIsSelect(x1, y1, x2, y2)){
				hasSelectShape = true;
				break;
			}
		return hasSelectShape;
	}

	/**
	 * check shape is select by select area
	 * @param selectArea select area set in select mode
	 */
	@Override
	public boolean checkIsSelect(IShape selectArea) {
		boolean hasSelectShape = false;
		for (IShape shape : _groupShapes) 
			if(shape.checkIsSelect(selectArea)){
				hasSelectShape = true;
				break;
			}
		return hasSelectShape;
	}

	/**
	 * check this shape is group or not
	 */
	@Override
	public boolean isGrouped() {
		return true;
	}
}
