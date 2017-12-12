package ooad.model;

import java.awt.Graphics;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import ooad.model.shape.IShape;

/**
 * model for storing data
 * @author daitor
 *
 */
public interface IModel {
	void draw(Graphics g);
	void registerPaintObserver(IPaintObserver observer);
	void unregisterPaintObserver(IPaintObserver observer);
	void notifyPaintChange();
	void setMousePos(int x, int y);
	void setPrevMousePos(int x, int y);
	int getMouseX();
	int getMouseY();
	int getPrevMouseX();
	int getPrevMouseY();
	void setMousePressed(boolean isPressed);
	boolean isMousePressed();
	void setMouseDragging(boolean isDragging);
	boolean isMouseDragging();
	void setMouseMoving(boolean isMoving);
	boolean isMouseMoving();
	void setDrawMode(DrawMode mode);
	DrawMode getDrawMode();
	void refreshShapeState();
	void newShape();
	void newShape(DrawMode mode);
	void storeShape(IShape shape);
	void refreshShapeDepth();
	void setShapeSelectStatus(boolean selected);
	void addShapeString(String name);
	ArrayList<IShape> getStoreShapes();
	void setSelectShapes(ArrayList<IShape> selectShapes);
	ArrayList<IShape> getSelectShapes();
	void setUserMode(DrawMode mode);
	void checkMouseEnclose(int mouseX, int mouseY);
	void groupShapes();
	void unGroupShapes();
	boolean checkCanGroup();
	boolean checkCanUnGroup();
	boolean checkCanEditName();
	void editShapeName(String name);
	void deleteAllShapes();
}
