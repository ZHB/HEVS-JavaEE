package ch.hevs.businessobject;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
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
		name="Flight.getIncoming", 
		query="SELECT f FROM Flight f WHERE f.departureDate > :minDate ORDER BY f.departureDate ASC"
	)
})
public class Flight {

	//SELECT c.name, p.name FROM Country c JOIN c.capital p 
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date departureDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Pilot pilot;
	
	@ManyToOne
	private Plane plane;
	
	
	/**
	 * OneToOne relation
	 * Site departure is mandatory
	 */
	@OneToOne
	@JoinColumn(name="fk_site_departure", nullable=false)
	private Site departure;
	
	
	/**
	 * OneToOne relation
	 * Client address is obligatory
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
}
