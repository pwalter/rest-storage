package de.pascalwalter.storage.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;

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

import de.pascalwalter.storage.api.exception.FileNotFoundException;


@Path("/file")
public class StorageFile {
	@Context
    private UriInfo context;
	
	// File download *********************************************************
	/*@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{path: [a-zA-Z0-9_/.]+}")
	public String downloadFileAsPlain(@PathParam("path") String path) {
		return "This would return the file: " + path;
	}
	*/
	@GET
	@Produces({ MediaType.APPLICATION_OCTET_STREAM})
	@Path("/{path: [a-zA-Z0-9_/.]+}")
	public Response downloadFile(@PathParam("path") String path) throws java.io.FileNotFoundException, LockException, IOException {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		//BlobstoreService bs = BlobstoreServiceFactory.getBlobstoreService();
		
		Query q = new Query("File");
		q.addFilter("path", Query.FilterOperator.EQUAL, path);
		
		PreparedQuery pq = ds.prepare(q);
		Entity e = pq.asSingleEntity();
		
		if(e == null) {
			throw new FileNotFoundException();
		}
		
		FileService fs = FileServiceFactory.getFileService();
		AppEngineFile file = new AppEngineFile((String) e.getProperty("storage-path"));
		FileReadChannel channel = fs.openReadChannel(file, false);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] barray = new byte[1024];
		ByteBuffer bb = ByteBuffer.wrap(barray);
		int nRead;
		while ((nRead=channel.read(bb)) != -1) {
			for (int i=0; i < nRead; i++) {
				out.write(barray[i]);
			}
			bb.clear();
		}
		
		byte[] filebytes = out.toByteArray();
		return Response.ok(filebytes).header("content-disposition","attachment; filename = test.txt").build();
		
		/*
		
		BlobKey key = new BlobKey((String) e.getProperty("storage-path"));
		
		
		StreamingOutput stream = new StreamingOutput() {
	        public void write(OutputStream output) throws IOException, WebApplicationException {
	            try {
	                wb.write(output);
	            } catch (Exception e) {
	                throw new WebApplicationException(e);
	            }
	        }
	    };*/
		
		//return new GenericEntity<String>("This would return the file from the API: " + e.getProperty("path") + "<br />Real Path: " + e.getProperty("storage-path")) {};
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/{path: [a-zA-Z0-9_/]+}")
	public GenericEntity<String> showFiles(@PathParam("path") String path) {
		return new GenericEntity<String>("This would show a list of files in the folder: " + path) {};
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/meta/{path: [a-zA-Z0-9_/.]+}")
	public GenericEntity<String> showFileMetadata(@PathParam("path") String path) {
		return new GenericEntity<String>("This would show some file metadata for the file: " + path) {};
	}
	
	@PUT
	@Path("/{path: [a-zA-Z0-9_/.]+}")
	public String uploadPutFile(
			@PathParam("path") String path,
			InputStream upload,
			@QueryParam("mime-type") String mimetype ) throws IOException {
		
		
		FileService fs = FileServiceFactory.getFileService();
		AppEngineFile file = fs.createNewBlobFile("text/plain");
		
		boolean lock = true;
		FileWriteChannel channel = fs.openWriteChannel(file, lock);
		
		
		int read = 0;
		byte[] bytes = new byte[1024];
	 
		while ((read = upload.read(bytes)) != -1) {
			//out.write(bytes, 0, read);
			channel.write(ByteBuffer.wrap(bytes));
		}
		
		lock = false;
		channel.closeFinally();
		
		// Store to Database
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Entity entity = new Entity("File");
		entity.setProperty("path", path);
		entity.setProperty("mime-type", mimetype);
		entity.setProperty("storage-path", file.getFullPath());
		
		Key k = ds.put(entity);
		
		return "This would upload the file using PUT: " + KeyFactory.keyToString(k);
	}
	
	@DELETE
	@Path("/{path: [a-zA-Z0-9_/.]+}")
	public String deleteFile(@PathParam("path") String path) {
		// http://stackoverflow.com/questions/3496209/input-and-output-binary-streams-using-jersey
		return "This would delete the file: " + path;
	}
}
