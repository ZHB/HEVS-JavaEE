package ch.hevs.businessobject;

import javax.persistence.Entity;

@Entity
public class Hanglider extends Plane {

	private String noseAngle;

	
	public Hanglider() {
		super();
	}
	
	public Hanglider(String noseAngle) {
		super();
		this.noseAngle = noseAngle;
		
		this.type = "Hang Glider";
	}

	
	public String getNoseAngle() {
		return noseAngle;
	}

	public void setNoseAngle(String noseAngle) {
		this.noseAngle = noseAngle;
	}
}
