package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JButton;

import model.Map;
import controller.Controller;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	private GraphicView graphicView;
	private LabelView textualView;
	private JLabel messageBox;
	private Controller controller;
	private JButton loadFile;
	private JButton loadDeliveries;

	public Window(Controller controller) {
		this.controller = controller;
		createAndShowGui();
	}

	private void createAndShowGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 900, 600);
		panel.setLayout(null);

		messageBox = new JLabel();
		graphicView = new GraphicView();
		textualView = new LabelView();
		loadFile = new JButton("New Map");
		loadFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.loadMap();
				;
			}
		});
		loadDeliveries = new JButton("New Delivery");
		loadDeliveries.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.loadDeliveries();
			}
		});

		messageBox.setBorder(BorderFactory.createTitledBorder("Messages"));
		messageBox.setBounds(200, 600, 480, 60);
		graphicView.setBounds(0, 0, 800, 700);
		textualView.setBounds(800, 0, 200, 400);
		loadFile.setBounds(850, 600, 100, 50);
		loadDeliveries.setBounds(850, 500, 100, 50);

		panel.add(messageBox);
		panel.add(graphicView);
		panel.add(textualView);
		panel.add(loadFile);
		panel.add(loadDeliveries);
		panel.setVisible(true);
		textualView.StepOne();
		graphicView.StepOne();
		getContentPane().add(panel);
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(1000, 700);
	}

	public void drawMap(Map map) {
		graphicView.paintMap(map);
		graphicView.update();
	}
}
