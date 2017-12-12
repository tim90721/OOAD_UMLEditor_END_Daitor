package ooad.model;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * custom file filter
 * @author daitor
 *
 */
public class CustomFileFilter extends FileFilter{
	private String _description;
	
	public CustomFileFilter(String description) {
		_description = description;
	}
	
	@Override
	public boolean accept(File f) {
		if(f.isDirectory())
			return true;
		String filename = f.toString();
		return filename.toLowerCase().endsWith("." + _description);
	}

	@Override
	public String getDescription() {
		return "*." + _description;
	}
	
	public String getType(){
		return _description;
	}
}
