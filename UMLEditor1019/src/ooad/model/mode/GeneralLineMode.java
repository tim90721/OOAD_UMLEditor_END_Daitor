package ooad.model.mode;

import ooad.model.DrawMode;
import ooad.model.IModel;

/**
 * drawing method for general line mode
 * @author daitor
 *
 */
public class GeneralLineMode extends BasicLineMode {

	/**
	 * constructor
	 * @param model model
	 */
	public GeneralLineMode(IModel model) {
		super(model);
	}

	/**
	 * set mode to general line mode
	 */
	@Override
	public void setMode() {
		_model.setDrawMode(DrawMode.GENERAL_LINE);
	}
}
