package rest.storage.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;

import rest.storage.api.exception.FileNotFoundException;
import rest.storage.api.helper.PathHelper;
import rest.storage.api.helper.StreamHelper;
import rest.storage.api.model.File;
import rest.storage.api.repository.FileRepository;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileReadChannel;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import com.google.appengine.api.files.LockException;
import com.sun.jersey.core.header.FormDataContentDisposition;



@Path("/file")
public class StorageFile {
	// File download *********************************************************
	@GET
	@Produces({ MediaType.APPLICATION_OCTET_STREAM})
	@Path("/{path: [a-zA-Z0-9_/.]+}")
	public Response downloadFile(@PathParam("path") String path) throws java.io.FileNotFoundException, LockException, IOException {
		// IMPORTANT!!! leading / before every Path indicates absolute path
		path = "/" + path;
		
		File f;
		try {
			f = FileRepository.getByPathAndUser(path, "pwalter");
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
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/list/{path: [a-zA-Z0-9_/]*}")
	public List<File> showFiles(@PathParam("path") String path) {
		// IMPORTANT!!! leading / before every Path indicates absolute path
		path = path != null ? "/" + path : "/";
		
		return FileRepository.getFilesInFolder(path, "pwalter");
		//return ;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/meta/{path: [a-zA-Z0-9_/.]+}")
	public File showFileMetadata(@PathParam("path") String path) {
		// IMPORTANT!!! leading / before every Path indicates absolute path
		path = "/" + path;
		
		File f;
		try {
			f = FileRepository.getByPathAndUser(path, "pwalter");
		} catch (EntityNotFoundException e) {
			throw new FileNotFoundException();
		}
		
		return f;
	}
	
	@PUT
	@Path("/{path: [a-zA-Z0-9_/.]+}")
	public Response uploadPutFile(
			@PathParam("path") String path,
			InputStream upload,
			@QueryParam("mime-type") String mimetype ) throws IOException {
		
		// IMPORTANT!!! leading / before every Path indicates absolute path
		path = "/" + path;
		
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
		f.setOwnerId(4711);
		
		Key k = FileRepository.save(f, "pwalter");
		
		return Response.status(200).build();
	}
	
	@DELETE
	@Path("/{path: [a-zA-Z0-9_/.]+}")
	public Response deleteFile(@PathParam("path") String path) {
		// IMPORTANT!!! leading / before every Path indicates absolute path
		path = "/" + path;
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Key k = PathHelper.getStorageKey("File", path, "pwalter");
		
		try {
			ds.get(k);
		} catch (EntityNotFoundException e1) {
			throw new FileNotFoundException();
		}
		
		ds.delete(k);
		
		return Response.status(200).build();
	}
}
