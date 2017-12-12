package ooad.model.shape;

import ooad.model.DrawMode;

/**
 * generate shape 
 * @author Daitor
 *
 */
public class ShapeFactory {
	
	/**
	 * get new shape based on drawing mode
	 * @param mode drawing mode
	 * @return new shape
	 */
	public IShape getShape(DrawMode mode) {
		switch (mode) {
		case SELECT:
			return new SelectShape();
		case ASSOCIATION_LINE:
			return new AssociationLine();
		case GENERAL_LINE:
			return new GeneralLine();
		case COMPOSITIONLINE:
			return new CompositionLine();
		case CLASS_MODE:
			return new ClassGraph();
		case USECASE_MODE:
			return new UseCaseShape();
		case GROUP:
			return new GroupShape();
		default:
			return new NoneShape();
		}
	}
}
