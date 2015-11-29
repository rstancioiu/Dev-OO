package view;

import java.awt.BasicStroke;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import model.Delivery;
import model.DeliveryRound;
import model.Node;
import model.Path;
import model.Section;
import model.TimeWindow;
import model.TypicalDay;

public class DeliveryRoundView extends JList {
	
	private DefaultListModel model;

	public DeliveryRoundView() {
		super();
		setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(this);
		model = new DefaultListModel();
		this.setModel(model);
		setBorder(BorderFactory.createTitledBorder("Round"));
	}

	private void addElement(String label) {
		model.addElement(label);
	}
	
	public void listDeliveryRound(DeliveryRound deliveryRound) {
		for (Path p : deliveryRound.getPaths()) {
			for (Section s : p.getSections()) {
				
			}
		}
	}

}
