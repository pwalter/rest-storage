package rest.storage.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;

import rest.storage.api.exception.FolderNotFoundException;
import rest.storage.api.helper.PathHelper;
import rest.storage.api.model.Folder;
import rest.storage.api.repository.FolderRepository;

@Path("/folder")
public class StorageFolder {
	@PUT
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/{path: [a-zA-Z0-9_/]+}")
	public Folder createFolder(@PathParam("path") String path) {
		path = perparePath(path);
		
		// Save Entry to Database
		Folder f = new Folder();
		f.setName(PathHelper.getFoldername(path));
		f.setPath(path);
		f.setOwnerId(4711);
		
		Key k = FolderRepository.save(f, "pwalter");
		
		return f;
	}
	
	@DELETE
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/{path: [a-zA-Z0-9_/]+}")
	public Response deleteFolder(@PathParam("path") String path) {
		path = perparePath(path);
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Key k = PathHelper.getStorageKey("Folder", path, "pwalter");
		
		try {
			ds.get(k);
		} catch (EntityNotFoundException e1) {
			throw new FolderNotFoundException();
		}
		
		ds.delete(k);
		
		return Response.status(200).build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/meta/{path: [a-zA-Z0-9_/]+}")
	public Folder getMetaData(@PathParam("path") String path) {
		path = perparePath(path);
		
		Folder f;
		try {
			f = FolderRepository.getByPathAndUser(path, "pwalter");
		} catch (EntityNotFoundException e) {
			throw new FolderNotFoundException();
		}
		
		return f;
	}
	
	private String perparePath(String path) {
		// Remove last /
		if(path.endsWith("/")) {
			path = path.substring(0, path.length() - 2);
		}
			
		// IMPORTANT!!! leading / before every Path indicates absolute path
		path = "/" + path;
		
		return path;
	}
}
