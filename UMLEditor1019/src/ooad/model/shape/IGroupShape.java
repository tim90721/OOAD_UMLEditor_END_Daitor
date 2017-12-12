package ooad.model.shape;

/**
 * general method for group shape
 * @author Daitor
 *
 */
public interface IGroupShape extends IAreaShape{
	void addShapeToGroup(IShape shape);
	int getShapeCount();
	IShape getStoredShape(int index);
}
