package rest.storage.api.repository;


import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.FilterOperator;

import rest.storage.api.helper.PathHelper;
import rest.storage.api.helper.ReflectionHelper;
import rest.storage.api.model.File;

public class FileRepository {
	public static File getByPathAndUser(String path, String user) throws EntityNotFoundException {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		Entity e = ds.get(PathHelper.getStorageKey("File", path, user));
		
		if(e == null)
			return null;
		
		File f = new File();
		ReflectionHelper.setPropertiesFromEntity(File.class, f, e);
		
		return f;
	}
	
	public static List<File> getFilesInFolder(String path, String user) {
		Query q = new Query("File");
		//db.GqlQuery("SELECT * FROM MyModel WHERE prop >= :1 AND prop < :2", "abc", u"abc" + u"\ufffd")
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		q.addFilter("path", FilterOperator.GREATER_THAN_OR_EQUAL, "/");
		q.addFilter("path", FilterOperator.LESS_THAN, "/" + "\ufffd");
		//q.addFilter("username", FilterOperator.EQUAL, user);
		PreparedQuery pq = ds.prepare(q);
		List<Entity> results = pq.asList(FetchOptions.Builder.withLimit(10));
		
		ArrayList<File> files = new ArrayList<File>();
		for(Entity e : results) {
			File f = new File();
			ReflectionHelper.setPropertiesFromEntity(File.class, f, e);
			
			files.add(f);
		}
		return files;
	}
	
	public static void delete(String path, String user) {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Key k = PathHelper.getStorageKey("File", path, user);
		
		ds.delete(k);
	}
	
	public static Key save(File f, String user) {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Entity entity = new Entity("File", PathHelper.getStorageKey(f.getPath(), user));
		
		entity = ReflectionHelper.setPropertiesToEntity(File.class, f, entity);
		
		Key k = ds.put(entity);
		
		return k;
	}
}
