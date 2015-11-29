package view;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import model.Delivery;
import model.TimeWindow;
import model.TypicalDay;

public class TextualView extends JList {
	
	private DefaultListModel model;

	public TextualView() {
		super();
		setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		//setLayoutOrientation(JList.HORIZONTAL_WRAP);
		setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(this);
		model = new DefaultListModel();
		this.setModel(model);
		setBorder(BorderFactory.createTitledBorder("Deliveries"));
	}

	private void addElement(String label) {
		model.addElement(label);
	}
	
	public void listDeliveries(TypicalDay typicalDay) {
		for (TimeWindow t : typicalDay.getTimeWindows()) {
			for (Delivery d : t.getDeliveries()) {
				addElement(t.getStart() + "-" + t.getEnd() + " : " + d.getAddress());
			}
		}
	}

}
