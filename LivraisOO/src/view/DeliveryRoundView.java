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

	private String formatHour(int seconds) {
		DecimalFormat formatter = new DecimalFormat("00");
		String result = formatter.format(seconds/3600) + ":"
				+ formatter.format(seconds%3600/60) + ":"
				+ formatter.format(seconds%60);
		return result;
	}

	public TimeWindow getWindowByDelivery(Delivery d, TypicalDay typicalDay) {
		for (TimeWindow t : typicalDay.getTimeWindows()) {
			for (Delivery d2 : t.getDeliveries()) {
				if(d==d2) {
					return t;
				}
			}
		}
		return new TimeWindow(-1, -1);
	}

	public void listDeliveryRound(DeliveryRound deliveryRound, TypicalDay typicalDay) {
		model.clear();
		int STOPTIME = 10;
		ArrayList<Path> paths = deliveryRound.getPaths();
		Path first = paths.get(0);
		int firstTime = getWindowByDelivery(first.getArrival(), typicalDay).getStart();
		int departure = (int) (firstTime - first.getDuration() + 1);
		model.addElement("Warehouse departure : " + formatHour(departure));
		departure -= STOPTIME;
		for (Path p : paths) {
			departure += STOPTIME + p.getDuration();
			Delivery arrival = p.getArrival();
			TimeWindow currentWindow = getWindowByDelivery(arrival, typicalDay);
			String line;
			line = "" + arrival.getAddress() + " at " + formatHour(departure);
			if(departure<currentWindow.getStart()) {
				int delta = currentWindow.getStart() - departure;
				line += " (wait " + formatHour(delta) + ")";
				departure += delta;
			} else if(departure>currentWindow.getEnd()) {
				int delta = departure - currentWindow.getEnd();
				line += " (late " + formatHour(delta) + ")";
			}
			if(p == paths.get(paths.size()-1)) {
				line = "Warehouse arrival : " + formatHour(departure);
			}

			model.addElement(line);
		}
	}

}
