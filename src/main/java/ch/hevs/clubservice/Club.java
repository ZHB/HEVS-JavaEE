package ch.hevs.clubservice;

import java.util.Calendar;
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
	 * Get all list of all pilots
	 * 
	 * @return	a <code>List</code> of planes
	 */
	public List<Pilot> getPilots();
	
	/**
	 * Get a list of flights
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
	 * @param departure	the departure site
	 * @param arrival	the arrival site
	 * @param plane		the plane used for this flight
	 * @param pilot		the pilot who is doing this flight
	 * @return	flight 	the booked flight
	 * @throws Exception
	 */
	public Flight bookFlight(Site departure, Site arrival, Plane plane, Pilot pilot, Calendar date);
	
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
	 * Add a new site in the database or update an
	 * existing one if the given site id correspond to 
	 * an existing one in the db
	 * 
	 * @param site	the site to update or add
	 * @return	site the updated site
	 */
	public Site addOrUpdateSite(Site site);
	
	/**
	 * Remove a pilot by his given id
	 * 
	 * @param pilot
	 */
	public boolean removePilot(Long id);
	
	/**
	 * Remove a pilot by his given id
	 * 
	 * @param id
	 */
	public boolean removeSite(Long id);
	
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
