package ooad.model.mode;

import java.awt.Graphics;

import ooad.model.shape.IShape;

/**
 * mode interface
 * @author daitor
 *
 */
public interface IMode {
	void drawing(Graphics g, IShape shape, int mouseX, int mouseY, int closeOffset);
	void storeShape(IShape shape);
	void setCoordinate(IShape shape, int x, int y);
	boolean checkIsSelect(IShape selectArea);
//	boolean moveSelectShape(int mouseX, int mouseY);
	void isLineEnclose(IShape line, int mouseX, int mouseY, int closeOffset);
	void addShapeString(String name);
	void setMode();
}
