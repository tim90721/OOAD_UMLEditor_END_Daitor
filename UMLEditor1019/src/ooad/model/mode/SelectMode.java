package ooad.model.mode;

import java.awt.Graphics;
import java.util.ArrayList;

import ooad.model.DrawMode;
import ooad.model.IModel;
import ooad.model.shape.IShape;

/**
 * drawing method related to select mode
 * @author daitor
 *
 */
public class SelectMode extends AbstractMode {
	private int _startX;
	private int _startY;
	private int _endX;
	private int _endY;
	private ArrayList<IShape> _selectShapes;

	/**
	 * constructor 
	 * @param model model
	 */
	public SelectMode(IModel model) {
		super(model);
		_selectShapes = new ArrayList<IShape>();
		_hasSelectShape = false;
	}

	/**
	 * store shape.
	 * this class override to do nothing
	 */
	@Override
	public void storeShape(IShape shape) {
	}

	/**
	 * check stored shape is select or not
	 * @param selectArea select area set in select mode
	 * @return if there is any store shape is select, than return true.
	 */
	@Override
	public boolean checkIsSelect(IShape selectArea) {
		super.checkIsSelect(selectArea);
		_hasSelectShape = false;
		_selectShapes.removeAll(_selectShapes);
		if (_model.isMouseDragging()) {
			configCoordinate(selectArea);
			for (IShape shape : _model.getStoreShapes())
				if (shape.checkIsSelect(_startX, _startY, _endX, _endY)) {
					shape.setSelected(true);
					_selectShapes.add(shape);
					_hasSelectShape = true;
					System.out.println("bb");
				}
		} else{
			for (IShape shape : _model.getStoreShapes())
				if (shape.checkIsSelect(selectArea)) {
					shape.setSelected(true);
					_selectShapes.add(shape);
					_hasSelectShape = true;
					System.out.println("ss");
				}
			if(_selectShapes.size() > 0){
				checkShapeDepth();
			}
		}
		if (_hasSelectShape)
			return true;
		return false;
	}
	
	/**
	 * check select shapes depth. 
	 * if user click an area contain multiple shapes,
	 * model will choose shape with higher depth.
	 */
	private void checkShapeDepth(){
		IShape shape = _selectShapes.get(0);
		for(int i = 0; i < _selectShapes.size(); i++){
			IShape compareShape = _selectShapes.get(i);
			if(compareShape.getDepth() > shape.getDepth()){
				shape.setSelected(false);
				shape = compareShape;
			}
		}
		_selectShapes.removeAll(_selectShapes);
		_selectShapes.add(shape);
	}

	/**
	 * configuration select area coordinate
	 * @param selectArea select area set in select mode
	 */
	private void configCoordinate(IShape selectArea) {
		_startX = selectArea.getStartX();
		_startY = selectArea.getStartY();
		int selectStartX = _startX;
		int selectStartY = _startY;
		_endX = selectArea.getEndX();
		_endY = selectArea.getEndY();
		int temp;
		if (_startX > _endX) {
			temp = _endX;
			_endX = selectStartX;
			selectStartX = temp;
		}
		if (_startY > _endY) {
			temp = _endY;
			_endY = selectStartY;
			selectStartY = temp;
		}
		_startX = selectStartX;
		_startY = selectStartY;
	}

	/**
	 * check is there any line enclose
	 * @param line line need to check
	 * @param mouseX mouse x location
	 * @param mouseY mouse y location
	 * @param closeOffset define how much pixel is meaning close
	 */
	@Override
	public void isLineEnclose(IShape line, int mouseX, int mouseY,
			int closeOffset) {
		super.isLineEnclose(line, mouseX, mouseY, closeOffset);
	}

	/**
	 * add string to shape
	 * this class override to do nothing
	 */
	@Override
	public void addShapeString(String name) {
	}

	/**
	 * set shape coordinate
	 * @param shape select area
	 * @param mouseX mouse x location
	 * @param mouseY mouse y location
	 */
	@Override
	public void setCoordinate(IShape shape, int mouseX, int mouseY) {
		super.setCoordinate(shape, mouseX, mouseY);
		if (_model.isMouseDragging())
			shape.setEnd(mouseX, mouseY);
	}

	/**
	 * set mode to select mode
	 */
	@Override
	public void setMode() {
		_model.setDrawMode(DrawMode.SELECT);
	}
//
//	@Override
//	public boolean moveSelectShape(int mouseX, int mouseY) {
//		return false;
//	}

	/**
	 * drawing method for select mode
	 * @param g graphic object for painting
	 * @param shape shape for painting
	 * @param mouseX mouse x location 
	 * @param mousey mouse y location
	 * @param closeOffset define how much pixel is meaning close
	 */
	@Override
	public void drawing(Graphics g, IShape shape, int mouseX, int mouseY,
			int closeOffset) {
		if (_model.isMousePressed() && !_model.isMouseDragging()){
			boolean isClickInSelectArea = false;
			setCoordinate(shape, mouseX, mouseY);
			for (IShape selectShape : _selectShapes) {
				if(selectShape.checkIsSelect(shape)){
					_model.setPrevMousePos(mouseX, mouseY);
					_model.setSelectShapes(_selectShapes);
					_model.setUserMode(DrawMode.MOVING);
					isClickInSelectArea = true;
					return;
				}
			}
			if(!isClickInSelectArea)
				_selectShapes = new ArrayList<IShape>();
		}
		super.drawing(g, shape, mouseX, mouseY, closeOffset);
		_model.setSelectShapes(_selectShapes);
	}
}
