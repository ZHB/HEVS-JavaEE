package ch.hevs.businessobject;

import java.util.Date;

import javax.enterprise.inject.Default;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.DefaultValue;

@Entity
public class Licence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotNull(message = "Please, enter the license date of issue")
	private Date dateOfIssue;
	
	@NotNull(message = "Please, enter the license certification number")
	@Pattern( regexp = "[0-9]{6}", message = "Please, enter a six numbers pilot license" )
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