package view;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.FlatteningPathIterator;
import java.awt.geom.Line2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

public class GraphicView extends JPanel {

	private CityMap map = new CityMap();
	private TypicalDay typicalDay = new TypicalDay();
	private DeliveryRound deliveryRound = new DeliveryRound();
	private AffineTransform at; // the current pan and zoom transform
	private Point2D XFormedPoint; // storage for a transformed mouse point
	private double translateX, translateY, scale; // already applied
	private double wheelCounter;
	private JPanel bottomButtons = new JPanel();
	private ArrayList<Delivery> selectedDeliveries = new ArrayList<Delivery>();
	private ArrayList<Node> selectedNodes = new ArrayList<Node>();

	/**
	 * Constructor of the graph panel
	 * 
	 * @param messageBox
	 * @param addButton
	 * @param deleteButton
	 * @param swapButton
	 * @param confirmButton
	 * @param cancelButton
	 * @param undoButton
	 * @param redoButton
	 */
	public GraphicView(JLabel messageBox, JButton addButton, JButton deleteButton, JButton swapButton,
			JButton confirmButton, JButton cancelButton, JButton undoButton, JButton redoButton) {
		super();
		this.setLayout(new BorderLayout());

		bottomButtons.setOpaque(false);
		bottomButtons.setLayout(new BoxLayout(bottomButtons, BoxLayout.X_AXIS));
		bottomButtons.add(addButton);
		bottomButtons.add(Box.createRigidArea(new Dimension(5, 0)));
		bottomButtons.add(deleteButton);
		bottomButtons.add(Box.createRigidArea(new Dimension(5, 0)));
		bottomButtons.add(swapButton);
		bottomButtons.add(Box.createRigidArea(new Dimension(20, 0)));
		bottomButtons.add(confirmButton);
		bottomButtons.add(Box.createRigidArea(new Dimension(5, 0)));
		bottomButtons.add(cancelButton);
		bottomButtons.add(Box.createHorizontalGlue());
		bottomButtons.add(undoButton);
		bottomButtons.add(Box.createRigidArea(new Dimension(5, 0)));
		bottomButtons.add(redoButton);
		add(bottomButtons, BorderLayout.PAGE_END);

		messageBox.setBorder(BorderFactory.createTitledBorder("Message Box"));
		add(messageBox, BorderLayout.PAGE_START);

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
				XFormedPoint = initialTransform.inverseTransform(e.getPoint(), null);
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

		public void mouseWheelMoved(MouseWheelEvent e) {
			int notches = e.getWheelRotation();
			wheelCounter -= ((double) notches) / 5;
			wheelCounter = Math.max(-2, Math.min(wheelCounter, 2));
			scale = Math.exp(wheelCounter);
			repaint();
		}

		public void mouseClicked(MouseEvent e) {
			Node selectedNode = new Node(-1, -1, -1);
			Point pointClicked = e.getPoint();
			double px = (pointClicked.getX() - getWidth() / 2) / scale - translateX + getWidth() / 2;
			double py = (pointClicked.getY() - getHeight() / 2) / scale - translateY + getHeight() / 2;
			for (Node n : map.getNodes()) {
				double x = n.getX() * getCoeff();
				double y = n.getY() * getCoeff();
				if (Math.sqrt((px - x) * (px - x) + (py - y) * (py - y)) < 20) {
					selectedNode = n;
				}
			}
			System.out.println(selectedNode.getId());
			if (selectedNode.getId() != -1) {
				boolean found = false;
				ArrayList<Path> paths = deliveryRound.getPaths();
				for (int j = 0; j < paths.size(); ++j) {
					if (selectedNode.getId() == paths.get(j).getDeparture().getAddress()) {
						selectedDeliveries.add(paths.get(j).getDeparture());
						found = true;
						System.out.println("Delivery Found");
					}
				}
				if (!found) {
					selectedNodes.add(selectedNode);
				}
			} else {
				clearNodes();
			}
			update();
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseMoved(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}
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

	/**
	 * Draw a node
	 * 
	 * @param g
	 * @param n
	 * @param c
	 */
	private void drawNode(Graphics g, Node n, Color c) {
		g.setColor(Color.BLACK);
		g.fillOval((int) (n.getX() * getCoeff() - 16), (int) (n.getY() * getCoeff() - 16), 32, 32);
		g.setColor(c);
		g.fillOval((int) (n.getX() * getCoeff() - 15), (int) (n.getY() * getCoeff() - 15), 30, 30);
		g.setColor(Color.BLACK);
		String nodeId = Integer.toString(n.getId());
		g.setFont(g.getFont().deriveFont(15F));
		g.drawString(nodeId, (int) (n.getX() * getCoeff() - nodeId.length() * 4), (int) (n.getY() * getCoeff() + 4));
	}

	/**
	 * Crate arrow head
	 * 
	 * @param line
	 * @param length
	 * @param width
	 * @return a shape
	 */
	private static Shape createArrowHead(Line2D line, double length, double width) {
		Point2D p0 = line.getP1();
		Point2D p1 = line.getP2();
		double x0 = p0.getX();
		double y0 = p0.getY();
		double x1 = p1.getX();
		double y1 = p1.getY();
		double dx = x1 - x0;
		double dy = y1 - y0;
		double invLength = 1.0 / Math.sqrt(dx * dx + dy * dy);
		double dirX = dx * invLength;
		double dirY = dy * invLength;
		double ax = x1 - length * dirX;
		double ay = y1 - length * dirY;
		double offsetX = width * -dirY * 0.5;
		double offsetY = width * dirX * 0.5;
		double c0x = ax + offsetX;
		double c0y = ay + offsetY;
		double c1x = ax - offsetX;
		double c1y = ay - offsetY;
		Path2D arrowHead = new Path2D.Double();
		arrowHead.moveTo(x1, y1);
		arrowHead.lineTo(c0x, c0y);
		arrowHead.lineTo(c1x, c1y);
		arrowHead.closePath();
		return arrowHead;
	}

	/**
	 * Draw an arrow
	 * 
	 * @param g1
	 * @param x2
	 * @param y2
	 * @param x1
	 * @param y1
	 * @param bold
	 */
	private void drawArrow(Graphics g1, int x2, int y2, int x1, int y1, boolean bold) {
		Graphics2D g = (Graphics2D) g1.create();
		QuadCurve2D q = new QuadCurve2D.Float();
		double dx = x2 - x1;
		double dy = y2 - y1;
		double angle = Math.atan2(dy, dx);
		x2 = (int) (x1 + dx - 16 * Math.cos(angle));
		y2 = (int) (y1 + dy - 16 * Math.sin(angle));
		double COEF = 20;
		Point2D vec = new Point2D.Double(dy, -dx);
		double norme = Math.sqrt(vec.getX() * vec.getX() + vec.getY() * vec.getY());
		vec.setLocation(vec.getX() / norme, vec.getY() / norme);
		Point2D middlePoint = new Point2D.Double(x1 + dx / 2, y1 + dy / 2);
		q.setCurve(x1, y1, middlePoint.getX() + vec.getX() * COEF, middlePoint.getY() + vec.getY() * COEF, x2, y2);
		g.draw(q);
		Point2D recul = q.getP2();
		Line2D l = new Line2D.Double(q.getCtrlPt(), recul);
		g.fill(createArrowHead(l, 30, 20));
	}

	/**
	 * Draw an edge between two nodes/points
	 * 
	 * @param g
	 * @param n1
	 * @param n2
	 * @param c
	 * @param bold
	 */
	private void drawLine(Graphics g, Node n1, Node n2, Color c, boolean bold) {
		Graphics2D graphics2D = (Graphics2D) g;
		g.setColor(c);
		graphics2D.setStroke(new BasicStroke(bold ? 4 : 1));
		drawArrow(g, (int) (n1.getX() * getCoeff()), (int) (n1.getY() * getCoeff()), (int) (n2.getX() * getCoeff()),
				(int) (n2.getY() * getCoeff()), bold);
		graphics2D.setStroke(new BasicStroke(1));
	}

	/**
	 * Paint the component
	 */
	public void paintComponent(Graphics g) {
		Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(g);

		AffineTransform saveTransform = graphics2D.getTransform();
		at = new AffineTransform(saveTransform);
		at.translate(getWidth() / 2, getHeight() / 2);
		at.scale(scale, scale);
		at.translate(-getWidth() / 2, -getHeight() / 2);
		at.translate(translateX, translateY);
		graphics2D.setTransform(at);

		// --- Draw map ---
		for (Section s : map.getSections()) {
			Node n1 = map.getNodeById(s.getArrival());
			Node n2 = map.getNodeById(s.getDeparture());
			drawLine(g, n1, n2, Color.LIGHT_GRAY, false);
		}

		ArrayList<Path> paths = deliveryRound.getPaths();
		for (Path p : paths) {
			for (Section s : p.getSections()) {
				Node n1 = map.getNodeById(s.getArrival());
				Node n2 = map.getNodeById(s.getDeparture());
				drawLine(g, n1, n2, new Color(40, 110, 130), true);
			}
		}

		// Draw late problems
		for (Path p : paths) {
			if (p.isLate() && p != paths.get(paths.size() - 1)) {
				for (Section s : p.getSections()) {
					Node n1 = map.getNodeById(s.getArrival());
					Node n2 = map.getNodeById(s.getDeparture());
					drawLine(g, n1, n2, Color.RED, true);
				}
			}
		}

		for (Node n : map.getNodes()) {
			drawNode(g, n, Color.WHITE);
		}

		// --- Draw deliveries ---
		for (TimeWindow t : typicalDay.getTimeWindows()) {
			for (Delivery d : t.getDeliveries()) {
				Node n = map.getNodeById(d.getAddress());
				drawNode(g, n, new Color(140, 210, 230));
			}
		}

		for (Path p : paths) {
			if (p.isLate() && p != paths.get(paths.size() - 1)) {
				drawNode(g, map.getNodeById(p.getArrival().getAddress()), Color.RED);
			}
		}

		if (typicalDay.getWareHouse() != -1) {
			drawNode(g, map.getNodeById(typicalDay.getWareHouse()), Color.GREEN);
		}

		for (Node n : selectedNodes) {
			drawNode(g, n, Color.YELLOW);
		}
		for (Delivery d : selectedDeliveries) {
			drawNode(g, map.getNodeById(d.getAddress()), Color.YELLOW);
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
		update();
	}

	/**
	 * Change the delivery round
	 * 
	 * @param deliveryRound
	 */
	public void paintDeliveryRound(DeliveryRound deliveryRound) {
		this.deliveryRound = deliveryRound;
	}

	/**
	 * Clear the view
	 */
	public void clearNodes() {
		selectedNodes.clear();
		selectedDeliveries.clear();
	}

	/**
	 * @return the selected deliveries
	 */
	public ArrayList<Delivery> getSelectedDeliveries() {
		return selectedDeliveries;
	}

	/**
	 * @return the selected nodes
	 */
	public ArrayList<Node> getSelectedNodes() {
		return selectedNodes;
	}

	/**
	 * Clear the model of graphic view
	 */
	public void clear() {
		clearNodes();
		deliveryRound = new DeliveryRound();
		typicalDay = new TypicalDay();
	}
}