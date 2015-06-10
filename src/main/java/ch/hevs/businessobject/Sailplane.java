package ch.hevs.businessobject;

import javax.persistence.Entity;

@Entity
public class Sailplane extends Plane {

	private String registration;

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}
	
	
}
