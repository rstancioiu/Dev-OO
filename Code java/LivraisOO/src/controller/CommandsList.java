package controller;

import java.util.LinkedList;

public class CommandsList {
	private LinkedList<Command> liste;
	private int indexCrt;
	
	public CommandsList(){
		indexCrt = -1;
		liste = new LinkedList<Command>();
	}
	
	/**
	 * Add the command this to the list
	 * @param c
	 */
	public void add(Command c){
		for (int i=indexCrt+1; i<liste.size(); i++)
			liste.remove(i);
		indexCrt++;
		liste.add(indexCrt, c);
		c.doCmd();
	}
	
	/**
	 * Temporarily delete the last add command (this Command can return to the list with the redo)
	 */
	public void undo(){
		if (indexCrt >= 0){
			Command Cmd = liste.get(indexCrt);
			indexCrt--;
			Cmd.undoCmd();
		}
	}
	
	/**
	 * Permanently delete the last add command from the list(this Command can't go back to the list with redo)
	 */
	public void remove(){
		if (indexCrt >= 0){
			Command Cmd = liste.get(indexCrt);
			liste.remove(indexCrt);
			indexCrt--;
			Cmd.undoCmd();
		}
	}

	/**
	 * Add back to the list the last delete command with undo
	 */
	public void redo(){
		if (indexCrt < liste.size()-1){
			indexCrt++;
			Command Cmd = liste.get(indexCrt);
			Cmd.doCmd();
		}
	}
	
	/**
	 * Permanently delete all the commands from the list
	 */
	public void reset(){
		while (indexCrt >=0){
			liste.remove(indexCrt);
			indexCrt--;
		}
	}

}
