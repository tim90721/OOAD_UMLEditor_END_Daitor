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
		_font = new Font("Arial Black", Font.PLAIN, _fontSize);
		try{
			setName(name);
		}catch(NullPointerException ex){
			System.out.println("No specific name");
		}
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
		if(!_nameField.equals(""))
			g2.drawString(_nameField, _startX, _startY);
	}

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
		if(name == null)
			name ="";
		_nameField = name;
		configFontWidth(_nameField);
		configFontHeight(_nameField);
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
