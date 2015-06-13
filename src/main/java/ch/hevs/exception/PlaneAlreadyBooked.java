package ch.hevs.exception;

import javax.ejb.EJBException;

public class PlaneAlreadyBooked extends EJBException {
	public PlaneAlreadyBooked() {
		super();
	}

	public PlaneAlreadyBooked(String arg0) {
		super(arg0);
	}
}