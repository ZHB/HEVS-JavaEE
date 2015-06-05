package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/*
@NamedQueries(value={
   @NamedQuery(
      name = "Pilot.getPilotByLicence",
      query = "SELECT p.* FROM Pilot p WHERE p.licence = :pilotLicence")
})
*/
@Entity
public class Pilot {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String firstname;
	private String lastname;
	private Gender gender;
	private String licence;
	
	@OneToMany(mappedBy="pilot", cascade = CascadeType.ALL)
	private Set<Flight> flights;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Plane> planes;

	public Pilot() {
		super();
	}

	public Pilot(String firstname, String lastname, Gender gender,
			String licence) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.licence = licence;
		
		flights = new HashSet<Flight>();
		planes = new HashSet<Plane>();
	}
	
	
	public void addFlight(Flight f)
	{
		flights.add(f);
		f.setPilot(this);
	}
	
	
}
