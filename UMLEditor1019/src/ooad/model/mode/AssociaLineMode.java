package ooad.model.mode;

import ooad.model.DrawMode;
import ooad.model.IModel;

/**
 * drawing method for association line mode 
 * @author daitor
 *
 */
public class AssociaLineMode extends BasicLineMode{
	
	/**
	 * constructor
	 * @param model model
	 */
	public AssociaLineMode(IModel model) {
		super(model);
	}

	/**
	 * set mode
	 */
	@Override
	public void setMode() {
		_model.setDrawMode(DrawMode.ASSOCIATION_LINE);
	}
}
