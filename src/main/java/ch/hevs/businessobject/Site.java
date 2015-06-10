package ch.hevs.businessobject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
	@NamedQuery(
			name="Site.getAll", 
			query="SELECT s FROM Site s ORDER BY s.type, s.name"
	),
	@NamedQuery(
		name="Site.getAllByType", 
		query="SELECT s FROM Site s WHERE s.type LIKE :siteType"
	)
})
public class Site {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotNull(message = "Please, enter a site name")
	private String name;
	
	@NotNull(message = "Please, enter a site latitude")
	private double latitude;
	
	@NotNull(message = "Please, enter a site longitude")
	private double longitude;
	
	@NotNull(message = "Please, enter a site type")
	private SiteType type;
	
	public Site() {
		super();
	}

	public Site(String name, double latitude, double longitude, SiteType type) {
		super();
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public SiteType getType() {
		return type;
	}

	public void setType(SiteType type) {
		this.type = type;
	}
	
	@Override
	public boolean equals(Object object) {
        return (object instanceof Site) && (id != null) 
             ? id.equals(((Site) object).id) 
             : (object == this);
    }
	
}
