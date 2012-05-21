package rest.storage.api.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


public class FileNotFoundException extends WebApplicationException {
	public FileNotFoundException() {
		super(Response.Status.NOT_FOUND);
	}
}
