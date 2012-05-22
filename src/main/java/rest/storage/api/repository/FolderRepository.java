package rest.storage.api.repository;

import java.util.ArrayList;
import java.util.List;

import rest.storage.api.helper.PathHelper;
import rest.storage.api.helper.ReflectionHelper;
import rest.storage.api.model.File;
import rest.storage.api.model.Folder;
import rest.storage.api.model.Owner;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

public class FolderRepository {
	public static void delete(String path, Owner owner) {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Key k = PathHelper.getStorageKey("Folder", path, owner);
		
		ds.delete(k);
	}
	
	public static Key save(Folder f, Owner owner) {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Entity entity = new Entity("Folder", PathHelper.getStorageKey(f.getPath(), owner));
		
		entity = ReflectionHelper.setPropertiesToEntity(Folder.class, f, entity);
		
		Key k = ds.put(entity);
		
		return k;
	}

	public static Folder getByPathAndUser(String path, Owner owner) throws EntityNotFoundException {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		Entity e = ds.get(PathHelper.getStorageKey("Folder", path, owner));
		
		if(e == null)
			return null;
		
		Folder f = new Folder();
		ReflectionHelper.setPropertiesFromEntity(Folder.class, f, e);
		
		return f;
	}

	public static boolean exists(String path, Owner owner) {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		try {
			Entity e = ds.get(PathHelper.getStorageKey("Folder", path, owner));
		} catch (EntityNotFoundException e) {
			return false;
		}
		
		return true;
	}

	public static List<Folder> getFoldersInFolder(String path, Owner owner) {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		ArrayList<Folder> folders = new ArrayList<Folder>();
		
		// TODO: implement!
		
		return folders;
	}
}
