package xml;

/**
 * XMLException class, used to create a new exception thrown when the application detects an error in a XML file
 * Extends Exception
 */
public class XMLException extends Exception {

	/**
	 * Create an exception with a message
	 * 
	 * @param message the message sent
	 */
	public XMLException(String message) {
		super(message);
	}
}
