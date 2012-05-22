package rest.storage.api.helper;

import javax.activation.MimetypesFileTypeMap;

import rest.storage.api.model.Owner;

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
	
	// Foldername ********************
	public static String getFoldername(String path) {
		return getFoldername(path, "/");
	}
	
	public static String getFoldername(String path, String folderSeperator) {
		path = getFolderpath(path);
		
		int sep = path.lastIndexOf(folderSeperator);
	    return path.substring(sep + 1);
	}
	
	// Folderpath ********************
	public static String getFolderpath(String path) {
		return getFolderpath(path, "/");
	}
	
	public static String getFolderpath(String path, String folderSeperator) {
		if(!path.startsWith(folderSeperator)) {
			path = folderSeperator + path;
		}
		
		// Remove filename
		// [/abc/de]/test.txt
		if(path.lastIndexOf(".") != -1) {
			path = path.substring(0, path.lastIndexOf(folderSeperator) + 1);
		}
		
		// Remove last / ----- ex.: /hgfdsa/bnm/ ==> /hgfdsa/bnm
		if(path.endsWith(folderSeperator) && path.lastIndexOf(folderSeperator) != 0) {
			path = path.substring(0, path.length() - 1);
		}
		
		return path;
	}
	
	// Mime-Type ********************
	public static String getMimetype(String path) {
		MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
		return mimeTypesMap.getContentType(path);
	}
	
	// Storage Key ********************
	public static String getStorageKey(String path, Owner owner) {
		//System.out.println(String.format("Path: %s || Username: %s || SHA1: %s", path, username, HashHelper.getSHA1(path + username)));
		return HashHelper.getSHA1(path + owner.getName());
	}
	
	public static Key getStorageKey(String kind, String path, Owner owner) {
		//System.out.println(String.format("Kind: %s || Path: %s || Username: %s || SHA1: %s", kind, path, username, getStorageKey(path, username)));
		return KeyFactory.createKey(kind, getStorageKey(path, owner));
	}
}
