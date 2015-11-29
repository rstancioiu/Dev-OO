package view;

import javax.swing.JPanel;

import model.CityMap;
import model.Delivery;
import model.DeliveryRound;
import model.Node;
import model.Section;
import model.TimeWindow;
import model.Path;
import model.TypicalDay;

import javax.swing.JLabel;

import java.awt.BasicStroke;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

public class GraphicView extends JPanel {

	private CityMap map = new CityMap();
	private TypicalDay typicalDay = new TypicalDay();
	private DeliveryRound deliveryRound = new DeliveryRound();
    private AffineTransform at;   // the current pan and zoom transform
    private Point2D XFormedPoint; // storage for a transformed mouse point
    private double translateX, translateY, scale; //already applied
    private double wheelCounter;
    
    public GraphicView() {
		this.setLayout(new FlowLayout());
		setBackground(Color.white);
		translateX = 0;
	    translateY = 0;
	    scale = 1;
	    wheelCounter = 0;
		MovingHandler handler = new MovingHandler();
		this.addMouseListener(handler);
		this.addMouseMotionListener(handler);
		this.addMouseWheelListener(handler);
	}
    	
	class MovingHandler implements MouseListener, MouseMotionListener, MouseWheelListener {
		double refX;
		double refY;
		AffineTransform initialTransform;

		public void mousePressed(MouseEvent e) {
			try {
				XFormedPoint = at.inverseTransform(e.getPoint(), null);
			} catch (NoninvertibleTransformException te) {
				System.out.println(te);
			}
			refX = XFormedPoint.getX();
			refY = XFormedPoint.getY();
			initialTransform = at;
		}

		public void mouseDragged(MouseEvent e) {
			try {
				XFormedPoint = initialTransform.inverseTransform(e.getPoint(),
						null);
			} catch (NoninvertibleTransformException te) {
				System.out.println(te);
			}
			double deltaX = XFormedPoint.getX() - refX;
			double deltaY = XFormedPoint.getY() - refY;
			refX = XFormedPoint.getX();
			refY = XFormedPoint.getY();
			translateX += deltaX;
			translateY += deltaY;
			repaint();
		}
		
		public void mouseWheelMoved(MouseWheelEvent e){
			int notches = e.getWheelRotation();
			wheelCounter -= ((double)notches)/5;
			wheelCounter = Math.max(-2,Math.min(wheelCounter, 2));
		    scale = Math.exp(wheelCounter);
		    repaint();
		}
		
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}

	/**
	 * Update the component
	 */
	public void update() {
		repaint();
	}

	public double getCoeff() {
		return 3;
	}
	
	private void drawNode(Graphics g, Node n, Color c) {
		g.setColor(Color.BLACK);
		g.fillOval((int) (n.getX() * getCoeff()-16), (int) (n.getY() * getCoeff()-16), 32, 32);
		g.setColor(c);
		g.fillOval((int) (n.getX() * getCoeff()-15), (int) (n.getY() * getCoeff()-15), 30, 30);
		g.setColor(Color.BLACK);
		String nodeId = Integer.toString(n.getId());
		g.setFont(g.getFont().deriveFont(15F));
		g.drawString(nodeId,(int) (n.getX() * getCoeff()-nodeId.length()*4),
				(int) (n.getY() * getCoeff()+4));
	}
	
	private void drawLine(Graphics g, Node n1, Node n2, Color c, boolean bold) {
		Graphics2D graphics2D = (Graphics2D) g;
		g.setColor(c);
		graphics2D.setStroke(new BasicStroke(bold ? 6 : 1));
		g.drawLine((int) (n1.getX() * getCoeff()), (int) (n1.getY() * getCoeff()), (int) (n2.getX() * getCoeff()),
				(int) (n2.getY() * getCoeff()));
		graphics2D.setStroke(new BasicStroke(1));
	}

	/**
	 * Paint the component
	 */
	public void paintComponent(Graphics g) {
		Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(g);
		
	    AffineTransform saveTransform = graphics2D.getTransform();
	    at = new AffineTransform(saveTransform);
	    at.translate(getWidth()/2, getHeight()/2);
	    at.scale(scale, scale);
	    at.translate(-getWidth()/2, -getHeight()/2);
	    at.translate(translateX, translateY);
	    graphics2D.setTransform(at);
	    
		//--- Draw delivery round ---
		for (Path p : deliveryRound.getPaths()) {
			for (Section s : p.getSections()) {
				Node n1 = map.getNodeById(s.getArrival());
				Node n2 = map.getNodeById(s.getDeparture());
				drawLine(g, n1, n2, new Color(40,110,130), true);
			}
		}
	    
	    //--- Draw map ---
	    for (Section s : map.getSections()) {
			Node n1 = map.getNodeById(s.getArrival());
			Node n2 = map.getNodeById(s.getDeparture());
			drawLine(g, n1, n2, Color.GRAY, false);
		}
	    for (Node n : map.getNodes()) {
	    	drawNode(g, n, Color.WHITE);
		}
		
	    //--- Draw deliveries ---
		for (TimeWindow t : typicalDay.getTimeWindows()) {
			for (Delivery d : t.getDeliveries()) {
				Node n = map.getNodeById(d.getAddress());
				drawNode(g, n, new Color(140,210,230));
			}
		}
	    
	    graphics2D.setTransform(saveTransform);
	}

	/**
	 * Change the map
	 * 
	 * @param map
	 */
	public void paintMap(CityMap map) {
		this.map = map;
	}

	/**
	 * Change the typical day
	 * 
	 * @param typicalDay
	 */
	public void paintDeliveries(TypicalDay typicalDay) {
		this.typicalDay = typicalDay;
	}
	
	/**
	 * Change the delivery round
	 * 
	 * @param deliveryRound
	 */
	public void paintDeliveryRound(DeliveryRound deliveryRound) {
		this.deliveryRound = deliveryRound;
	}
}