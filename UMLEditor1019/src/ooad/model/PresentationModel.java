package ooad.model;

/**
 * presentation model
 * @author daitor
 *
 */
public class PresentationModel implements IPresentationModel{
	private Model _model;
	private boolean _isSelect;
	private boolean _isAssociationLine;
	private boolean _isGeneralLine;
	private boolean _isCompositionLine;
	private boolean _isClassMode;
	private boolean _isUseCaseMode;
	
	/**
	 * constructor
	 * @param model
	 */
	public PresentationModel(Model model){
		this._model = model;
		setSelectMode(true);
	}
	
	/**
	 * get model
	 */
	public IModel getModel(){
		return this._model;
	}

	/**
	 * set is select mode
	 * @param isSelect is select mode or not
	 */
	@Override
	public void setSelectMode(boolean isSelect) {
		_isSelect = isSelect;
		_model.setDrawMode(DrawMode.SELECT);
	}

	/**
	 * set is association line mode
	 * @param isAssociaLine is association line mode or not
	 */
	@Override
	public void setAssociaLineMode(boolean isAssociaLine) {
		_isAssociationLine = isAssociaLine;
		_model.setDrawMode(DrawMode.ASSOCIATION_LINE);
	}

	/**
	 * set is general line mode
	 * @param isGeneralLine is general line mode or not
	 */
	@Override
	public void setGeneralLineMode(boolean isGeneralLine) {
		_isGeneralLine = isGeneralLine;
		_model.setDrawMode(DrawMode.GENERAL_LINE);
	}

	/**
	 * set is composition line mode
	 * @param isCompositionLine is composition line mode or not
	 */
	@Override
	public void setCompositionLineMode(boolean isCompositionLine) {
		_isCompositionLine = isCompositionLine;
		_model.setDrawMode(DrawMode.COMPOSITIONLINE);
	}

	/**
	 * set is class mode 
	 * @param isClassMode is class mode or not
	 */
	@Override
	public void setClassMode(boolean isClassMode) {
		_isClassMode = isClassMode;
		_model.setDrawMode(DrawMode.CLASS_MODE);
	}

	/**
	 * set is use case mode
	 * @param isUseCaseMode is use case mode or not
	 */
	@Override
	public void setUseCaseMode(boolean isUseCaseMode) {
		_isUseCaseMode = isUseCaseMode;
		_model.setDrawMode(DrawMode.USECASE_MODE);
	}

	/**
	 * refresh button status
	 */
	@Override
	public void refreshButtonState() {
		_isSelect = false;
		_isAssociationLine = false;
		_isGeneralLine = false;
		_isCompositionLine = false;
		_isClassMode = false;
		_isUseCaseMode = false;
	}

	/**
	 * is select mode or not
	 * @return is select mode
	 */
	@Override
	public boolean isSelectMode() {
		return _isSelect;
	}

	/**
	 * is Association line mode or not 
	 * @return is association line mode 
	 */
	@Override
	public boolean isAssociaLineMode() {
		return _isAssociationLine;
	}

	/**
	 * is general line mode or not
	 * @return is generalize mode
	 */
	@Override
	public boolean isGeneralLineMode() {
		return _isGeneralLine;
	}

	/**
	 * is composition line mode or not
	 * @return is composition line mode
	 */
	@Override
	public boolean isCompositionLineMode() {
		return _isCompositionLine;
	}

	/**
	 * is class mode or not
	 * @return is class mode
	 */
	@Override
	public boolean isClassMode() {
		return _isClassMode;
	}

	/**
	 * is use case mode or not
	 * @return is use case mode
	 */
	@Override
	public boolean isUseCaseMode() {
		return _isUseCaseMode;
	}

	/**
	 * can group or not
	 * @return is menu item group can be selected or not
	 */
	@Override
	public boolean canGroup() {
		return _model.checkCanGroup();
	}

	/**
	 * can ungroup or not
	 * @return is menu item ungroup can be selected or not
	 */
	@Override
	public boolean canUngroup() {
		return  _model.checkCanUnGroup();
	}

	/**
	 * can edit name or not
	 * @return is menu item edit name can be selected or not
	 */
	@Override
	public boolean canEditName() {
		return _model.checkCanEditName();
	}

	/**
	 * delete all store shapes 
	 */
	@Override
	public void newCanvas() {
		_model.deleteAllShapes();
	}
}
