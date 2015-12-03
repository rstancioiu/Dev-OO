package controller;

/**
 * Command interface. Defines a pattern followed by every command class
 */
public interface Command {

	/**
	 * Execute the command
	 */
	public void doCmd();

	/**
	 * Execute the reversed command
	 */
	public void undoCmd();
}
