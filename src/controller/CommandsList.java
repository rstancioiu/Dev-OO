package controller;

import java.util.LinkedList;

/**
 * CommandList class, contains a list of executed commands
 * Facilitates undo/redo implementation
 */
public class CommandsList {
	private LinkedList<Command> liste;
	private int indexCrt;

	/**
	 * Constructor of the list of commands
	 */
	public CommandsList() {
		indexCrt = -1;
		liste = new LinkedList<Command>();
	}

	/**
	 * Add a command to the list
	 * @param c command to add
	 */
	public void add(Command c) {
		for (int i = indexCrt + 1; i < liste.size(); i++)
			liste.remove(i);
		indexCrt++;
		liste.add(indexCrt, c);
	}

	/**
	 * Temporarily delete the last added command
	 * This command can return to the list
	 */
	public void undo() {
		if (indexCrt >= 0) {
			Command Cmd = liste.get(indexCrt);
			indexCrt--;
			Cmd.undoCmd();
		}
	}

	/**
	 * Permanently delete the last added command from the list
	 * this command can't return to the list
	 */
	public void remove() {
		if (indexCrt >= 0) {
			Command Cmd = liste.get(indexCrt);
			liste.remove(indexCrt);
			indexCrt--;
			Cmd.undoCmd();
		}
	}

	/**
	 * Add back to the list the last undone command
	 */
	public void redo() {
		if (indexCrt < liste.size() - 1) {
			indexCrt++;
			Command Cmd = liste.get(indexCrt);
			Cmd.doCmd();
		}
	}

	/**
	 * Reset the list, remove every commands
	 */
	public void reset() {
		while (indexCrt >= 0) {
			liste.remove(indexCrt);
			indexCrt--;
		}
	}
}
