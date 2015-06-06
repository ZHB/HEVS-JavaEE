package ch.hevs.clubservice;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;

import ch.hevs.businessobject.Flight;
import ch.hevs.businessobject.Pilot;
import ch.hevs.businessobject.Plane;
import ch.hevs.businessobject.Site;
import ch.hevs.businessobject.SiteType;

@Stateless
public class ClubBean implements Club {

	@PersistenceContext(name = "BankPU")
	private EntityManager em;

	@Override
	public Pilot getPilotByCallsign(String callsign) {
		Pilot p = (Pilot) em.createNamedQuery("Pilot.getByCallsign")
				.setParameter("callsign", callsign)
				.getSingleResult();
		
		// lazy initialize flight collection set
		Hibernate.initialize(p.getFlights());
		Hibernate.initialize(p.getPlanes());

		return p;
	}	
	
	@Override
	public List<Site> getSites() {
		return em.createNamedQuery("Site.getAll")
				.getResultList();
	}

	@Override
	public Site getDepartureSiteByName(String name) {
		Site s = (Site) em.createNamedQuery("Site.getByNameAndType")
				.setParameter("siteName", name)
				.setParameter("siteType", SiteType.DEPARTURE)
				.getSingleResult();
		
		return s;
	}

	@Override
	public Site getArrivalSiteByName(String name) {
		Site s = (Site) em.createNamedQuery("Site.getByNameAndType")
				.setParameter("siteName", name)
				.setParameter("siteType", SiteType.ARRIVAL)
				.getSingleResult();
		
		return s;
	}

	
	@Override
	public List<Site> getDepartureSites() {
		return em.createNamedQuery("Site.getAllByType")
				.setParameter("siteType", SiteType.DEPARTURE)
				.getResultList();
	}

	@Override
	public List<Site> getArrivalSites() {
		return em.createNamedQuery("Site.getAllByType").setParameter("siteType", SiteType.ARRIVAL).getResultList();
	}
	
	@Override
	public Site getSiteById(long id) {
		Site s = (Site) em.createNamedQuery("Site.getById")
				.setParameter("siteId", id)
				.getSingleResult();

		return s;
	}

	@Override
	public List<Plane> getPlanes() {
		return em.createQuery("FROM Plane").getResultList();
	}
	
	@Override
	public Plane getPlaneById(long id) {
		
		Plane p = (Plane) em.createNamedQuery("Plane.getById")
				.setParameter("planeId", id)
				.getSingleResult();
		
		// lazy initialize flight collection set
		Hibernate.initialize(p.getFlights());
		

		return p;
	}

	@Override
	public Flight bookAFlight(Site departure, Site arrival, Plane plane, Pilot pilot, Calendar date) throws Exception {
		
		
		
		Flight f = new Flight();
		f.setDepartureDate(new java.sql.Date(date.getTimeInMillis()));
		
		f.defineDeparture(departure);
		f.defineArrival(arrival);
		
		plane.addFlight(f);
		pilot.addFlight(f);
		
		em.persist(f);
		
		return f;
	}
	
	@Override
	public List<Flight> getFlights() {
		return em.createNamedQuery("Flight.getAll")
				.getResultList();
	}

	@Override
	public List<Flight> getIncomingFlights() {
		
		Date date = new Date();
		
		return em.createNamedQuery("Flight.getIncoming")
				.setParameter("minDate", new java.sql.Timestamp(date.getTime()))
				.getResultList();
	}
}
