package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

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

	/**
	 * Constructor of the class
	 */
	public DeliveryRoundView() {
		super();
		setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(this);
		model = new DefaultListModel();
		this.setModel(model);
	}

	/**
	 * Add an element to the list of delivery round
	 * 
	 * @param label
	 */
	private void addElement(String label) {
		model.addElement(label);
	}

	/**
	 * Generate a formated hour from a number of seconds
	 * 
	 * @param seconds
	 * @return formated hour string
	 */
	private String formatHour(int seconds) {
		DecimalFormat formatter = new DecimalFormat("00");
		String result = formatter.format(seconds / 3600) + ":" + formatter.format(seconds % 3600 / 60) + ":"
				+ formatter.format(seconds % 60);
		return result;
	}

	/**
	 * @param d
	 * @param typicalDay
	 * @return a TimeWindow by a delivery d
	 */
	public TimeWindow getWindowByDelivery(Delivery d, TypicalDay typicalDay) {
		for (TimeWindow t : typicalDay.getTimeWindows()) {
			for (Delivery d2 : t.getDeliveries()) {
				if (d == d2) {
					return t;
				}
			}
		}
		return new TimeWindow(-1, -1);
	}

	/**
	 * Update the list of deliveries of a delivery round
	 * 
	 * @param deliveryRound
	 * @param typicalDay
	 */
	public void listDeliveryRound(DeliveryRound deliveryRound, TypicalDay typicalDay) {
		model.clear();
		deliveryRound.updateTimes();
		ArrayList<Path> paths = deliveryRound.getPaths();
		int firstTime = deliveryRound.getStart();
		model.addElement("Warehouse departure : " + formatHour(firstTime));
		for (Path p : paths) {
			int time = p.getArrival().getTime();
			String line = "" + p.getArrival().getAddress() + " at " + formatHour(time);
			if (time < p.getArrival().getTimeWindow().getStart()) {
				int delta = p.getArrival().getTimeWindow().getStart() - time;
				line += " (wait " + formatHour(delta) + ")";
			} else if (time > p.getArrival().getTimeWindow().getEnd()) {
				int delta = time - p.getArrival().getTimeWindow().getEnd();
				line += " (late " + formatHour(delta) + ")";
			}
			if (p == paths.get(paths.size() - 1)) {
				line = "Warehouse arrival : " + formatHour(time);
			}
			model.addElement(line);
		}
	}
	
	/**
	 * Clear the model of delivery round view
	 */
	public void clear(){
		model.clear();
	}

}
