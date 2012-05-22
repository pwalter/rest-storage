package rest.storage.api;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;

import rest.storage.api.exception.FolderNotFoundException;
import rest.storage.api.helper.PathHelper;
import rest.storage.api.model.File;
import rest.storage.api.model.Folder;
import rest.storage.api.model.Owner;
import rest.storage.api.repository.FileRepository;
import rest.storage.api.repository.FolderRepository;

@Path("/folder")
public class StorageFolder extends Base {
	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{path: [a-zA-Z0-9_/]+}")
	public Folder createFolder(@PathParam("path") String path) {
		path = perparePath(path);
		
		// Get Current Owner
		Owner owner = getCurrentOwner();
		
		// Save Entry to Database
		Folder f = new Folder();
		f.setName(PathHelper.getFoldername(path));
		f.setPath(path);
		f.setOwnerId(4711);
		
		Key k = FolderRepository.save(f, owner);
		
		return f;
	}
	
	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{path: [a-zA-Z0-9_/]+}")
	public Response deleteFolder(@PathParam("path") String path) {
		path = perparePath(path);
		
		// Get Current Owner
		Owner owner = getCurrentOwner();
		
		// TODO: Check if there are any Files in this folder and throw FolderNotEmptyException otherwise
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Key k = PathHelper.getStorageKey("Folder", path, owner);
		
		try {
			ds.get(k);
		} catch (EntityNotFoundException e1) {
			throw new FolderNotFoundException();
		}
		
		ds.delete(k);
		
		return Response.status(200).build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/meta/{path: [a-zA-Z0-9_/]+}")
	public Folder getMetaData(@PathParam("path") String path) {
		path = perparePath(path);
		
		// Get Current Owner
		Owner owner = getCurrentOwner();
		
		Folder f;
		try {
			f = FolderRepository.getByPathAndUser(path, owner);
		} catch (EntityNotFoundException e) {
			throw new FolderNotFoundException();
		}
		
		return f;
	}
	
	private String perparePath(String path) {
		// Remove last /
		if(path.endsWith("/")) {
			path = path.substring(0, path.lastIndexOf("/") - 1);
		}
			
		// IMPORTANT!!! leading / before every Path indicates absolute path
		if(!path.startsWith("/")) {
			path = "/" + path;
		}
		
		return path;
	}
}
