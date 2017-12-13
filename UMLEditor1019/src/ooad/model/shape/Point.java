package ooad.model.shape;

public class Point {
	protected int _x, _y;
	
	public Point(){
	
	}
	
	public Point(int x, int y){
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
}
