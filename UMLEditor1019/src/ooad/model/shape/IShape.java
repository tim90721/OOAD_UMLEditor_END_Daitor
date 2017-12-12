package ooad.model.shape;

import java.awt.Graphics;

/**
 * general method for shape
 * @author Daitor
 *
 */
public interface IShape {
	void drawShape(Graphics g);
	void setStartX(int x);
	void setEndX(int x);
	void setStartY(int y);
	void setEndY(int y);
	void setMiddleX(int x);
	void setMiddleY(int y);
	void setStart(int startX, int startY);
	void setEnd(int endX, int endY);
	void setMiddle(int middleX, int middleY);
	void setCoordinate(int startX, int startY, int endX, int endY);
	int getStartX();
	int getEndX();
	int getStartY();
	int getEndY();
	int getMiddleX();
	int getMiddleY();
	void setSelected(boolean isSelect);
	boolean isSelected();
	void isLineEnclose(IShape line, int mouseLineX, int mouseLineY, int closeOffset);
	boolean isLineEnclose(int mouseLineX, int mouseLineY, int closeOffset);
	double getDistance(int x1, int y1, int x2, int y2);
	/**
	 * set connected line start position
	 * @param line
	 */
	void setLineStartPos(IShape line);
	void setLineEndPos(IShape line);
	boolean isLine();
	String getShapeName();
	void movePos(int difX, int difY);
	boolean checkIsSelect(int x1, int y1, int x2, int y2);
	boolean checkIsSelect(IShape selectArea);
	void setDepth(int depth);
	int getDepth();
}
