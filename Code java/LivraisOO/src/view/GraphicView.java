package view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Color;

public class GraphicView extends JPanel{

	private JLabel messageArea;
	
	public GraphicView(view.Window w){
		super();
		this.setLayout(new FlowLayout());
		setBackground(Color.blue);
		
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
	
	public void StepOne(){
	}
}
