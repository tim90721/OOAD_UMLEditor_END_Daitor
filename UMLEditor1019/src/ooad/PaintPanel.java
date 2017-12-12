package ooad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ooad.model.IModel;
import ooad.model.IPaintObserver;
import ooad.model.Model;
import ooad.viewevent.CustomMouseEvent;

/**
 * custom paint main panel
 * @author daitor
 *
 */
public class PaintPanel extends JPanel implements IPaintObserver{
	private IModel _model;
	
	/**
	 * constructor
	 * @param model model
	 * @param mouseEvent mouse event for painting
	 */
	public PaintPanel(IModel model, CustomMouseEvent mouseEvent) {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setBackground(Color.WHITE);
		
		_model = model;
		_model.registerPaintObserver(this);
		
		initiateMouseListener(mouseEvent);
	}
	
	/**
	 * initial mouse event
	 * @param mouseEvent mouse event for recording mouse location
	 */
	private void initiateMouseListener(CustomMouseEvent mouseEvent){
		addMouseListener(mouseEvent.getPressedEvent());
		addMouseMotionListener(mouseEvent.getDraggedEvent());
		addMouseListener(mouseEvent.getReleasedEvent());
	}
	
	/**
	 * painting and call model for painting storing data
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		_model.draw(g);
	}

	/**
	 * listener after mouse clicking event
	 */
	@Override
	public void updatePaint() {
		repaint();
	}
}
