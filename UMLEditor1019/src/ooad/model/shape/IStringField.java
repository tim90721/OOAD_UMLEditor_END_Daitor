package ooad.model.shape;

import java.awt.Graphics;

/**
 * general method for string field
 * @author Daitor
 *
 */
public interface IStringField{
	void setName(String name);
	String getName();
	void setFontSize(int fontSize);
	int getFontSize();
	int getFontPixelWidth();
	int getFontPixelHeight();
	void configFontWidth(String name);
	void configFontHeight(String name);
	void drawString(Graphics g);
	int getWidth();
	int getHeight();
	void setStartX(int x);
	void setStartY(int y);
	int getStartX();
	int getStartY();
	void movPos(int difX, int difY);
}
