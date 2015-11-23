package view;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class LabelView extends JLabel {

	public LabelView(Window w) {
		super();
		this.setVerticalAlignment(CENTER);
		setBorder(BorderFactory.createTitledBorder("Deliveries"));
		w.add(this);
	}

	public void StepOne() {
		this.setText("<html>Drop your deliveries plan here...</html>");
	}

	public void StepTwo() {

	}
}
