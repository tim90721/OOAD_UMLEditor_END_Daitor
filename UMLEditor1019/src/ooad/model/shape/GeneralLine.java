package ooad.model.shape;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * data method for general line method
 * @author daitor
 *
 */
public class GeneralLine extends BasicLine {
	private int[] _trianglePointsOrgX = new int[]{ 0, 0, 20, 0, 0 };
	private int[] _trianglePointsOrgY = new int[]{ 0, 10, 0, -10, 0 };
	private int[] _trianglePointsX = new int[5];
	private int[] _trianglePointsY = new int[5];

	/**
	 * constructor
	 */
	public GeneralLine() {
		_name = "GeneralLine";
	}
	
	/**
	 * draw method for general line 
	 * @param g graphic object for painting
	 */
	@Override
	public void drawShape(Graphics g) {
		super.drawShape(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.rotate(getAngle(), getStartX(), getStartY());
		configTrianglePoints(_rotateEndX, _rotateEndY);
		g2.drawPolyline(_trianglePointsX, _trianglePointsY, 5);
		g2.rotate(-1 * getAngle(), getStartX(), getStartY());
		g2.setStroke(new BasicStroke(1));
	}

	/**
	 * configure triangular points
	 * @param x 
	 * @param y
	 */
	private void configTrianglePoints(int x, int y) {
		for(int i = 0;i < _trianglePointsX.length; i++) {
			_trianglePointsX[i] = getRotateX() + _trianglePointsOrgX[i];
			_trianglePointsY[i] = getRotateY() - _trianglePointsOrgY[i];
		}
	}

	/**
	 * set end location
	 * @param endX end x location
	 * @param endY end y location
	 */
	@Override
	public void setEnd(int endX, int endY) {
		super.setEnd(endX, endY);
		setRotateXY(getRotateX() - 20, getRotateY());
	}
}
