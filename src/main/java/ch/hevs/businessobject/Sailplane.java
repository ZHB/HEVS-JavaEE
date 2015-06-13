package ch.hevs.businessobject;

import javax.persistence.Entity;

@Entity
public class Sailplane extends Plane {

	/**
	 * The registration number of the sail plane.
	 * In Switzerland, this identification number starts with
	 * HB-. For example HB-HTZ
	 */
	private String registration;

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}
}
