package view;

import javax.swing.JPanel;

import model.CityMap;
import model.Node;
import model.Section;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Color;

public class GraphicView extends JPanel {

	private JLabel messageArea;
	private CityMap map = new CityMap();

	public GraphicView() {
		this.setLayout(new FlowLayout());
		setBackground(Color.white);

		this.messageArea = new JLabel("Plan", JLabel.LEFT);
		this.messageArea.setText("<html>Drop your plan here...</html>");
		this.messageArea.setVisible(true);
		this.add(messageArea);
	}

	/**
	 * Update the component
	 */
	public void update() {
		repaint();
	}

	/**
	 * Paint the component
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Node n : map.getNodes()) {
			g.fillOval(n.getX(), (int) (n.getY() * map.getCoeff()), 6, 6);
			g.drawString(Integer.toString(n.getId()), n.getX() + 3, (int) (n.getY() * map.getCoeff()) + 3);
		}
		for (Section s : map.getSections()) {
			Node n1 = map.getNodeById(s.getArrival());
			Node n2 = map.getNodeById(s.getDeparture());
			g.drawLine(n1.getX(), (int) (n1.getY() * map.getCoeff()), n2.getX(), (int) (n2.getY() * map.getCoeff()));
		}
	}

	/**
	 * Change of the map
	 * 
	 * @param map
	 */
	public void paintMap(CityMap map) {
		this.map = map;
	}

	public void StepOne() {
	}
}