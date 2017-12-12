package ooad.model.shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * data method for class graph
 * @author daitor
 *
 */
public class ClassGraph extends AreaShape{

	/**
	 * constructor
	 */
	public ClassGraph() {
		setWidth(60);
		setHeight(100);
		_name = "ClassGraph";
	}
	
	/**
	 * draw class graph
	 * @param g graphic object for painting
	 */
	@Override
	public void drawShape(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.WHITE);
		g2.fillRect(getStartX(), getStartY(), getWidth(), getHeight());
		g2.setColor(Color.BLACK);
		g2.drawRect(getStartX(), getStartY(), getWidth(), getHeight());
		g2.setStroke(new BasicStroke(1));
		super.drawShape(g);
	}
	
	/**
	 * add string to this class graph
	 * @param stringField string field need to add to this class graph
	 * @param name string field name
	 */
	@Override
	public void addShapeString(IStringField stringField, String name) {
		stringField.setStartX(getStartX() + stringField.getFontSize());
		stringField.setStartY(getStartY() + stringField.getFontSize());
		super.addShapeString(stringField, name);
//		stringField.setStart(getStartX() + stringField.getFontSize(),
//				getStartY() + stringField.getFontSize());
	}

	@Override
	public void editShapeString(String name) {
		super.editShapeString(name);
		IStringField stringField = _strings.get(0);
		stringField.setStartX(getStartX() + stringField.getFontSize());
		stringField.setStartY(getStartY() + stringField.getFontSize());
	}
}
