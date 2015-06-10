package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import ch.hevs.businessobject.Flight;
import ch.hevs.businessobject.Pilot;
import ch.hevs.businessobject.Plane;
import ch.hevs.businessobject.Site;
import ch.hevs.clubservice.Club;

/**
 * TransferBean.java
 * 
 */
@ManagedBean(name="bookingBean")
@RequestScoped
public class BookingBean
{

	
	private List<Flight> incomingFlights;
	
	private Plane plane;
	private Site departureSite;
	private Site arrivalSite;
	private List<Plane> planes;
	private List<Site> departureSites;
	private List<Site> arrivalSites;
	
	/**
	 * The flight departure date
	 */
	private Date departureDate;
	
	/**
	 * The flight departure time
	 */
	private Date departureTime;
	
	private String pilotCallsign;
	
	@EJB(name = "ClubBean") 
	private Club club;
	
	@PostConstruct
    public void initialize() {
 		
		// create lists for dropdown menu
		departureSites = club.getDepartureSites();
		arrivalSites = club.getArrivalSites();
		planes = club.getPlanes();

		
		// get incoming flights
		incomingFlights = new ArrayList<Flight>();
		incomingFlights = club.getIncomingFlights();
    }
	
	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public Site getDepartureSite() {
		return departureSite;
	}

	public void setDepartureSite(Site departureSite) {		
		this.departureSite = departureSite;
	}

	public Site getArrivalSite() {
		return arrivalSite;
	}

	public void setArrivalSite(Site arrivalSite) {
		this.arrivalSite = arrivalSite;
	}

	public List<Site> getDepartureSites() {
		return departureSites;
	}

	public void setDepartureSites(List<Site> departureSites) {
		this.departureSites = departureSites;
	}

	public List<Site> getArrivalSites() {
		return arrivalSites;
	}

	public void setArrivalSites(List<Site> arrivalSites) {
		this.arrivalSites = arrivalSites;
	}

	public List<Plane> getPlanes() {
		return planes;
	}
	
	public void setPlanes(List<Plane> planes) {
		this.planes = planes;
	}

	public String getPilotCallsign() {
		return pilotCallsign;
	}

	public void setPilotCallsign(String pilotCallsign) {
		this.pilotCallsign = pilotCallsign;
	}
	
	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * Perform a new flight booking
	 * 
	 * @return
	 */
	public String performBooking() {
    	try {
			if(departureSite == null || arrivalSite == null) {
				FacesMessage message = new FacesMessage( "Please, select a departure and arrival site");
		        FacesContext.getCurrentInstance().addMessage( null, message );
			} else {
				// get the pilot by his callsign
				Pilot pilot = club.getPilotByCallsign(pilotCallsign);
				if(pilot == null) {
					FacesMessage message = new FacesMessage( "No pilot found with " + pilotCallsign + " callsign");
			        FacesContext.getCurrentInstance().addMessage( null, message );
				}
				
				// converte Java.Date to Java.Calendar
				Calendar calDepartureTime = Calendar.getInstance();
				calDepartureTime.setTime(departureTime);
				
				Calendar calDepartureDate = Calendar.getInstance();
				calDepartureDate.setTime(departureDate);
								
				// set the departure date/time from the submitted departure date and time
				Calendar calDepartureDateTime = Calendar.getInstance();
				calDepartureDateTime.set(
						calDepartureDate.get(Calendar.YEAR), 
						calDepartureDate.get(Calendar.MONTH), 
						calDepartureDate.get(Calendar.DATE), 
						calDepartureTime.get(Calendar.HOUR_OF_DAY), 
						calDepartureTime.get(Calendar.MINUTE), 
						calDepartureTime.get(Calendar.SECOND)
						);
				
				// Save the new flight and redirect to new page (see faces-config.xml)
				if(club.bookFlight(departureSite, arrivalSite, plane, pilot, calDepartureDateTime) != null) {
					return "bookingSuccess";
				} 
			}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    	return "bookingError";
	}

	public List<Flight> getIncomingFlights() {
		return incomingFlights;
	}

	public void setIncomingFlights(List<Flight> incomingFlights) {
		this.incomingFlights = incomingFlights;
	}
}