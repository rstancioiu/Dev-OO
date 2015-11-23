package view;

import javax.swing.JPanel;

import model.Map;
import model.Node;
import model.Section;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Color;

public class GraphicView extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel messageArea;
	
	public GraphicView(view.Window w){
		super();
		this.setLayout(new FlowLayout());
		setBackground(Color.white);
		
		this.messageArea = new JLabel("Plan", JLabel.LEFT);
		this.messageArea.setText("<html>Drop your plan here...</html>");
		this.messageArea.setVisible(true);
		this.add(messageArea);

		w.add(this);
	}
	
	public void update(){
		repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	public void paintMap(Map map){
		for(Node n:map.getNodes()){
			this.getGraphics().fillOval(n.getX(), (int)(n.getY()*map.getCoeff()), 6, 6);
			this.getGraphics().drawString(Integer.toString(n.getId()), n.getX()+3, (int)(n.getY()*map.getCoeff())+3);
		}
		for(Section s:map.getSections()){
			Node n1 = map.getNodeById(s.getArrival());
			Node n2 = map.getNodeById(s.getDeparture());
			this.getGraphics().drawLine(n1.getX(), (int)(n1.getY()*map.getCoeff()), n2.getX(), (int)(n2.getY()*map.getCoeff()));
		}
		this.messageArea.setVisible(false);
	}
	public void StepOne(){
	}
}
