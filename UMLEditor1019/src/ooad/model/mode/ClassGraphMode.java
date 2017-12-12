package ooad.model.mode;

import java.util.ArrayList;

import ooad.model.DrawMode;
import ooad.model.IModel;
import ooad.model.shape.AbstractAreaShape;
import ooad.model.shape.IShape;
import ooad.model.shape.IStringField;

/**
 * drawing method for class graph method
 * @author daitor
 *
 */
public class ClassGraphMode extends AbstractMode{
	
	/**
	 * constructor
	 * @param model model
	 */
	public ClassGraphMode(IModel model) {
		super(model);
	}

	/**
	 * add class graph string 
	 * @param stringField string field add to class graph
	 * @param name string field name
	 */
	@Override
	public void addShapeString(IStringField stringField, String name) {
		ArrayList<IShape> shapes = _model.getStoreShapes();
		AbstractAreaShape classGraph = (AbstractAreaShape)shapes.get(shapes.size() - 1);
		classGraph.addShapeString(stringField, name);
		shapes.remove(classGraph);
		storeShape(stringField);
		_model.notifyPaintChange();
	}

	/**
	 * set class graph coordinate
	 * @param shape class graph
	 * @param mouseX mouse x location
	 * @param mouseY mouse y location
	 */
	@Override
	public void setCoordinate(IShape shape, int mouseX, int mouseY) {
		super.setCoordinate(shape, mouseX, mouseY);
		if(_model.isMouseDragging())
			shape.setStart(mouseX, mouseY);
	}

	/**
	 * set mode to class graph mode
	 */
	@Override
	public void setMode() {
		_model.setDrawMode(DrawMode.CLASS_MODE);
	}
}
