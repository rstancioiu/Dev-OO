package view;

import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import model.Delivery;
import model.TimeWindow;
import model.TypicalDay;

public class TypicalDayView extends JList {
	
	private DefaultListModel model;

	public TypicalDayView() {
		super();
		setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(this);
		model = new DefaultListModel();
		this.setModel(model);
		setBorder(BorderFactory.createTitledBorder("Deliveries"));
	}

	private void addElement(String label) {
		model.addElement(label);
	}
	
	private String formatHour(int seconds) {
		DecimalFormat formatter = new DecimalFormat("00");
		String result = formatter.format(seconds/3600) + ":"
					  + formatter.format(seconds%3600/60) + ":"
					  + formatter.format(seconds%60);
		return result;
	}
	
	public void listDeliveries(TypicalDay typicalDay) {
		model.clear();
		model.addElement("Warehouse : " + typicalDay.getWareHouse());
		for (TimeWindow t : typicalDay.getTimeWindows()) {
			for (Delivery d : t.getDeliveries()) {
				addElement(formatHour(t.getStart()) + " - " + formatHour(t.getEnd()) + " : " + d.getAddress());
			}
		}
	}

}
