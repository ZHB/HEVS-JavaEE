package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
@NamedQueries({
	@NamedQuery(
			name="Pilot.getByCallsign", 
			query="SELECT p FROM Pilot p WHERE p.callsign = :callsign"
	)
})
public class Pilot {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String callsign;
	private String firstname;
	private String lastname;
	private Gender gender;
	
	@OneToMany(mappedBy="pilot")
	private Set<Flight> flights;
	
	@ManyToMany
	private Set<Plane> planes;
	
	/**
	 * OneToOne relation
	 * Site departure is mandatory
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="fk_licence", nullable=false)
	private Licence licence;

	public Pilot() {
		super();
	}

	public Pilot(String firstname, String lastname, Gender gender) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		
		flights = new HashSet<Flight>();
		planes = new HashSet<Plane>();
	}
	
	public void addFlight(Flight f)
	{
		flights.add(f);
		f.setPilot(this);
	}
	
	/**
	 * Add a licence to the pilot
	 * 
	 * @param l
	 */
	public void addLicence(Licence l) {
		licence = l;
	}

	public Set<Flight> getFlights() {
		return flights;
	}

	public void setFlights(Set<Flight> flights) {
		this.flights = flights;
	}

	public Set<Plane> getPlanes() {
		return planes;
	}

	public void setPlanes(Set<Plane> planes) {
		this.planes = planes;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
	
	
}
