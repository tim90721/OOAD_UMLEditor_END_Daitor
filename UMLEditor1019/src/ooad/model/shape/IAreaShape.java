package ooad.model.shape;

/**
 * general method for area shape.
 * area shape include class graph, use case, string field
 * @author Daitor
 *
 */
public interface IAreaShape {
	void setWidth(int width);
	void setHeight(int height);
	int getWidth();
	int getHeight();
	void setCloseSide(CloseSide side);
	CloseSide getCloseSide();
	void addShapeString(IStringField stringField, String name);
	void editShapeString(String name);
}
