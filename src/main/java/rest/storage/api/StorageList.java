package rest.storage.api;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import rest.storage.api.model.Owner;
import rest.storage.api.model.StorageNode;
import rest.storage.api.repository.FileRepository;



@Path("/list")
public class StorageList extends Base {
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("{path: [a-zA-Z0-9_/]+}")
	public List<StorageNode> listFilesAndFolders(@PathParam("path") String path) {
		path = path != null ? path = preparePath(path) : "/";
		
		// Get Current Owner
		Owner owner = getCurrentOwner();
		
		return FileRepository.getStorageNodesInFolder(path, owner);
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<StorageNode> listFilesAndFolderFromRoot() {
		// Get Current Owner
		Owner owner = getCurrentOwner();
		
		return FileRepository.getStorageNodesInFolder("/", owner);
	}
	
	private String preparePath(String path) {
		// IMPORTANT!!! leading / before every Path indicates absolute path
		if(!path.startsWith("/")) {
			path = "/" + path;
		}
				
		return path;
	}
}
