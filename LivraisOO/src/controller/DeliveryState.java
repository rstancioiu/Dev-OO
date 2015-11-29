package controller;

import view.Window;

import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;

import model.DeliveryRound;
import model.Path;
import model.Section;

public class DeliveryState extends DefaultState {
	
	public void generateRoadmap(DeliveryRound deliveryRound) {
		 
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		 
		int userSelection = fileChooser.showSaveDialog(null);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		    
		    try {
		    	int count = 1;
		    	double duration = 0;
	            FileWriter roadmap = new FileWriter(fileChooser.getSelectedFile()+".txt");
	            // write file header
	            roadmap.write("ROADMAP \n START: " + deliveryRound.getStart() + " \n END: " + deliveryRound.getEnd() + "\n DURANTION: " + Double.toString(deliveryRound.getDuration()));
	            // write deliveries
	            for (Path p : deliveryRound.getPaths()){
	            	// Describe the delivery:  order, adress and client
	            	roadmap.write("Delivery number: " + Integer.toString(count));
	            	count++;
	            	roadmap.write(" \n Adress: " + Integer.toString(p.getArrival().getAddress()));
	            	roadmap.write("\n Client: " + Integer.toString(p.getDeparture().getClient()));
	            	//Increase the variable duration with the duration of the current path
	            	duration += p.getDuration();
	            	roadmap.write("\n Arrival Time: " + Double.toString(duration) );
	            	// departure time is arrival time plus the time of delivery (10 minutes)
	            	duration+=600;
	            	roadmap.write("\n Departure Time " + Double.toString(duration));
	            	//itinerary is the sequence of sections. Get the departure of each section
	            	roadmap.write("\n Itinerary :");
	            	for (Section s: p.getSections())
	            		roadmap.write(Integer.toString(s.getDeparture()) + " - ");
	            	//add the last arrival of the path (it was not included in the departures)
	            	roadmap.write(Integer.toString(p.getArrival().getAddress()));
	            	roadmap.write("\n\n");
	            }
	            roadmap.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }   
		}
	}

	
	public void updateVue(Window window){
	}

}
