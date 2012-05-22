package rest.storage.api.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


public class OwnerNotFoundException extends WebApplicationException {
	public OwnerNotFoundException() {
		super(Response.Status.NOT_ACCEPTABLE);
	}
}
