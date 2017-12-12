package ooad.model.mode;

import java.util.ArrayList;

import ooad.model.DrawMode;
import ooad.model.IModel;
import ooad.model.shape.AbstractAreaShape;
import ooad.model.shape.IShape;
import ooad.model.shape.IStringField;

public class UseCaseMode extends AbstractMode{

	/**
	 * constructor 
	 * @param model model
	 */
	public UseCaseMode(IModel model) {
		super(model);
	}

	/**
	 * add string field to use case 
	 * @param stringField string field need to add to use case
	 * @param name string field name
	 */
	@Override
	public void addShapeString(IStringField stringField, String name) {
		ArrayList<IShape> shapes = _model.getStoreShapes();
		AbstractAreaShape useCase = (AbstractAreaShape)shapes.get(shapes.size() - 1);
		useCase.addShapeString(stringField, name);
		shapes.remove(useCase);
		storeShape(stringField);
		_model.notifyPaintChange();
	}

	/**
	 * set use case coordinate 
	 * @param shape use case graph
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
	 * set mode to use case mode
	 */
	@Override
	public void setMode() {
		_model.setDrawMode(DrawMode.USECASE_MODE);
	}
}
