package ch.hevs.businessobject;

import javax.persistence.Entity;

@Entity
public class Paraglider extends Plane {

	private WingApproval wingApproval;

	public Paraglider() {
		super();
	}

	public Paraglider(WingApproval wingApproval) {
		super();
		this.wingApproval = wingApproval;
		
		this.type = "Paraglider";
	}

	public WingApproval getWingApproval() {
		return wingApproval;
	}

	public void setWingApproval(WingApproval wingApproval) {
		this.wingApproval = wingApproval;
	}
}
