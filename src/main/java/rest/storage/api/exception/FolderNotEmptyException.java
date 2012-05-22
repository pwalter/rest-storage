package rest.storage.api.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


public class FolderNotEmptyException extends WebApplicationException {
	public FolderNotEmptyException() {
		super(Response.Status.CONFLICT);
	}
}
