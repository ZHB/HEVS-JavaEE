package ch.hevs.clubservice;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Flight;
import ch.hevs.businessobject.Licence;
import ch.hevs.businessobject.Pilot;
import ch.hevs.businessobject.Plane;
import ch.hevs.businessobject.Site;

@Local
public interface Club {
	
	/**
	 * Get a Pilot by his callsign
	 * 
	 * @param callsign	the pilot callsign
	 * @return the pilot
	 */
	public Pilot getPilotByCallsign(String callsign);
	
	/**
	 * Get all pilots list
	 * 
	 * @return	a <code>List</code> of planes
	 */
	public List<Pilot> getPilots();
	
	/**
	 * Get all flights list
	 * 
	 * @return a <code>List</code> of flights
	 */
	public List<Flight> getFlights();
	
	/**
	 * Get list of all sites arrival and departure
	 * 
	 * @return <code>List</code> of sites
	 */
	public List<Site> getSites();
	
	/**
	 * Get list of all departure sites
	 * 
	 * @return <code>List</code> of departure sites
	 */
	public List<Site> getDepartureSites();
	
	/**
	 * Get list of all arrival sites
	 * 
	 * @return <code>List</code> of arrival sites
	 */
	public List<Site> getArrivalSites();
	
	/**
	 * Get list of all planes
	 * 
	 * @return list of all planes
	 */
	public List<Plane> getPlanes();
	
	
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
	public Flight bookFlight(Site departure, Site arrival, Plane plane, Pilot pilot, Calendar date) throws Exception;
	
	
	/**
	 * Get a list of incoming flights
	 * 
	 * @return
	 */
	public List<Flight> getIncomingFlights();
	
	/**
	 * Add a new pilot with his licence
	 * 
	 * @param pilot
	 * @param licence
	 * @return
	 */
	public Pilot addOrUpdatePilot(Pilot pilot, Licence licence);
	
	/**
	 * Remove a pilot
	 * 
	 * @param pilot
	 */
	public void removePilot(Long id);
	
	
	/**
	 * Find a site by his Id
	 * 
	 * @param id	the site id
	 * @return Site the founded <code>Site</code>
	 */
	 public Site getSiteById(Long id);
	 
	 /**
	  * Find a plane by his id
	  * 
	  * @param id
	  * @return	Plane the founded <code>Plane</code>
	  */
	 public Plane getPlaneById(Long id);
	 
	 /**
	  * Find a pilot by his id
	  * 
	  * @param id	The pilot id
	  * @return Pilot	the founded <code>Pilot</code>
	  */
	 public Pilot getPilotById(Long id);
}
