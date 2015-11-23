package controller;

import java.io.File;

import model.Map;
import model.Delivery;
import model.Node;
import view.window

public interface State {
	
	/**
	 * Method called by controller when the button 'Load Map' is hit
	 * It loads a map from a .XML file
	 * @param mapFile
	 */
	public void loadMap(File mapFile);
	
	/**
	 * Method called by controller when the button 'Load Request' is hit
	 * It loads the deliveries request from a .XML file
	 * @param requestFile
	 */
	public void loadDeliveries(File requestFile);
	
	/**
	 * Method called by controller when the button 'Compute Deliveries' is hit
	 * It computes the delivery round from a deliveries list
	 * @param map
	 * @param deliveryRound
	 */
	public void computeDeliveries(Map map, Delivery deliveryRound);
	
	/**
	 * Method called by controller when the button 'Add Delivery' is hit
	 * It adds a new delivery through a chosen node in the map with the add command
	 * @param deliveryRound
	 * @param m
	 */
	public void addDelivery(Delivery deliveryRound, Map m);
	
	/**
	 * Method called by controller when a delivery is selected
	 * @param d
	 */
	public void modifyDelivery(Delivery d);
	
	/**
	 * Method called by controller when a delivery is selected and the button 'Delete Delivery' is hit
	 * @param d
	 * @param deliveryRound
	 */
	public void deleteDelivery(Delivery deliveryRound, Delivery d);
	
	/**
	 * Method called by controller when two deliveries are selected and the button 'Swap Deliveries' is hit
	 * @param d1
	 * @param d2
	 */
	public void modifyDelivery(Delivery deliveryRound, Delivery d1, Delivery d2);
	
	/**
	 * Method called by controller when the button 'Generate Roadmap' is hit
	 */
	public void generateRoadmap();
	
	/**
	 * Method called by controller after a right click
	 * @param window
	 * @param cmdList
	 */
	public void clicDroit(Window window, CommandsList cmdList);
	
	
	
	
	
	
	
	

}
