package rest.storage.api.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


public class FolderNotFoundException extends WebApplicationException {
	public FolderNotFoundException() {
		super(Response.Status.NOT_FOUND);
	}
}
