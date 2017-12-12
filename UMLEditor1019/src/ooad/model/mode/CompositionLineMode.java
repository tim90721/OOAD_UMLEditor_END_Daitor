package ooad.model.mode;

import ooad.model.DrawMode;
import ooad.model.IModel;

/**
 * drawing method for composition line mode
 * @author daitor
 *
 */
public class CompositionLineMode extends BasicLineMode{
	
	/**
	 * constructor
	 * @param model model
	 */
	public CompositionLineMode(IModel model) {
		super(model);
	}

	/**
	 * set mode to composition line mode
	 */
	@Override
	public void setMode() {
		_model.setDrawMode(DrawMode.COMPOSITIONLINE);
	}
}
