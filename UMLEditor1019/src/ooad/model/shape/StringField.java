package ooad.model.shape;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

/**
 * string field draw in the area shape
 * @author Daitor
 *
 */
public class StringField implements IStringField{
//	private AbstractAreaShape _parent;
	private String _nameField;
	private Font _font;
	private int _fontSize = 20;
	private int _fontPixelWidth;
	private int _fontPixelHeight;
	private int _startX, _startY;
	
	/**
	 * constructor 
	 * @param shape shape to be decorated
	 * @param name string name
	 */
	public StringField(String name, int x, int y){
//		_name = "StringField";
//		_parent = shape;
		_font = new Font("Arial Black", Font.PLAIN, _fontSize);
		setName(name);
		setStartX(x);
		setStartY(y);
	}

	/**
	 * draw the string field
	 * @param g graphic object for painting
	 */
	@Override
	public void drawString(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font("Arial Black", Font.PLAIN, _fontSize));
//		_parent.drawShape(g);
		if(!_nameField.equals(""))
			g2.drawString(_nameField, _startX, _startY);
	}

//	/**
//	 * check parent area shape has line enclose for setting end line
//	 * @param line line to be checked is enclose
//	 * @param mouseLineX mouse x location
//	 * @param mouseLineY mouse y location
//	 * @param closeOffset determine how much pixel is meaning close
//	 */
//	@Override
//	public void isLineEnclose(IShape line, int mouseLineX, int mouseLineY, int closeOffset) {
//		_parent.isLineEnclose(line, mouseLineX, mouseLineY, closeOffset);
//	}
//	
//	/**
//	 * check parent area shape has line enclose for setting start line
//	 * @param mouseLineX mouse x location
//	 * @param mouseLineY mouse y location
//	 * @param closeOffset determine how much pixel is meaning close
//	 */
//	@Override
//	public boolean isLineEnclose(int mouseLineX, int mouseLineY, int closeOffset) {
//		return _parent.isLineEnclose(mouseLineX, mouseLineY, closeOffset);
//	}

	/**
	 * get font size
	 * @return font size
	 */
	@Override
	public int getFontSize(){
		return _fontSize;
	}
	
	/**
	 * set string field name
	 * @param name string name
	 */
	@Override
	public void setName(String name) {
		_nameField = name;
		configFontWidth(_nameField);
		configFontHeight(_nameField);
//		setWidth(2 * _fontSize + _fontPixelWidth);
//		setHeight(2 * _fontSize + _fontPixelHeight);
	}

	/**
	 * get string field name
	 * @return string field name
	 */
	@Override
	public String getName() {
		return _nameField;
	}

	/**
	 * set font size
	 * @return font size
	 */
	@Override
	public void setFontSize(int fontSize) {
		_fontSize = fontSize;
	}

	/**
	 * get font pixel width
	 * @return font pixel width
	 */
	@Override
	public int getFontPixelWidth() {
		return _fontPixelWidth;
	}

	/**
	 * get font pixel height
	 * @return font pixel height
	 */
	@Override
	public int getFontPixelHeight() {
		return _fontPixelHeight;
	}

//	/**
//	 * set parent connect start line position
//	 * @param line start line need to be set
//	 */
//	@Override
//	public void setLineStartPos(IShape line) {
//		_parent.setLineStartPos(line);
//	}

//	/**
//	 * set parent connect end line position
//	 * @param line end line need to be set
//	 */
//	@Override
//	public void setLineEndPos(IShape line) {
//		_parent.setLineEndPos(line);
//	}

//	/**
//	 * get parent start x location
//	 * @return parent start x location
//	 */
//	@Override
//	public int getStartX() {
//		return _parent.getStartX();
//	}

//	/**
//	 * get parent end x location
//	 * @return parent end x location
//	 */
//	@Override
//	public int getEndX() {
//		return _parent.getEndX();
//	}

//	/**
//	 * get parent start y location
//	 * @return parent start y location
//	 */
//	@Override
//	public int getStartY() {
//		return _parent.getStartY();
//	}

//	/**
//	 * get parent end y location
//	 * @return parent end y location
//	 */
//	@Override
//	public int getEndY() {
//		return _parent.getEndY();
//	}

//	/**
//	 * set parent is select or not
//	 * @param isSelect is parent select or not
//	 */
//	@Override
//	public void setSelected(boolean isSelect) {
//		_parent.setSelected(isSelect);
//	}

//	/**
//	 * get is parent shape select or not
//	 * @return is parent shape select
//	 */
//	@Override
//	public boolean isSelected() {
//		return _parent.isSelected();
//	}

//	/**
//	 * do nothing for this method
//	 */
//	@Override
//	public void addShapeString(IStringField stringField, String name) {
//	}

//	/**
//	 * move parent position then move itself
//	 * @param difX difference distance in x direction
//	 * @param difY difference distance in y direction
//	 */
//	@Override
//	public void movePos(int difX, int difY) {
//		_parent.movePos(difX, difY);
//		super.movePos(difX, difY);
//	}

//	/**
//	 * set parent width
//	 * @param width parent width
//	 */
//	@Override
//	public void setWidth(int width) {
//		_parent.setWidth(width);
//	}

//	/**
//	 * set parent height 
//	 * @param height parent height
//	 */
//	@Override
//	public void setHeight(int height) {
//		_parent.setHeight(height);
//	}

	/**
	 * get string width
	 */
	@Override
	public int getWidth() {
		return 2 * _fontSize + _fontPixelWidth;
	}

	/**
	 * get string height
	 */
	@Override
	public int getHeight() {
		return 2 * _fontSize + _fontPixelHeight;
	}

	/**
	 * set string start x
	 */
	@Override
	public void setStartX(int x) {
		_startX = x;
	}

	/**
	 * set string start y
	 */
	@Override
	public void setStartY(int y) {
		_startY = y;
	}

	/**
	 * get string start x
	 */
	@Override
	public int getStartX() {
		return _startX;
	}

	/**
	 * get string start y
	 */
	@Override
	public int getStartY() {
		return _startY;
	}

	/**
	 * move drawing start point
	 */
	@Override
	public void movPos(int difX, int difY) {
		setStartX(_startX - difX);
		setStartY(_startY - difY);
	}

	/**
	 * configure font width according to name
	 * @param name string field name
	 */
	@Override
	public void configFontWidth(String name) {
		AffineTransform affineTransform = _font.getTransform();
		FontRenderContext context = new FontRenderContext(affineTransform, true, true);
		_fontPixelWidth = (int)(_font.getStringBounds(_nameField, context).getWidth());
	}

	/**
	 * configure font height according to name
	 * @param name string field name
	 */
	@Override
	public void configFontHeight(String name) {
		AffineTransform affineTransform = _font.getTransform();
		FontRenderContext context = new FontRenderContext(affineTransform, true, true);
		_fontPixelHeight = (int)(_font.getStringBounds(_nameField, context).getHeight());
	}
}
