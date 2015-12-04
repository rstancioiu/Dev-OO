package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Controller;
import model.CityMap;
import model.Delivery;
import model.DeliveryRound;
import model.Node;
import model.TypicalDay;

/**
 * Window class, directly manages the main window and its lower right bloc.
 * Includes a DeliveryRoundView, a GraphicView and a TypicalDayView
 * Extends JFrame
 */
public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	private GraphicView graphicView;
	private TypicalDayView typicalDayView;
	private DeliveryRoundView deliveryRoundView;
	private Controller controller;
	private JButton loadMap, loadDeliveries, compute, generateRoadmap;
	private JButton addButton, deleteButton, swapButton, confirmButton, cancelButton, undoButton, redoButton;
	private JLabel messageBox;

	/**
	 * Constructor of the window
	 * 
	 * @param controller application's controller
	 */
	public Window(Controller controller) {
		this.controller = controller;
		createAndShowGui();
	}

	/**
	 * Create and show the GUI
	 */
	private void createAndShowGui() {
		setSize(1000, 600);
		setMinimumSize(new Dimension(900, 500));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(1000, 700));
		GroupLayout layout = new GroupLayout(panel);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		panel.setLayout(layout);

		messageBox = new JLabel(" ");
		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.clickAddButton();
			}
		});

		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.clickDeleteButton();
			}
		});

		swapButton = new JButton("Swap");
		swapButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.clickSwapButton();
			}
		});

		confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Node> nodes = graphicView.getSelectedNodes();
				ArrayList<Delivery> deliveries = graphicView.getSelectedDeliveries();
				if (nodes.size() >= 1 && deliveries.size() >= 1) {
					controller.confirmAdd(deliveries.get(deliveries.size() - 1), nodes.get(nodes.size() - 1));
				}
				if (deliveries.size() >= 1) {
					controller.confirmDelete(deliveries.get(deliveries.size() - 1));
				}
				if (deliveries.size() >= 2) {
					controller.confirmSwap(deliveries.get(deliveries.size() - 2),
							deliveries.get(deliveries.size() - 1));
				}
				graphicView.clearNodes();
			}
		});

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.cancel();
				graphicView.clearNodes();
			}
		});

		undoButton = new JButton("Undo");
		undoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.undo();
			}
		});

		redoButton = new JButton("Redo");
		redoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.redo();
			}
		});

		hideButtons();
		graphicView = new GraphicView(messageBox, addButton, deleteButton, swapButton, confirmButton, cancelButton,
				undoButton, redoButton);
		typicalDayView = new TypicalDayView();
		deliveryRoundView = new DeliveryRoundView();
		loadMap = new JButton("Load Map");
		loadMap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.loadMap();
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
				controller.generateRoadmap();
			}
		});

		JScrollPane typicalDayScroll = new JScrollPane(typicalDayView);
		JScrollPane deliveryRoundScroll = new JScrollPane(deliveryRoundView);
		deliveryRoundScroll.setBorder(BorderFactory.createTitledBorder("Round"));
		typicalDayScroll.setBorder(BorderFactory.createTitledBorder("Deliveries"));

		layout.setHorizontalGroup(layout.createSequentialGroup().addComponent(graphicView)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(typicalDayScroll)
						.addComponent(deliveryRoundScroll).addComponent(compute).addComponent(generateRoadmap)
						.addComponent(loadMap).addComponent(loadDeliveries)));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(graphicView)
						.addGroup(layout.createSequentialGroup().addComponent(typicalDayScroll)
								.addComponent(deliveryRoundScroll).addComponent(compute).addComponent(generateRoadmap)
								.addComponent(loadMap).addComponent(loadDeliveries))));

		typicalDayScroll.setMinimumSize(new Dimension(300, 150));
		typicalDayScroll.setMaximumSize(new Dimension(300, 400));
		deliveryRoundScroll.setMinimumSize(new Dimension(300, 150));
		deliveryRoundScroll.setMaximumSize(new Dimension(300, 400));

		loadMap.setMinimumSize(new Dimension(150, 20));
		loadDeliveries.setMinimumSize(new Dimension(150, 20));
		compute.setMinimumSize(new Dimension(150, 20));
		generateRoadmap.setMinimumSize(new Dimension(150, 20));

		panel.setVisible(true);
		getContentPane().add(panel);
		setVisible(true);
	}

	/**
	 * Draw a plan
	 * 
	 * @param map map of the city
	 */
	public void drawMap(CityMap map) {
		deliveryRoundView.clear();
		typicalDayView.clear();
		graphicView.clear();
		graphicView.paintMap(map);
		graphicView.update();
	}

	/**
	 * Draw the list of deliveries
	 * 
	 * @param typicalDay typical day containing the list of deliveries
	 */
	public void drawDeliveries(TypicalDay typicalDay) {
		graphicView.paintDeliveries(typicalDay);
		typicalDayView.listDeliveries(typicalDay);
		graphicView.update();
	}

	/**
	 * Draw the delivery round
	 * 
	 * @param deliveryRound drawn delivery round
	 * @param typicalDay typical day containing the delivery round
	 */
	public void drawDeliveryRound(DeliveryRound deliveryRound, TypicalDay typicalDay) {
		graphicView.paintDeliveryRound(deliveryRound);
		typicalDayView.listDeliveries(typicalDay);
		deliveryRoundView.listDeliveryRound(deliveryRound, typicalDay);
		graphicView.update();
	}

	/**
	 * Enable/disable the loading of a map
	 * 
	 * @param state set this state to the LoadMap button
	 */
	public void enableLoadMap(boolean state) {
		loadMap.setEnabled(state);
	}

	/**
	 * Enable/disable the loading of deliveries
	 * 
	 * @param state set this state to the LoadDeliveries button
	 */
	public void enableLoadDeliveries(boolean state) {
		loadDeliveries.setEnabled(state);
	}

	/**
	 * Enable/disable the computing of a delivery round
	 * 
	 * @param state set this state to the Compute button
	 */
	public void enableCompute(boolean state) {
		compute.setEnabled(state);
	}

	/**
	 * Enable/disable the generation of a road map
	 * 
	 * @param state set this state to the GenerateRoadMap button
	 */
	public void enableGenerateRoadmap(boolean state) {
		generateRoadmap.setEnabled(state);
	}

	/**
	 * Enable/disable the addition of a new delivery
	 * 
	 * @param state set this state to the AddButton button
	 */
	public void enableAddButton(boolean state) {
		addButton.setEnabled(state);
	}

	/**
	 * Enable/disable the deletion of a delivery
	 * 
	 * @param state set this state to the DeleteButton button
	 */
	public void enableDeleteButton(boolean state) {
		deleteButton.setEnabled(state);
	}

	/**
	 * Enable/disable the swapping of two deliveries
	 * 
	 * @param state set this state to the SwapButton button
	 */
	public void enableSwapButton(boolean state) {
		swapButton.setEnabled(state);
	}

	/**
	 * Enable/disable the undo of a command
	 * 
	 * @param state set this state to the UndoButton button
	 */
	public void enableUndoButton(boolean state) {
		undoButton.setEnabled(state);
	}

	/**
	 * Enable/disable the redo of a command
	 * 
	 * @param state set this state to the RedoButton button
	 */
	public void enableRedoButton(boolean state) {
		redoButton.setEnabled(state);
	}

	/**
	 * Disable all buttons
	 */
	public void disableAll() {
		enableCompute(false);
		enableGenerateRoadmap(false);
		enableAddButton(false);
		enableDeleteButton(false);
		enableSwapButton(false);
		enableLoadDeliveries(false);
		undoButton.setEnabled(false);
		redoButton.setEnabled(false);
	}

	/**
	 * Set a message in the message box
	 * 
	 * @param message set this message to the message box
	 */
	public void setMessage(String message) {
		messageBox.setText(message);
	}

	/**
	 * Show confirm/cancel buttons
	 */
	public void showButtons() {
		confirmButton.setVisible(true);
		confirmButton.setEnabled(true);
		cancelButton.setVisible(true);
		cancelButton.setEnabled(true);
		graphicView.clearNodes();
	}

	/**
	 * Hide confirm/cancel buttons
	 */
	public void hideButtons() {
		confirmButton.setVisible(false);
		confirmButton.setEnabled(false);
		cancelButton.setVisible(false);
		cancelButton.setEnabled(false);
	}
	
	/**
	 * Clear deliveries
	 */
	public void clearDeliveries(){
		deliveryRoundView.clear();
		graphicView.clear();
	}
}
