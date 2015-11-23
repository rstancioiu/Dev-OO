package view;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Map;

public class Window extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GraphicView graphicView;
	private TextualView textualView;
	private JLabel messageBox;
	
	public Window(){
		setLayout(null);
		messageBox = new JLabel();
		graphicView = new GraphicView(this);
		textualView = new TextualView(this);
		messageBox.setBorder(BorderFactory.createTitledBorder("Messages"));
		getContentPane().add(messageBox);
		setSize();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textualView.StepOne();
		graphicView.StepOne();
	}
	
	private void setSize(){
		setSize(1000, 800);
		messageBox.setSize(480,60);
		messageBox.setLocation(0,400);
		graphicView.setLocation(0, 0);
		graphicView.setSize(800, 740);
		textualView.setSize(100,400);
		textualView.setLocation(380,0);
	}
	
	@SuppressWarnings("unused")
	private void showMessage(String message){
		messageBox.setText(message);
	}
	
	public void drawMap(Map map){
		this.graphicView.paintMap(map);
	}
}
