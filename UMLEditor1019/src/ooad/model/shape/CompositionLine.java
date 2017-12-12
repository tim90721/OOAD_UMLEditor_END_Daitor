package ooad.model.shape;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * data method for composition line 
 * @author daitor
 *
 */
public class CompositionLine extends BasicLine {
	private int[] _diamondOrgPointX = new int[] { 0, 10, 20, 10, 0 };
	private int[] _diamondOrgPointY = new int[] { 0, 10, 0, -10, 0 };
	private int[] _diamondPointX = new int[5];
	private int[] _diamondPointY = new int[5];

	/**
	 * constructor
	 */
	public CompositionLine() {
		_name = "CompositionLine";
	}
	
	/**
	 * draw shape for composition line
	 * @param g graphic object for painting
	 */
	@Override
	public void drawShape(Graphics g) {
		super.drawShape(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.rotate(getAngle(), getStartX(), getStartY());
		configDiamondPoints(getEndX(), getEndY());
		g2.drawPolyline(_diamondPointX, _diamondPointY, 5);
		g2.rotate(-1 * getAngle(), getStartX(), getStartY());
		g2.setStroke(new BasicStroke(1));
	}

	/**
	 * configure triangle points
	 * @param x 
	 * @param y
	 */
	private void configDiamondPoints(int x, int y) {
		for (int i = 0; i < _diamondPointX.length; i++) {
			_diamondPointX[i] = getEndX() + _diamondOrgPointX[i];
			_diamondPointY[i] = getEndY() - _diamondOrgPointY[i];
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
		setEndX(getEndX() - 20);
	}
}
