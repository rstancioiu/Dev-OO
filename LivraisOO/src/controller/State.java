package controller;

import java.io.File;

import model.Plan;
import model.Node;
import view.Window;

public interface State {

	/**
	 * Method called by controller when the button 'Load Map' is clicked. It
	 * loads a map from a .XML file
	 * 
	 * @param mapFile
	 */
	public void loadMap(File mapFile);

	/**
	 * Method called by controller when the button 'Load Request' is cliked. It
	 * loads the deliveries request from a .XML file
	 * 
	 * @param requestFile
	 */
	public void loadDeliveries(File requestFile);

	/**
	 * Method called by controller when the button 'Compute Deliveries' is
	 * clicked. It computes the delivery round from a deliveries list
	 * 
	 * @param map
	 * @param deliveryRound
	 */
	public void computeDeliveries(Plan map, Node deliveryRound);

	/**
	 * Method called by controller when the button 'Add Delivery' is clicked. It
	 * adds a new delivery through a chosen node in the map with the add command
	 * 
	 * @param deliveryRound
	 * @param m
	 */
	public void addDelivery(Node deliveryRound, Plan m);

	/**
	 * Method called by controller when a delivery is selected
	 * 
	 * @param d
	 */
	public void modifyDelivery(Node d);

	/**
	 * Method called by controller when a delivery is selected and the button
	 * 'Delete Delivery' is hit
	 * 
	 * @param d
	 * @param deliveryRound
	 */
	public void deleteDelivery(Node deliveryRound, Node d);

	/**
	 * Method called by controller when two deliveries are selected and the
	 * button 'Swap Deliveries' is hit
	 * 
	 * @param d1
	 * @param d2
	 */
	public void modifyDelivery(Node deliveryRound, Node d1, Node d2);

	/**
	 * Method called by controller when the button 'Generate Roadmap' is hit
	 */
	public void generateRoadmap();

	/**
	 * Method called by controller after a right click
	 * 
	 * @param window
	 * @param cmdList
	 */
	public void rightClick(Window window, CommandsList cmdList);

}
