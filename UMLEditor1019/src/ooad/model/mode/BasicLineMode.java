package ooad.model.mode;

import ooad.model.DrawMode;
import ooad.model.IModel;
import ooad.model.shape.IBasicLine;
import ooad.model.shape.IShape;

/**
 * basic drawing method for line
 * @author daitor
 *
 */
public abstract class BasicLineMode extends AbstractMode {
	private boolean _hasCloseShape = false;
	private IShape _startShape;

	/**
	 * constructor
	 * @param model model
	 */
	public BasicLineMode(IModel model) {
		super(model);
	}

	/**
	 * check is line enclose 
	 * @param line current drawing line
	 * @param mouseX mouse x location
	 * @param mouseY mouse y location
	 * @param closeOffset define how much pixel is meaning close 
	 */
	@Override
	public void isLineEnclose(IShape line, int mouseX, int mouseY,
			int closeOffset) {
		super.isLineEnclose(line, mouseX, mouseY, closeOffset);
		if(_model.isMouseDragging() && !_model.isMousePressed()
				|| !_model.isMouseDragging() && _model.isMousePressed()){
			_hasCloseShape = false;
		}
		for (IShape shape : _model.getStoreShapes())
			if (_model.isMouseDragging()){
				shape.isLineEnclose(line, mouseX, mouseY, closeOffset);
				if(!_model.isMousePressed() 
						&& shape.isLineEnclose(mouseX, mouseY, closeOffset)
						&& !shape.equals(_startShape)){
					_hasCloseShape = true;
				}
			}
			else if (!_model.isMouseDragging() && _model.isMousePressed()) {
				if (shape.isLineEnclose(mouseX, mouseY, closeOffset)) {
					line.setCoordinate(mouseX, mouseY, mouseX, mouseY);
					shape.setLineStartPos(line);
					_startShape = shape;
					_hasCloseShape = true;
				} 
			} else{
				shape.isLineEnclose(mouseX, mouseY, closeOffset);
			}
		if(!_hasCloseShape){
			_model.newShape(DrawMode.NONE);
		}
	}

	/**
	 * set line's coordinate
	 * @param shape line need to set
	 * @param mouseX mouse x location
	 * @param mouseY mouse y location
	 */
	@Override
	public void setCoordinate(IShape shape, int mouseX, int mouseY) {
		super.setCoordinate(shape, mouseX, mouseY);
		if (_model.isMouseDragging() && shape.getShapeName() != "None"){
			((IBasicLine)shape).setMouseEndXY(mouseX, mouseY);
			shape.setEnd(mouseX, mouseY);
		}
	}

	/**
	 * do nothing
	 */
	@Override
	public void addShapeString(String name) {
	}

	/**
	 * store shape
	 * @param shape shape need to store
	 */
	@Override
	public void storeShape(IShape shape) {
		super.storeShape(shape);
		_hasCloseShape = false;
	}
}
