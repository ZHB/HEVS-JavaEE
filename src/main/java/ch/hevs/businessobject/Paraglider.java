package ch.hevs.businessobject;

import javax.persistence.Entity;

@Entity
public class Paraglider extends Plane {

	/**
	 * It's the category of paraglider wing. There is four categories from A to D
	 * and two extra categories (acrobatics and competition) of wings. The easiest 
	 * category is A
	 */
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
