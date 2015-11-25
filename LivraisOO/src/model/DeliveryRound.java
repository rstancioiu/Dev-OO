package model;

import java.util.ArrayList;

/**
 * Represents a delivery round, with a list of paths, a start hour, an end hour and a duration
 * 
 * @author Heptaswagnome
 *
 */
public class DeliveryRound {
	

	private String start;
	private String end;
	private int duration;
	private ArrayList<Path>paths;
	
	/**
	 * Constructor of DeliveryRound
	 */
	public DeliveryRound(){
		paths = new ArrayList<Path>();
	}
	
	/**
	 * Adds a delivery after the "previous" delivery
	 * @param previous
	 * @param newDelivery
	 */
	public void addDelivery(Delivery previous, Delivery newDelivery){
		Path ancientPath;
		for(int i = 0; i<paths.size(); i++){
			if(paths.get(i).getDeparture()==previous){
				ancientPath = paths.get(i);
				//TODO : calculer les deux nouveaux chemins, remplacer l'ancien par le premier et ajouter un deuxieme
				//Path path = new Path();
				break;
			}
		}
	}
	
	/**
	 * Deletes a delivery
	 * @param d
	 */
	public void deleteDelivery(Delivery d){
		Path path1 = null;
		Path path2 = null;
		for(int i = 0; i<paths.size(); i++){
			if(paths.get(i).getDeparture()==d){
				path2 = paths.get(i);
			}
			if(paths.get(i).getArrival()==d){
				path1 = paths.get(i);
			}
		}
		//TODO : calculer le nouveau chemin, remplacer path1 par le nouveau et supprimer path2
	}
	
	/**
	 * Swaps two deliveries
	 * @param first
	 * @param second
	 */
	public void swapDeliveries(Delivery first, Delivery second){
		//TODO : echanger les deux livraisons et recalculer les chemins
	}
	
	/**
	 * Paths setter
	 * @param p New paths
	 */
	public void setPaths (ArrayList<Path> p){
		paths = p;
	}
	
	/**
	 * Start time setter
	 * @param s New start time
	 */
	public void setStart (String s){
		start = s;
	}
	
	/**
	 * End time setter
	 * @param e New end time
	 */
	public void setEnd (String e){
		end = e;
	}
	
	/**
	 * Duration setter
	 * @param d New duration
	 */
	public void setDuration (int d){
		duration = d;
	}
	
	/**
	 * Returns the first unused ID for a delivery
	 * @return
	 */
	public int getNewID(){
		
		ArrayList<Integer> IDs = new ArrayList<Integer>();
		for(Path p : paths){
			IDs.add(p.getArrival().getId());
		}
		
		int ID = 1;
		while(IDs.indexOf(ID) != -1) ID++;
		
		return ID;
	}
}