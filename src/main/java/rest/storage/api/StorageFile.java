package rest.storage.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rest.storage.api.exception.FileNotFoundException;
import rest.storage.api.exception.OwnerNotFoundException;
import rest.storage.api.helper.PathHelper;
import rest.storage.api.helper.StreamHelper;
import rest.storage.api.model.File;
import rest.storage.api.model.Folder;
import rest.storage.api.model.Owner;
import rest.storage.api.model.StorageNode;
import rest.storage.api.repository.FileRepository;
import rest.storage.api.repository.FolderRepository;
import rest.storage.api.repository.OwnerRepository;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileReadChannel;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import com.google.appengine.api.files.LockException;



@Path("/file")
public class StorageFile extends Base {
	// File download *********************************************************
	@GET
	//@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	@Path("/{path: [a-zA-Z0-9_/.]+}")
	public Response downloadFile(@PathParam("path") String path) throws java.io.FileNotFoundException, LockException, IOException {
		path = preparePath(path);
		
		// Get Current Owner
		Owner owner = getCurrentOwner();
		
		File f;
		try {
			f = FileRepository.getByPathAndUser(path, owner);
		} catch (EntityNotFoundException e) {
			throw new FileNotFoundException();
		}
		
		FileService fs = FileServiceFactory.getFileService();
		AppEngineFile file = new AppEngineFile((String) f.getStoragePath());
		FileReadChannel channel = fs.openReadChannel(file, false);
		
		byte[] bytes = StreamHelper.getBytes(channel);
		
		return Response.ok(bytes).header("content-disposition","attachment; filename = " + f.getFilename()).build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/meta/{path: [a-zA-Z0-9_/.]+}")
	public File showFileMetadata(@PathParam("path") String path) {
		path = preparePath(path);
		
		// Get Current Owner
		Owner owner = getCurrentOwner();
		
		File f;
		try {
			f = FileRepository.getByPathAndUser(path, owner);
		} catch (EntityNotFoundException e) {
			throw new FileNotFoundException();
		}
		
		return f;
	}
	
	@PUT
	@Path("/{path: [a-zA-Z0-9_/.]+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public File uploadPutFile(
			@PathParam("path") String path,
			InputStream upload,
			@QueryParam("mime-type") String mimetype ) throws IOException {
		
		path = preparePath(path);
		
		// Get Current Owner
		Owner owner = getCurrentOwner();
		
		// Resolve mime-type and filename
		mimetype = mimetype == null || mimetype == "" ? PathHelper.getMimetype(path) : mimetype;
		
		FileService fs = FileServiceFactory.getFileService();
		AppEngineFile file = fs.createNewBlobFile(mimetype);
		
		boolean lock = true;
		FileWriteChannel channel = fs.openWriteChannel(file, lock);
		
		long length = StreamHelper.writeToChannel(upload, channel);
		
		lock = false;
		channel.closeFinally();
		
		// Save Entry to Database
		File f = new File();
		f.setFilename(PathHelper.getFilename(path));
		f.setMimeType(mimetype);
		f.setPath(path);
		f.setStoragePath(file.getFullPath());
		f.setLength(length);
		f.setOwnerId(owner.getId());
		
		Key k = FileRepository.save(f, owner);
		
		// Save Folder Entry if it doesn't exist jet
		if(!FolderRepository.exists(path, owner)) {
			Folder folder = new Folder();
			folder.setName(PathHelper.getFoldername(path));
			folder.setOwnerId(owner.getId());
			folder.setPath(PathHelper.getFolderpath(path));
			
			FolderRepository.save(folder, owner);
		}
		
		return f;
	}
	
	@DELETE
	@Path("/{path: [a-zA-Z0-9_/.]+}")
	public Response deleteFile(@PathParam("path") String path) {
		path = preparePath(path);
		
		// Get Current Owner
		Owner owner = getCurrentOwner();
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Key k = PathHelper.getStorageKey("File", path, owner);
		
		try {
			ds.get(k);
		} catch (EntityNotFoundException e1) {
			throw new FileNotFoundException();
		}
		
		ds.delete(k);
		
		return Response.status(200).build();
	}
	
	private String preparePath(String path) {
		// IMPORTANT!!! leading / before every Path indicates absolute path
		if(!path.startsWith("/")) {
			path = "/" + path;
		}
				
		return path;
	}
}
