package view;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class LabelView extends JLabel {

	public LabelView() {
		super();
		this.setVerticalAlignment(CENTER);
		setBorder(BorderFactory.createTitledBorder("Deliveries"));
	}

	public void StepOne() {
		this.setText("<html>Drop your deliveries plan here...</html>");
	}

	public void StepTwo() {

	}
}
