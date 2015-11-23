package controller;

public interface Command {

	/**
	 * Execute the 'this' command
	 */
	void doCmd();
	
	/**
	 * Execute the 'this' reverse command
	 */
	void undoCmd();
}


