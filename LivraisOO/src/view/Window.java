package view;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

<<<<<<< HEAD:LivraisOO/src/view/Window.java
import model.Plan;

public class Window extends JFrame {
=======
import model.Map;

public class Window extends JFrame{
>>>>>>> 9a535b2919dfa1f665df945f77774da1c8c10f49:Code java/LivraisOO/src/view/Window.java

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GraphicView graphicView;
	private LabelView textualView;
	private JLabel messageBox;

	public Window() {
		setLayout(null);
		messageBox = new JLabel();
		graphicView = new GraphicView(this);
		textualView = new LabelView(this);
		messageBox.setBorder(BorderFactory.createTitledBorder("Messages"));
		getContentPane().add(messageBox);
		setSize();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textualView.StepOne();
		graphicView.StepOne();
	}
<<<<<<< HEAD:LivraisOO/src/view/Window.java

	private void setSize() {
		setSize(1000, 800);
		messageBox.setSize(480, 60);
		messageBox.setLocation(0, 400);
		graphicView.setLocation(0, 0);
		graphicView.setSize(800, 740);
		textualView.setSize(100, 400);
		textualView.setLocation(380, 0);
	}

	private void showMessage(String message) {
		messageBox.setText(message);
	}
	
	public void drawMap(Plan map){
=======
	
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
>>>>>>> 9a535b2919dfa1f665df945f77774da1c8c10f49:Code java/LivraisOO/src/view/Window.java
		this.graphicView.paintMap(map);
	}
}
