package controller;

import graph.Graph;
import model.Delivery;
import model.DeliveryRound;

/**
 * AbstractCommand abstract class, implementing ICommand interface, extended by commands
 * Contains a delivery, a delivery round and a graph
 */
public abstract class AbstractCommand implements Command {

	protected Delivery delivery;
	protected DeliveryRound deliveryRound;
	protected Graph graph;

	/**
	 * Constructor of command
	 * @param d delivery attached to the command
	 * @param dr delivery round attached to the command
	 * @param g graph attached to the command
	 */
	public AbstractCommand(Delivery d, DeliveryRound dr, Graph g) {
		this.delivery = d;
		this.deliveryRound = dr;
		this.graph = g;
	}

	@Override
	public abstract void doCmd();

	@Override
	public abstract void undoCmd();

}
