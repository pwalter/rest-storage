package rest.storage.api.helper;

import javax.activation.MimetypesFileTypeMap;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class PathHelper {
	
	// Extension ********************
	public static String getExtension(String path) {
		
	    return getExtension(path, ".");
	}
	
	public static String getExtension(String path, String seperator) {
		int dot = path.lastIndexOf(seperator);
	    return path.substring(dot + 1);
	}
	
	
	// Filename ********************
	public static String getFilename(String path) {
	    return getFilename(path, "/");
	}
	
	public static String getFilename(String path, String folderSeperator) {
	    int sep = path.lastIndexOf(folderSeperator);
	    return path.substring(sep + 1);
	}
	
	// Mime-Type ********************
	public static String getMimetype(String path) {
		MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
		return mimeTypesMap.getContentType(path);
	}
	
	// Storage Key
	public static String getStorageKey(String path, String username) {
		//System.out.println(String.format("Path: %s || Username: %s || SHA1: %s", path, username, HashHelper.getSHA1(path + username)));
		return HashHelper.getSHA1(path + username);
	}
	
	public static Key getStorageKey(String kind, String path, String username) {
		//System.out.println(String.format("Kind: %s || Path: %s || Username: %s || SHA1: %s", kind, path, username, getStorageKey(path, username)));
		return KeyFactory.createKey(kind, getStorageKey(path, username));
	}
}
