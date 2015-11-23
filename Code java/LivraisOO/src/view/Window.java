package view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame{

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
		setSize(500, 500);
		messageBox.setSize(480,60);
		messageBox.setLocation(0,400);
		graphicView.setLocation(10, 10);
		graphicView.setSize(360, 360);
		textualView.setSize(100,400);
		textualView.setLocation(380,0);
	}
	
	private void showMessage(String message){
		messageBox.setText(message);
	}
}
