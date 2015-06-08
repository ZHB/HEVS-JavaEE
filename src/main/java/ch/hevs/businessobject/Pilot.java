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
import javax.validation.constraints.Size;

@NamedQueries({
	@NamedQuery(
			name="Pilot.getByCallsign", 
			query="SELECT p FROM Pilot p WHERE p.callsign = :callsign"
	)
})
@Entity
public class Pilot {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotNull(message = "Please, enter a pilot callsign")
	@Pattern( regexp = "[A-Z]{3}", message = "Please, enter a three letter pilot callsign (UPPERCASE)" )
	@Size(min=3, max=3)
	private String callsign;
	
	@NotNull(message = "Please, enter the pilot firstname")
	private String firstname;
	
	@NotNull(message = "Please, enter the pilot lastname")
	private String lastname;
	
	@NotNull(message = "Please, enter the pilot gender")
	private Gender gender;
	
	@OneToMany(mappedBy="pilot", cascade = CascadeType.REMOVE)
	private Set<Flight> flights;
	
	@ManyToMany(cascade = CascadeType.REMOVE)
	private Set<Plane> planes;

	/**
	 * OneToOne relation
	 * Site departure is mandatory
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="fk_licence", nullable=false)
	@NotNull(message = "Please, enter the pilot licence number")
	private Licence licence;
	
	public Pilot() {
		super();
	}

	public Pilot(String firstname, String lastname, Gender gender,
			String licence) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		
		flights = new HashSet<Flight>();
		planes = new HashSet<Plane>();
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

	public void setCallsign(String callsign) {
		this.callsign = callsign;
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
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@ " + gender);
		this.gender = gender;
	}
	
	
	
	
}
