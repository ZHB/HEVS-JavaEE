package ch.hevs.clubservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.hamcrest.core.IsInstanceOf;
import org.hibernate.Hibernate;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Flight;
import ch.hevs.businessobject.Hanglider;
import ch.hevs.businessobject.Paraglider;
import ch.hevs.businessobject.Pilot;
import ch.hevs.businessobject.Plane;
import ch.hevs.businessobject.Site;
import ch.hevs.businessobject.SiteType;
import ch.hevs.businessobject.WingApproval;

@Stateless
public class ClubBean implements Club {

	@PersistenceContext(name = "BankPU")
	private EntityManager em;
	
	@Override
	public List<Flight> getFlights() {
		return em.createQuery("FROM FLIGHT").getResultList();
	}

	@Override
	public List<Site> getSites() {
		return em.createQuery("FROM Site").getResultList();
	}

	@Override
	public Site getDepartureSiteByName(String name) {
		Site s = (Site) em.createNamedQuery("Site.getByNameType")
				.setParameter("siteName", name)
				.setParameter("siteType", SiteType.DEPARTURE)
				.getSingleResult();
		
		return s;
	}

	@Override
	public Site getArrivalSiteByName(String name) {
		Site s = (Site) em.createNamedQuery("Site.getByNameType")
				.setParameter("siteName", name)
				.setParameter("siteType", SiteType.ARRIVAL)
				.getSingleResult();
		
		return s;
	}

	
	@Override
	public List<Site> getDepartureSites() {
		return em.createNamedQuery("Site.getAllByType").setParameter("siteType", SiteType.DEPARTURE).getResultList();
	}

	@Override
	public List<Site> getArrivalSites() {
		//return em.createQuery("SELECT s FROM Site s WHERE type LIKE 1").getResultList();
		return em.createNamedQuery("Site.getAllByType").setParameter("siteType", SiteType.ARRIVAL).getResultList();
	}

	@Override
	public List<Plane> getAll() {
		return em.createQuery("FROM Plane").getResultList();
	}
	
	@Override
	public Plane getById(long id) {
		
		Plane p = (Plane) em.createNamedQuery("Plane.getById")
				.setParameter("planeId", id)
				.getSingleResult();
		
		// lazy initialize flight collection set
		Hibernate.initialize(p.getFlights());
		

		return p;
	}

	@Override
	public Flight bookFlight(Site departure, Site arrival, Plane plane, Pilot pilot, Date date) throws Exception {
		
		Flight f = new Flight();
		f.setDepartureDate(new java.sql.Date(date.getTime()));
		
		f.defineDeparture(departure);
		f.defineArrival(arrival);
		
		plane.addFlight(f);
		pilot.addFlight(f);
		
		em.persist(f);
		
		return f;
	}

	@Override
	public List<Flight> getIncomingFlights() {
		
		Date date = new Date();
		
		return em.createNamedQuery("Flight.getIncoming")
				.setParameter("minDate", new java.sql.Timestamp(date.getTime()))
				.getResultList();
	}


	
	

	

	
	
	
}
