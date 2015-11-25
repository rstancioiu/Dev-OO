package view;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class TextualView extends JList {

	public TextualView() {
		super();
		setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		//setLayoutOrientation(JList.HORIZONTAL_WRAP);
		setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(this);
		DefaultListModel model = new DefaultListModel();
		this.setModel(model);
		model.addElement("Jane Doe");
		model.addElement("John Smith");
		model.addElement("Kathy Green");
		setBorder(BorderFactory.createTitledBorder("Deliveries"));
	}

	public void StepOne() {

	}

	public void StepTwo() {

	}
}
