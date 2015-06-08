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
	 * @param callsign the pilot callsign
	 * @return
	 */
	public Pilot getPilotByCallsign(String callsign);
	
	public List<Pilot> getPilots();
	
	public List<Flight> getFlights();
	
	/**
	 * Get list of all sites arrival and departure
	 * 
	 * @return list of sites
	 */
	public List<Site> getSites();
	
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
	public List<Plane> getAll();
	
	/**
	 * Get a plane by his ID
	 * 
	 * @param id
	 * @return
	 */
	public Plane getById(long id);
	
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
	 * Find a site by his Id
	 * 
	 * @param id
	 * @return
	 */
	 public Site findById(Long id);
	 
	 public Plane findPlaneById(Long id);
	 
	 public Pilot getPilotById(Long id);
}
