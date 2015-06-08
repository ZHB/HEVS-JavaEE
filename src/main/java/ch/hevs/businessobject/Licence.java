package ch.hevs.businessobject;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Licence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotNull(message = "Please, enter the licence date of issue")
	private Date dateOfIssue;
	
	@NotNull(message = "Please, enter the licence certification number")
	private int certificationNumber;
	
	
	public Licence() {
		super();
	}


	public Licence(Date dateOfIssue, int certificationNumber) {
		super();
		this.dateOfIssue = dateOfIssue;
		this.certificationNumber = certificationNumber;
	}


	public Date getDateOfIssue() {
		return dateOfIssue;
	}


	public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}


	public int getCertificationNumber() {		
		return certificationNumber;
	}


	public void setCertificationNumber(int certificationNumber) {
		this.certificationNumber = certificationNumber;
	}
}