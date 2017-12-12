package ooad.model.shape;

import java.awt.Graphics;

public class Port {
	private int _x, _y;
	public static int _portWidth = 10;
	/**
	 * constructor
	 */
	public Port() {
	
	}
	
	/**
	 * constructor
	 * @param x coordinate x
	 * @param y coordinate y
	 */
	public Port(int x, int y) {
		this._x = x;
		this._y = y;
	}
	
	/**
	 * set x
	 * @param x coordinate x
	 */
	public void setX(int x) {
		this._x = x;
	}
	
	/**
	 * set y
	 * @param y coordinate y
	 */
	public void setY(int y) {
		this._y = y;
	}
	
	/**
	 * get x
	 * @return x
	 */
	public int getX() {
		return this._x;
	}
	
	/**
	 * get y
	 * @return y
	 */
	public int getY() {
		return this._y;
	}
	
	/**
	 * draw port
	 * @param g Graphics
	 */
	public void drawPort(Graphics g) {
		g.fillRect(_x, _y, _portWidth, _portWidth);
	}
}
