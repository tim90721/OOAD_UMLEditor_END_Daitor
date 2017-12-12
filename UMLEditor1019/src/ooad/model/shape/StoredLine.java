package ooad.model.shape;

/**
 * pack line and close side for area shape
 * @author Daitor
 *
 */
public class StoredLine {
	private IShape _line;
	private CloseSide _closeSide;
	
	/**
	 * constructor
	 * @param line line connect to area shape
	 * @param closeSide close side
	 */
	public StoredLine(IShape line, CloseSide closeSide) {
		_line = line;
		_closeSide = closeSide;
	}
	
	/**
	 * get the line that connect to the area shape
	 * @return 
	 */
	public IShape getLine() {
		return _line;
	}
	
	/**
	 * get line connect side
	 * @return close side that line connected
	 */
	public CloseSide getCloseSide() {
		return _closeSide;
	}
}
