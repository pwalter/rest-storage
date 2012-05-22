package rest.storage.api.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rest.storage.api.helper.ReflectionHelper;
import rest.storage.api.model.Folder;
import rest.storage.api.model.Owner;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class OwnerRepository {
	static Logger log = LoggerFactory.getLogger(OwnerRepository.class);
	
	public static Owner getByIdentifier(String identifier) throws EntityNotFoundException {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Key k = KeyFactory.createKey("Owner", identifier);
		
		Entity e = ds.get(k);
		
		Owner f = new Owner();
		ReflectionHelper.setPropertiesFromEntity(Owner.class, f, e);
		
		return f;
	}

	public static Key save(Owner owner) {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Entity entity = new Entity("Owner", owner.getIdentifier());
		
		entity = ReflectionHelper.setPropertiesToEntity(Owner.class, owner, entity);
		
		Key k = ds.put(entity);
		
		return k;
	}
}
