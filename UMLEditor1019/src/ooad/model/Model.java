package ooad.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ooad.model.mode.IMode;
import ooad.model.mode.ModeFactory;
import ooad.model.shape.AbstractAreaShape;
import ooad.model.shape.IAreaShape;
import ooad.model.shape.IGroupShape;
import ooad.model.shape.IGroupable;
import ooad.model.shape.IShape;
import ooad.model.shape.IStringField;
import ooad.model.shape.ShapeFactory;
import ooad.model.shape.StringField;

/**
 * model for storing data
 * @author daitor
 *
 */
public class Model implements IModel, IPaintSubject, IMenuItemGroupSubject{
	private ArrayList<IShape> _shapes;
	private ArrayList<IShape> _selectShapes;
	private ArrayList<IPaintObserver> _paintObservers;
	private ArrayList<IMenuItemGroupObserver> _menuObservers;
	private int _mouseX, _mouseY;
	private int _prevMouseX, _prevMouseY;
	private int _closeOffset = 30;
	private boolean _isPressed = false;
	private boolean _isDragging = false;
	private boolean _isMouseMoving = false;
	private DrawMode _mode;
	private ShapeFactory _shapeFactory;
	private ModeFactory _modeFactory;
	private IShape _shape;
	private IMode _userMode;

	/**
	 * constructor
	 */
	public Model() {
		_shapes = new ArrayList<IShape>();
		_selectShapes = new ArrayList<IShape>();
		_paintObservers = new ArrayList<IPaintObserver>();
		_menuObservers = new ArrayList<IMenuItemGroupObserver>();
		_shapeFactory = new ShapeFactory();
		_modeFactory = new ModeFactory(this);
		setDrawMode(DrawMode.NONE);
		_shape = _shapeFactory.getShape(getDrawMode());
	}

	/**
	 * draw storing shapes
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		_userMode.drawing(g, _shape, _mouseX, _mouseY, _closeOffset);
		if (!isMousePressed() && !isMouseMoving()) 
			_userMode.storeShape(_shape);
		for (IShape shape : _shapes)
			shape.drawShape(g);
		if (isMousePressed())
			_shape.drawShape(g);
	}

	/**
	 * register paint observer for model change
	 */
	@Override
	public void registerPaintObserver(IPaintObserver observer) {
		_paintObservers.add(observer);
	}

	/**
	 * remove paint observer
	 */
	@Override
	public void unregisterPaintObserver(IPaintObserver observer) {
		int i = _paintObservers.indexOf(observer);
		_paintObservers.remove(i);
	}

	/**
	 * notify paint observer after model change
	 */
	@Override
	public void notifyPaintChange() {
		for (IPaintObserver observer : _paintObservers)
			observer.updatePaint();
	}

	/**
	 * set mouse position
	 * @param x mouse x location
	 * @param y mouse y location
	 */
	@Override
	public void setMousePos(int x, int y) {
		this._mouseX = x;
		this._mouseY = y;
		notifyPaintChange();
		notifyMenuItemGroupChange();
	}

	/** 
	 * set mouse previous position
	 * @param x mouse x previous location
	 * @param y mouse y previous location
	 */
	@Override
	public void setPrevMousePos(int x, int y) {
		_prevMouseX = x;
		_prevMouseY = y;
	}

	/**
	 * get mouse x location
	 */
	@Override
	public int getMouseX() {
		return _mouseX;
	}

	/**
	 *  get mouse y location
	 */
	@Override
	public int getMouseY() {
		return _mouseY;
	}

	/**
	 * get previous mouse x
	 */
	@Override
	public int getPrevMouseX() {
		return _prevMouseX;
	}

	/**
	 * get previous mouse y
	 */
	@Override
	public int getPrevMouseY() {
		return _prevMouseY;
	}

	/**
	 * set mouse is press or not
	 * @param isPressed is mouse pressed
	 */
	@Override
	public void setMousePressed(boolean isPressed) {
		this._isPressed = isPressed;
	}

	/**
	 * get mouse is pressed or not
	 */
	@Override
	public boolean isMousePressed() {
		return this._isPressed;
	}

	/**
	 * set painting mode
	 */
	@Override
	public void setDrawMode(DrawMode mode) {
		this._mode = mode;
		setUserMode(mode);
		refreshShapeState();
	}

	/**
	 * get current drawing mode
	 */
	@Override
	public DrawMode getDrawMode() {
		return this._mode;
	}

	/**
	 * refresh each shape state 
	 * and new a none shape avoiding false painting
	 */
	@Override
	public void refreshShapeState() {
		for (IShape shape : _shapes)
			shape.setSelected(false);
		_shape = _shapeFactory.getShape(DrawMode.NONE);
		notifyPaintChange();
	}

	/**
	 * set mouse is pressed and dragging or not
	 * @param isDragging is mouse dragging
	 */
	@Override
	public void setMouseDragging(boolean isDragging) {
		this._isDragging = isDragging;
	}

	/**
	 * get is mouse dragging or not
	 */
	@Override
	public boolean isMouseDragging() {
		return this._isDragging;
	}

	/**
	 * set mouse is moving or not
	 * if mouse is pressed this value should be false
	 * @param isMoving is mouse moving or not
	 */
	@Override
	public void setMouseMoving(boolean isMoving) {
		_isMouseMoving = isMoving;
	}

	/**
	 * get is mouse moving or not
	 */
	@Override
	public boolean isMouseMoving() {
		return _isMouseMoving;
	}

	/**
	 * new a shape according to current drawing mode
	 */
	@Override
	public void newShape() {
		_shape = _shapeFactory.getShape(getDrawMode());
	}

	/**
	 * new a shape according to mode
	 * @param mode drawing mode for the new shape
	 */
	@Override
	public void newShape(DrawMode mode) {
		_shape = _shapeFactory.getShape(mode);
	}

	/**
	 * store shape
	 * @param shape shape to store
	 */
	@Override
	public void storeShape(IShape shape) {
		_shapes.add(shape);
		refreshShapeDepth();
	}
	
	/**
	 * refresh store shapes depth
	 */
	@Override
	public void refreshShapeDepth() {
		for(IShape storeShape : _shapes)
			storeShape.setDepth(_shapes.indexOf(storeShape));
	}

	/**
	 * set store shapes select status
	 * @param selected is shape selected or not
	 */
	@Override
	public void setShapeSelectStatus(boolean selected) {
		for (IShape shape : _shapes)
			shape.setSelected(selected);
	}

	/** 
	 * set select shapes
	 * @param selectShapes select shapes array list get in select mode 
	 */
	@Override
	public void setSelectShapes(ArrayList<IShape> selectShapes) {
		_selectShapes = selectShapes;
	}

	/**
	 * get select shapes
	 */
	@Override
	public ArrayList<IShape> getSelectShapes() {
		return _selectShapes;
	}

	/**
	 * add shape string
	 * @param name shape string need to add
	 */
	@Override
	public void addShapeString(String name) {
//		_shape = new StringField(name); 
		_userMode.addShapeString(name);
		newShape(DrawMode.NONE);
	}

	/**
	 * get stored shapes
	 */
	@Override
	public ArrayList<IShape> getStoreShapes() {
		return _shapes;
	}

	/**
	 * set user mode
	 * @param mode drawing mode
	 */
	@Override
	public void setUserMode(DrawMode mode) {
		_userMode = _modeFactory.getMode(mode);
	}

	/**
	 * check is mouse enclose 
	 * @param mouseX mouse x location
	 * @param mouseY mouse y location
	 */
	@Override
	public void checkMouseEnclose(int mouseX, int mouseY) {
		boolean isClose = false;
		for (IShape shape : _shapes)
			if (shape.isLineEnclose(mouseX, mouseY, _closeOffset))
				isClose = true;
		if (isClose)
			setMousePos(mouseX, mouseY);
	}

	/**
	 * group shape and refresh store shapes
	 */
	@Override
	public void groupShapes() {
		newShape(DrawMode.GROUP);
		for (IShape shape : _selectShapes) {
			((IGroupShape)_shape).addShapeToGroup(shape);
			_shapes.remove(shape);
		}
		storeShape(_shape);
		_selectShapes.removeAll(_selectShapes);
		_selectShapes.add(_shape);
	}

	/**
	 * ungroup shapes and refresh store shapes
	 */
	@Override
	public void unGroupShapes() {
		if(_selectShapes.size() < 2) {
			IGroupShape groupShape = (IGroupShape)_selectShapes.get(0);
			for(int i = 0; i < groupShape.getShapeCount(); i++){
				IShape shape = groupShape.getStoredShape(i);
				storeShape(shape);
				_selectShapes.add(shape);
			}
			_shapes.remove((IShape)groupShape);
			_selectShapes.remove((IShape)groupShape);
		}
	}

	/**
	 * check is menu item group can be clicked
	 */
	@Override
	public boolean checkCanGroup() {
		if(_selectShapes.size() > 1) 
			return true;
		return false;
	}

	/**
	 * check is menu item ungroup can be clicked
	 */
	@Override
	public boolean checkCanUnGroup() {
		if(_selectShapes.size() == 1) 
			for (IShape shape : _selectShapes) 
				if(((IGroupable)shape).isGrouped()) 
					return true;
		return false;
	}

	/**
	 * check menu item edit name can be clicked
	 */
	@Override
	public boolean checkCanEditName() {
		if(_selectShapes.size() == 1){
			IGroupable shape = (IGroupable)_selectShapes.get(0);
			if(!shape.isGrouped())
				return true;
		}
		return false;
	}
	
	/**
	 * register menu item group listener for listening group item is clicked
	 */
	@Override
	public void registerMenuItemGroupObserver(IMenuItemGroupObserver observer) {
		_menuObservers.add(observer);
	}

	/**
	 * notify menu item group click event, for refreshing item enable status
	 */
	@Override
	public void notifyMenuItemGroupChange() {
		for (IMenuItemGroupObserver observer : _menuObservers) 
			observer.updateItem();
	}

	/**
	 * edit object shape's name
	 */
	@Override
	public void editShapeName(String name) {
		IAreaShape shape = (IAreaShape)_selectShapes.get(0);
		shape.editShapeString(name);
		notifyPaintChange();
	}

	/**
	 * delete all stored shapes
	 */
	@Override
	public void deleteAllShapes() {
		_shapes.removeAll(_shapes);
		_selectShapes.removeAll(_selectShapes);
		notifyPaintChange();
		notifyMenuItemGroupChange();
	}
}
