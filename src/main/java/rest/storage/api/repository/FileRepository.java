package rest.storage.api.repository;


import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.FilterOperator;

import rest.storage.api.helper.PathHelper;
import rest.storage.api.helper.ReflectionHelper;
import rest.storage.api.model.File;
import rest.storage.api.model.Folder;
import rest.storage.api.model.Owner;
import rest.storage.api.model.StorageNode;

public class FileRepository {
	public static File getByPathAndUser(String path, Owner owner) throws EntityNotFoundException {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		Entity e = ds.get(PathHelper.getStorageKey("File", path, owner));
		
		if(e == null)
			return null;
		
		File f = new File();
		ReflectionHelper.setPropertiesFromEntity(File.class, f, e);
		
		return f;
	}
	
	public static List<StorageNode> getStorageNodesInFolder(String path, Owner owner) {
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		ArrayList<StorageNode> objects = new ArrayList<StorageNode>();
		
		// Read folders
		Query q2 = new Query("Folder");
		q2.addFilter("path", FilterOperator.GREATER_THAN_OR_EQUAL, path);
		q2.addFilter("path", FilterOperator.LESS_THAN, path + "\ufffd");
		q2.addFilter("ownerId", FilterOperator.EQUAL, owner.getId());
		
		PreparedQuery pq2 = ds.prepare(q2);
		List<Entity> results2 = pq2.asList(FetchOptions.Builder.withLimit(99999));
		for(Entity e : results2) {
			Folder f = new Folder();
			ReflectionHelper.setPropertiesFromEntity(Folder.class, f, e);
			
			objects.add(f);
		}
		
		// Read files
		Query q = new Query("File");
		q.addFilter("path", FilterOperator.GREATER_THAN_OR_EQUAL, path);
		q.addFilter("path", FilterOperator.LESS_THAN, path + "\ufffd");
		q.addFilter("ownerId", FilterOperator.EQUAL, owner.getId());
		
		PreparedQuery pq = ds.prepare(q);
		List<Entity> results = pq.asList(FetchOptions.Builder.withLimit(99999));
		for(Entity e : results) {
			File f = new File();
			ReflectionHelper.setPropertiesFromEntity(File.class, f, e);
			
			objects.add(f);
		}
		
		return objects;
	}
	
	public static void delete(String path, Owner owner) {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Key k = PathHelper.getStorageKey("File", path, owner);
		
		ds.delete(k);
	}
	
	public static Key save(File f, Owner owner) {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Entity entity = new Entity("File", PathHelper.getStorageKey(f.getPath(), owner));
		
		entity = ReflectionHelper.setPropertiesToEntity(File.class, f, entity);
		
		Key k = ds.put(entity);
		
		return k;
	}
}
