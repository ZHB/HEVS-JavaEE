package ch.hevs.clubservice;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;

import ch.hevs.businessobject.Flight;
import ch.hevs.businessobject.Licence;
import ch.hevs.businessobject.Pilot;
import ch.hevs.businessobject.Plane;
import ch.hevs.businessobject.Site;
import ch.hevs.businessobject.SiteType;
import ch.hevs.exception.NotAllowedToDeleteException;

@Stateless
public class ClubBean implements Club {

	@PersistenceContext(name = "BankPU")
	private EntityManager em;
	
	@Resource
	private SessionContext ctx;
	
	@Override
	public Pilot getPilotByCallsign(String callsign) {
		try{
			Pilot p = (Pilot) em.createNamedQuery("Pilot.getByCallsign")
					.setParameter("callsign", callsign)
					.getSingleResult();
			
			// lazy initialize flight collection set
			Hibernate.initialize(p.getFlights());
			Hibernate.initialize(p.getPlanes());

			return p;
	    } catch(NoResultException e) {
	        return null;
	    }
	}	
	
	@Override
	public List<Flight> getFlights() {
		return em.createNamedQuery("Flight.getAll").getResultList();
	}

	@Override
	public List<Site> getSites() {
		return em.createNamedQuery("Site.getAll").getResultList();
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
	public List<Plane> getPlanes() {
		return em.createNamedQuery("Plane.getAll").getResultList();
	}

	@Override
	public Flight bookFlight(Site departure, Site arrival, Plane plane, Pilot pilot, Calendar date) throws Exception {
		
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
	public List<Flight> getIncomingFlights() {
		
		Date date = new Date();
		
		Calendar calDepartureDate = Calendar.getInstance();
		calDepartureDate.set(
				calDepartureDate.get(Calendar.YEAR), 
				calDepartureDate.get(Calendar.MONTH), 
				calDepartureDate.get(Calendar.DATE),
				0,
				0);
		
		
		return em.createNamedQuery("Flight.getIncoming")
				.setParameter("minDate", new java.sql.Timestamp(calDepartureDate.getTimeInMillis()))
				.getResultList();
	}
	
	@Override
	public Pilot addOrUpdatePilot(Pilot pilot, Licence licence) {

		// add the pilote licence
		pilot.addLicence(licence);

		em.merge(pilot);		
		em.flush();
		
		return pilot;
	}

	@Override
	public Site getSiteById(Long id) {
		
		Site s = em.find(Site.class, id);
		
		return s;
	}

	@Override
	public Plane getPlaneById(Long id) {
		Plane p = em.find(Plane.class, id);
		
		// lazy initialize flight collection set
		Hibernate.initialize(p.getFlights());
		
		return p;
	}

	@Override
	public List<Pilot> getPilots() {
		return em.createNamedQuery("Pilot.getAll").getResultList();
	}

	@Override
	public Pilot getPilotById(Long id) {
		return em.find(Pilot.class, id);
	}

	@Override
	public boolean removePilot(Long id) {
		if (!ctx.isCallerInRole("administrator")) {    
        	throw (EJBException) new NotAllowedToDeleteException("You are not allowed to delete pilots !");
        } 
		
		Pilot p = em.find(Pilot.class, id);
	    em.remove(p);
		em.flush();
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("adminForm.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public boolean removeSite(Long id) {
        if (!ctx.isCallerInRole("administrator")) {    
        	throw (EJBException) new NotAllowedToDeleteException("You are not allowed to delete sites !");
        } 
        
		Site s = em.find(Site.class, id);
	    em.remove(s);
		em.flush();
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("adminForm.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public Site addOrUpdateSite(Site site) {
		em.merge(site);		
		em.flush();
		
		return site;
	}	
}
