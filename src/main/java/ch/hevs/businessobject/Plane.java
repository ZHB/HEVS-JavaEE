package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(
			name="Plane.getAll", 
			query="SELECT p FROM Plane p"
	)
})
public class Plane {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	/**
	 * The plane type human readable
	 */
	@NotNull(message = "Please, enter a plane type")
	protected String type;
	
	/**
	 * The plane model
	 */
	@NotNull(message = "Please, enter a plane model")
	private String model;
	
	/**
	 * The plane price
	 */
	@NotNull(message = "Please, enter a plane price")
	private double price;
	
	/**
	 * The flights that did the plane
	 */
	@OneToMany(mappedBy="plane", cascade = CascadeType.ALL)
	protected Set<Flight> flights;
	
	/**
	 * The plane owner
	 */
	@ManyToMany
	private Set<Pilot> pilots;
	
	public Plane() {
		super();
		
		flights = new HashSet<Flight>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Set<Flight> getFlights() {
		return flights;
	}

	public void setFlights(Set<Flight> flights) {
		this.flights = flights;
	}

	public void addFlight(Flight f) {
		flights.add(f);		
		f.setPlane(this);
	}	
	
	/**
	 * This equal method is used by the Plane converter to find the 
	 * correct Plane entity by a given id
	 */
	@Override
	public boolean equals(Object object) {
        return (object instanceof Plane) && (id != null) 
             ? id.equals(((Plane) object).id) 
             : (object == this);
    }
}
