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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NamedQueries({
	@NamedQuery(
			name="Pilot.getByCallsign", 
			query="SELECT p FROM Pilot p WHERE p.callsign = :callsign"
	),
	@NamedQuery(
			name="Pilot.getAll", 
			query="SELECT p FROM Pilot p"
	),
	@NamedQuery(
			name="Pilot.getOwnersPlanes", 
			query="SELECT p FROM Plane p"
	)
})
@Entity
public class Pilot {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	/**
	 * The pilot callsign
	 */
	@NotNull(message = "Please, enter a pilot callsign")
	@Pattern( regexp = "[A-Z]{3}", message = "Please, enter a three letter pilot callsign (UPPERCASE)" )
	private String callsign;
	
	/**
	 * The pilot firstname
	 */
	@NotNull(message = "Please, enter the pilot firstname")
	private String firstname;
	
	/**
	 * The pilot lastname
	 */
	@NotNull(message = "Please, enter the pilot lastname")
	private String lastname;
	
	/**
	 * The pilot gender
	 */
	@NotNull(message = "Please, enter the pilot gender")
	private Gender gender;
	
	/**
	 * Flights operated by the pilot
	 */
	@OneToMany(mappedBy="pilot", cascade = CascadeType.REMOVE)
	private Set<Flight> flights;
	
	/**
	 * Aircraft belonging to the pilot
	 */
	@ManyToMany(mappedBy="pilots", cascade = CascadeType.REMOVE)
	private Set<Plane> planes;

	/**
	 * The pilot licence
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="fk_licence", nullable=false)
	@NotNull(message = "Please, enter the pilot licence number")
	private Licence licence;
	
	public Pilot() {
		super();
	}

	/**
	 * Construct a new pilot object
	 * 
	 * @param firstname	the pilot name
	 * @param lastname	the pilot firstname
	 * @param gender	the pilot <code>Gender</code>
	 * @param licence	the pilot <code>Licence</code>
	 */
	public Pilot(String firstname, String lastname, Gender gender,
			String licence) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		
		flights = new HashSet<Flight>();
		planes = new HashSet<Plane>();
	}
	
	/**
	 * Adds a flight as belonging to the pilot.
	 * 
	 * @param flight	the <code>Flight</code> that will be added to the pilot
	 */
	public void addFlight(Flight flight)
	{
		flights.add(flight);
		flight.setPilot(this);
	}
	
	/**
	 * Add a license to the pilot
	 * 
	 * @param licence	the <code>License</code> to add
	 */
	public void addLicence(Licence licence) {
		this.licence = licence;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCallsign() {
		return callsign;
	}

	/**
	 * Callsign is an unique three uppercase letters that identifies a pilot. 
	 * It is used to identifiy pilot conversation on radio call
	 * 
	 * @param callsign must be three uppercase letters (e.g. HJT)
	 */
	public void setCallsign(String callsign) {
		this.callsign = callsign;
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

	public Licence getLicence() {
		return licence;
	}

	public void setLicence(Licence licence) {
		this.licence = licence;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
