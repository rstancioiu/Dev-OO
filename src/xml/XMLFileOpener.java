package xml;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;

/**
 * XMLFileOpener class, imported from PlaCo
 * Uses to open a XML file
 * Extends FileFilter, follow the Singleton pattern
 */
public class XMLFileOpener extends FileFilter {

	private static XMLFileOpener instance = null;

	/**
	 * Empty constructor
	 */
	private XMLFileOpener() {
		super();
	}

	/**
	 * Get the current instance of XMLFileOpener if it exists, create one otherwise
	 * 
	 * @return the XMLFileOpener instance
	 */
	protected static XMLFileOpener getInstance() {
		if (instance == null)
			instance = new XMLFileOpener();
		return instance;
	}

	/**
	 * Opens a file
	 * 
	 * @param read boolean indicating if the file has to be read
	 * @return a file
	 * @throws XMLException
	 */
	public File open(boolean read) throws XMLException {
		int returnVal;
		JFileChooser jFileChooserXML = new JFileChooser();
		jFileChooserXML.setFileFilter(this);
		jFileChooserXML.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (read)
			returnVal = jFileChooserXML.showOpenDialog(null);
		else
			returnVal = jFileChooserXML.showSaveDialog(null);
		if (returnVal != JFileChooser.APPROVE_OPTION)
			throw new XMLException("Problems when openning the file");
		return new File(jFileChooserXML.getSelectedFile().getAbsolutePath());
	}

	@Override
	public boolean accept(File f) {
		if (f == null)
			return false;
		if (f.isDirectory())
			return true;
		String extension = getExtension(f);
		if (extension == null)
			return false;
		return extension.contentEquals("xml");
	}

	@Override
	public String getDescription() {
		return "Fichier XML";
	}

	/**
	 * Get extension of the file
	 * 
	 * @param f filename
	 * @return a string representing the extension of the file
	 */
	private String getExtension(File f) {
		String filename = f.getName();
		int i = filename.lastIndexOf('.');
		if (i > 0 && i < filename.length() - 1)
			return filename.substring(i + 1).toLowerCase();
		return null;
	}
}
