package ch.hevs.businessobject;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(
			name="Flight.getAll", 
			query="SELECT f FROM Flight f"
	),
	@NamedQuery(
		name="Flight.getIncoming", 
		query="SELECT f FROM Flight f WHERE f.departureDate >= :minDate ORDER BY f.departureDate ASC"
	)
})
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	/**
	 * Indicates the flight estimated departure date/time
	 */
	@Column(nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date departureDate;
	
	/**
	 * It's the pilot who is doing the flight
	 */
	@ManyToOne
	private Pilot pilot;
	
	/**
	 * It's the plane who is used to flight with
	 */
	@ManyToOne
	private Plane plane;
	
	/**
	 * Set a departure site for the flight
	 */
	@OneToOne
	@JoinColumn(name="fk_site_departure", nullable=false)
	private Site departure;
	
	
	/**
	 * Set an arrival site for the flight
	 */
	@OneToOne
	@JoinColumn(name="fk_site_arrival", nullable=false)
	private Site arrival;


	public Flight() {
		super();
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	
	public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	/**
	 * Define a new departure Site for this flight
	 * 
	 * @param d
	 */
	public void defineDeparture(Site d) {
		departure = d;
	}
	
	/**
	 * Define a new arrival Site for this flight
	 * 
	 * @param a
	 */
	public void defineArrival(Site a) {
		arrival = a;
	}

	public Site getDeparture() {
		return departure;
	}

	public void setDeparture(Site departure) {
		this.departure = departure;
	}

	public Site getArrival() {
		return arrival;
	}

	public void setArrival(Site arrival) {
		this.arrival = arrival;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
