package ch.hevs.businessobject;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Licence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private Date dateOfIssue;
	private int certificationNumber;
	
	
	public Licence() {
		super();
	}


	public Licence(Date dateOfIssue, int certificationNumber) {
		super();
		this.dateOfIssue = dateOfIssue;
		this.certificationNumber = certificationNumber;
	}
}
