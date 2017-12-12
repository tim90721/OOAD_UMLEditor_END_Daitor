package ooad.model.shape;

/**
 * general method for group shape
 * @author Daitor
 *
 */
public interface IGroupShape{
	void addShapeToGroup(IShape shape);
	int getShapeCount();
	IShape getStoredShape(int index);
}
