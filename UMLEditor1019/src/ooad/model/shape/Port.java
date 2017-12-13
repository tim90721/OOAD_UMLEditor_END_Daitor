package ooad.model.shape;

import java.awt.Graphics;

public class Port extends Point{
	
	public static int _portWidth = 10;
	
	/**
	 * constructor
	 */
	public Port() {
		super();
	}
	
	/**
	 * constructor
	 * @param x coordinate x
	 * @param y coordinate y
	 */
	public Port(int x, int y) {
		super(x, y);
	}
	
	public Port(Point point){
		this._x = point.getX();
		this._y = point.getY();
	}
	
	/**
	 * draw port
	 * @param g Graphics
	 */
	public void drawPort(Graphics g) {
		g.fillRect(getX(), getY(), _portWidth, _portWidth);
	}
}
