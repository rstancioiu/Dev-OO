package controller;

import view.Window;

import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import model.DeliveryRound;
import model.Path;
import model.Section;

public class DeliveryState extends DefaultState {

	private String formatHour(int seconds) {
		DecimalFormat formatter = new DecimalFormat("00");
		String result = formatter.format(seconds/3600) + ":"
				+ formatter.format(seconds%3600/60) + ":"
				+ formatter.format(seconds%60);
		return result;
	}

	public void generateRoadmap(DeliveryRound deliveryRound) {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   

		int userSelection = fileChooser.showSaveDialog(null);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			System.out.println("Save as file: " + fileToSave.getAbsolutePath());

			try {
				int count = 1;
				FileWriter roadmap = new FileWriter(fileChooser.getSelectedFile());
				roadmap.write("ROADMAP \n START: " + formatHour(deliveryRound.getStart())
						+ " \n END: " + formatHour(deliveryRound.getEnd()) + "\n DURATION: "
						+ formatHour((int)deliveryRound.getDuration()) + "\n\n");
				ArrayList<Path> paths = deliveryRound.getPaths();
				for (Path p : paths){
					if(p == paths.get(paths.size()-1)) {
						roadmap.write("Homecoming: ");
						count++;
						roadmap.write(" \n Adress: " + Integer.toString(p.getArrival().getAddress()));
						roadmap.write("\n Arrival Time: " + formatHour((int)p.getArrival().getTime()));
						roadmap.write("\n Itinerary :");
						for (Section s: p.getSections())
							roadmap.write(Integer.toString(s.getDeparture()) + " - ");
						roadmap.write(Integer.toString(p.getArrival().getAddress()));
						break;
					}
					roadmap.write("Delivery number: " + Integer.toString(count));
					count++;
					roadmap.write(" \n Adress: " + Integer.toString(p.getArrival().getAddress()));
					roadmap.write("\n Client: " + Integer.toString(p.getDeparture().getClient()));
					roadmap.write("\n Arrival Time: " + formatHour((int)p.getArrival().getTime()));
					int doorTime = Math.max(p.getArrival().getTime(),p.getArrival().getTimeWindow().getStart());
					roadmap.write("\n Departure Time " + formatHour((int)(doorTime)+600));
					roadmap.write("\n Itinerary :");
					for (Section s: p.getSections())
						roadmap.write(Integer.toString(s.getDeparture()) + " - ");
					roadmap.write(Integer.toString(p.getArrival().getAddress()));
					roadmap.write("\n\n");
				}
				roadmap.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void clickAddButton(){
		Controller.setCurrentState(Controller.additionState);
	}

	public void clickDeleteButton(){
		Controller.setCurrentState(Controller.deleteState);
	}

	public void clickSwapButton(){
		Controller.setCurrentState(Controller.swapState);
	}

	public void updateVue(Window window){
		window.hideButtons();
		window.enableAddButton(true);
		window.enableDeleteButton(true);
		window.enableSwapButton(true);
		window.enableGenerateRoadmap(true);
		window.enableUndoButton(true);
		window.enableRedoButton(true);
	}

}
