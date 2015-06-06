package ch.hevs.clubservice;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Flight;
import ch.hevs.businessobject.Pilot;
import ch.hevs.businessobject.Plane;
import ch.hevs.businessobject.Site;

@Local
public interface Club {
	
	/**
	 * Get a Pilot by his callsign
	 * 
	 * @param callsign the pilot callsign
	 * @return
	 */
	public Pilot getPilotByCallsign(String callsign);
	
	/**
	 * Get list of all sites arrival and departure
	 * 
	 * @return list of sites
	 */
	public List<Site> getSites();
	
	/**
	 * Get a site by his id
	 * 
	 * @param id Site id
	 * @return
	 */
	public Site getSiteById(long id);
	
	/**
	 * Get a departure site by his name
	 * 
	 * @return
	 */
	public Site getDepartureSiteByName(String name);
	
	/**
	 * Get arrival site by his name
	 * 
	 * @return
	 */
	public Site getArrivalSiteByName(String name);
	
	/**
	 * Get list of all departure sites
	 * 
	 * @return list of departure sites
	 */
	public List<Site> getDepartureSites();
	
	/**
	 * Get list of all arrival sites
	 * 
	 * @return list of arrival sites
	 */
	public List<Site> getArrivalSites();
	
	/**
	 * Get list of all planes
	 * 
	 * @return list of all planes
	 */
	public List<Plane> getPlanes();
	
	/**
	 * Get a plane by his ID
	 * 
	 * @param id
	 * @return
	 */
	public Plane getPlaneById(long id);
	
	/**
	 * Get a list of all flights
	 * 
	 * @return List of flights
	 */
	public List<Flight> getFlights();
	
	/**
	 * Book a new flight
	 * 
	 * @param departure
	 * @param arrival
	 * @param plane
	 * @param pilot
	 * @return
	 * @throws Exception
	 */
	public Flight bookAFlight(Site departure, Site arrival, Plane plane, Pilot pilot, Calendar date) throws Exception;
	
	/**
	 * Get a list of incoming flights
	 * 
	 * @return
	 */
	public List<Flight> getIncomingFlights();
}
