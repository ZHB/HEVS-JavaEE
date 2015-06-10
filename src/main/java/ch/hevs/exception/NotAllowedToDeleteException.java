package ch.hevs.exception;

import javax.ejb.EJBException;

public class NotAllowedToDeleteException extends EJBException {
	public NotAllowedToDeleteException() {
		super();
	}

	public NotAllowedToDeleteException(String arg0) {
		super(arg0);
	}
}
