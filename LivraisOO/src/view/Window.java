package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JButton;

import model.CityMap;
import model.Delivery;
import model.DeliveryRound;
import model.TimeWindow;
import model.TypicalDay;

import controller.Controller;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	private GraphicView graphicView;
	private TextualView textualView;
	private Controller controller;
	private JButton loadMap;
	private JButton loadDeliveries;
	private JButton compute;
	private JButton generateRoadmap;

	public Window(Controller controller) {
		this.controller = controller;
		createAndShowGui();
	}

	private void createAndShowGui() {
		setSize(1000, 600);
		setMinimumSize(new Dimension(500, 500));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(1000,700));
		GroupLayout layout = new GroupLayout(panel);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		panel.setLayout(layout);

		graphicView = new GraphicView();
		textualView = new TextualView();
		loadMap = new JButton("Load Map");
		loadMap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.loadMap();
				;
			}
		});
		loadDeliveries = new JButton("Load deliveries");
		loadDeliveries.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.loadDeliveries();
			}
		});
		compute = new JButton("Compute");
		compute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.computeDeliveries();
			}
		});
		generateRoadmap = new JButton("Generate Roadmap");
		generateRoadmap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		layout.setHorizontalGroup(
		   layout.createSequentialGroup()
		      .addComponent(graphicView)
		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
		           .addComponent(textualView)
		           .addComponent(compute)
		           .addComponent(generateRoadmap)
		           .addComponent(loadMap)
		           .addComponent(loadDeliveries)
		      )
		);
		
		layout.setVerticalGroup(
		   layout.createSequentialGroup()
		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		           .addComponent(graphicView)
		           .addGroup(layout.createSequentialGroup()
				      .addComponent(textualView)
		    		  .addComponent(compute)
			          .addComponent(generateRoadmap)
				      .addComponent(loadMap)
				      .addComponent(loadDeliveries)
				   )
			)
		);

		textualView.setMinimumSize(new Dimension(200,250));
		textualView.setMaximumSize(new Dimension(200,800));
		loadMap.setMinimumSize(new Dimension(150,20));
		loadDeliveries.setMinimumSize(new Dimension(150,20));
		compute.setMinimumSize(new Dimension(150,20));
		generateRoadmap.setMinimumSize(new Dimension(150,20));
		
		panel.setVisible(true);
		getContentPane().add(panel);
		setVisible(true);
	}

	public void drawMap(CityMap map) {
		graphicView.paintMap(map);
		graphicView.update();
	}

	public void drawDeliveries(TypicalDay typicalDay) {
		graphicView.paintDeliveries(typicalDay);
		textualView.listDeliveries(typicalDay);
		graphicView.update();
	}
	
	public void drawDeliveryRound(DeliveryRound deliveryRound) {
		graphicView.paintDeliveryRound(deliveryRound);
		graphicView.update();
	}
	
	public void enableLoadMap(boolean state) {
		loadMap.setEnabled(state);
	}
	
	public void enableLoadDeliveries(boolean state) {
		loadDeliveries.setEnabled(state);	
	}
	
	public void enableCompute(boolean state) {
		compute.setEnabled(state);
	}
	
	public void enableGenerateRoadmap(boolean state) {
		generateRoadmap.setEnabled(state);
	}
	
	public void disableAll() {
		enableLoadMap(false);
		enableLoadDeliveries(false);
		enableCompute(false);
		enableGenerateRoadmap(false);
	}
}
