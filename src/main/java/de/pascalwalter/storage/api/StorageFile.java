package de.pascalwalter.storage.api;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;


@Path("/files")
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
		@Produces({ MediaType.APPLICATION_JSON})
		@Path("/{path: [a-zA-Z0-9_/.]+}")
		public GenericEntity<String> downloadFile(@PathParam("path") String path) {
			return new GenericEntity<String>("This would return the file: " + path) {};
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
		
		@POST
		@Path("/{path: [a-zA-Z0-9_/.]+}")
		public String uploadPostFile(@PathParam("path") String path) {
			// http://stackoverflow.com/questions/3496209/input-and-output-binary-streams-using-jersey
			return "This would upload the file using POST: " + path;
		}
		
		@PUT
		@Path("/{path: [a-zA-Z0-9_/.]+}")
		public String uploadPutFile(@PathParam("path") String path) {
			// http://stackoverflow.com/questions/3496209/input-and-output-binary-streams-using-jersey
			return "This would upload the file using PUT: " + path;
		}
		
		@DELETE
		@Path("/{path: [a-zA-Z0-9_/.]+}")
		public String deleteFile(@PathParam("path") String path) {
			// http://stackoverflow.com/questions/3496209/input-and-output-binary-streams-using-jersey
			return "This would delete the file: " + path;
		}
}
