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
	
	/**
	 * Represent the license date of issue. That's the date 
	 * that indicate the license is invalid
	 */
	@NotNull(message = "Please, enter the license date of issue")
	private Date dateOfIssue;
	
	/**
	 * Indicates the pilot certification number.
	 * The number must be six digits length
	 */
	@NotNull(message = "Please, enter the license certification number")
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