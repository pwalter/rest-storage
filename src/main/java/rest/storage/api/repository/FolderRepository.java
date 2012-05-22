package rest.storage.api.repository;

import rest.storage.api.helper.PathHelper;
import rest.storage.api.helper.ReflectionHelper;
import rest.storage.api.model.File;
import rest.storage.api.model.Folder;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;

public class FolderRepository {
	public static void delete(String path, String user) {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Key k = PathHelper.getStorageKey("Folder", path, user);
		
		ds.delete(k);
	}
	
	public static Key save(Folder f, String user) {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Entity entity = new Entity("Folder", PathHelper.getStorageKey(f.getPath(), user));
		
		entity = ReflectionHelper.setPropertiesToEntity(File.class, f, entity);
		
		Key k = ds.put(entity);
		
		return k;
	}

	public static Folder getByPathAndUser(String path, String user) throws EntityNotFoundException {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		Entity e = ds.get(PathHelper.getStorageKey("Folder", path, user));
		
		if(e == null)
			return null;
		
		Folder f = new Folder();
		ReflectionHelper.setPropertiesFromEntity(Folder.class, f, e);
		
		return f;
	}
}
