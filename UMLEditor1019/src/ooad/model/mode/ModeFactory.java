package ooad.model.mode;

import ooad.model.DrawMode;
import ooad.model.IModel;

/**
 * get mode from factory 
 * @author daitor
 *
 */
public class ModeFactory {
	private SelectMode _selectMode;
	private MovingMode _movingMode;
	private AssociaLineMode _associaLineMode;
	private GeneralLineMode _generalLineMode;
	private CompositionLineMode _compositionLineMode;
	private ClassGraphMode _classGraphMode;
	private UseCaseMode _useCaseMode;
	private NoneMode _noneMode;
	
	/**
	 * constructor 
	 * @param model model
	 */
	public ModeFactory(IModel model){
		_selectMode = new SelectMode(model);
		_movingMode = new MovingMode(model);
		_associaLineMode = new AssociaLineMode(model);
		_generalLineMode = new GeneralLineMode(model);
		_compositionLineMode = new CompositionLineMode(model);
		_classGraphMode = new ClassGraphMode(model);
		_useCaseMode = new UseCaseMode(model);
		_noneMode = new NoneMode();
	}
	
	/**
	 * get mode according to current mode
	 * @param mode drawing mode
	 * @return Mode
	 */
	public IMode getMode(DrawMode mode){
		switch (mode) {
		case SELECT:
			return _selectMode;
		case MOVING:
			return _movingMode;
		case ASSOCIATION_LINE:
			return _associaLineMode;
		case GENERAL_LINE:
			return _generalLineMode;
		case COMPOSITIONLINE:
			return _compositionLineMode;
		case CLASS_MODE:
			return _classGraphMode;
		case USECASE_MODE:
			return _useCaseMode;
		default:
			return _noneMode;
		}
	}
}
