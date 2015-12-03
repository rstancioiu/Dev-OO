package view;

import java.text.DecimalFormat;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import model.Delivery;
import model.TimeWindow;
import model.TypicalDay;

/**
 * TypicalDayView class, viewer of a typical day. Manages the upper right bloc off the window
 * Extends JList
 */
public class TypicalDayView extends JList {

	private DefaultListModel model;

	/**
	 * Constructor for the list of deliveries of a typical day
	 */
	public TypicalDayView() {
		super();
		setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(this);
		model = new DefaultListModel();
		this.setModel(model);
	}

	/**
	 * Add an element to the list of deliveries
	 * 
	 * @param label new element
	 */
	private void addElement(String label) {
		model.addElement(label);
	}

	/**
	 * Generate a formated hour from a number of seconds
	 * 
	 * @param seconds number of seconds
	 * @return a formated hour string
	 */
	private String formatHour(int seconds) {
		DecimalFormat formatter = new DecimalFormat("00");
		String result = formatter.format(seconds / 3600) + ":" + formatter.format(seconds % 3600 / 60) + ":"
				+ formatter.format(seconds % 60);
		return result;
	}

	/**
	 * Update the list of deliveries
	 * 
	 * @param typicalDay this typical day deliveries list will be updated
	 */
	public void listDeliveries(TypicalDay typicalDay) {
		model.clear();
		model.addElement("Warehouse : " + typicalDay.getWareHouse());
		for (TimeWindow t : typicalDay.getTimeWindows()) {
			for (Delivery d : t.getDeliveries()) {
				addElement(formatHour(t.getStart()) + " - " + formatHour(t.getEnd()) + " : " + d.getAddress());
			}
		}
	}
	
	/**
	 * Clear the model of a typical day view
	 */
	public void clear(){
		model.clear();
	}

}
