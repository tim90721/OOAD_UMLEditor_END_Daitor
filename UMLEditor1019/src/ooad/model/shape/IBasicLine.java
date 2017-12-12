package ooad.model.shape;

/**
 * general method for basic lines
 * @author Daitor
 *
 */
public interface IBasicLine {
	void setMiddle();
	void setDirection(Direction direction);
	Direction getDirection();
	void setDifX(int difX);
	void setDifY(int difY);
	int getDifX();
	int getDifY();
	void setMouseEndXY(int x, int y);
	int getMouseEndX();
	int getMouseEndY();
	void setAngle(double angle);
	void setAngle(int difX, int difY);
	double getAngle();
}
