package ch.hevs.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import ch.hevs.businessobject.Gender;
import ch.hevs.businessobject.Licence;
import ch.hevs.businessobject.Pilot;
import ch.hevs.businessobject.Plane;
import ch.hevs.businessobject.Site;
import ch.hevs.businessobject.SiteType;
import ch.hevs.clubservice.Club;


@ManagedBean(name="manageBean")
@RequestScoped
public class ManageBean {
	
	@EJB(name = "ManageBean") 
	private Club club;
	
	private Long pilotId;
	private Long siteId;
	
	private List<Pilot> pilots;
	private List<Site> sites;
	private List<Plane> planes;
	
	private Pilot pilot;
	private Licence licence;
	private Site site;

	/**
	 * This method is called after the class instantiation
	 * and after the constructor call to be sure that the bean was
	 * fully initialized before doing calls
	 */
	@PostConstruct
    public void initialize() {
		pilots = club.getPilots();
		sites = club.getSites();
		setPlanes(club.getPlanes());
		
		pilot = new Pilot();
		licence = new Licence();
		site = new Site();
    }

	/**
	 * This method is used to load a pilot
	 * 
	 * @return
	 */
	public String loadPilot() {
		if(pilotId == null) {
			return null;
		}
		pilot = club.getPilotById(pilotId);
		licence = pilot.getLicence();
	    return null;
	}
	
	/**
	 * This method is used to load a Site
	 * 
	 * @return
	 */
	public String loadSite() {
		if(siteId == null) {
			return null;
		}
		
		site = club.getSiteById(siteId);
	    return null;
	}
	
	/**
	 * Retrieve a list of Genders
	 * 
	 * @return	an array of Genders
	 */
	public Gender[] getGenders() {
		return Gender.values();
	}

	/**
	 * Retrieve a list of SiteType
	 * 
	 * @return	an array of SiteType
	 */
	public SiteType[] getSiteTypes() {
		return SiteType.values();
	}
	
	public Long getPilotId() {
		return pilotId;
	}

	public void setPilotId(Long pilotId) {
		this.pilotId = pilotId;
	}
	
	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}
	
	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Licence getLicence() {
		return licence;
	}

	public void setLicence(Licence licence) {
		this.licence = licence;
	}

	public List<Pilot> getPilots() {
		return pilots;
	}

	public void setPilots(List<Pilot> pilots) {
		this.pilots = pilots;
	}
	
	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}
	
	public List<Plane> getPlanes() {
		return planes;
	}

	public void setPlanes(List<Plane> planes) {
		this.planes = planes;
	}

	/**
	 * Add or update a <code>Pilot</code> in the database. 
	 * Add operation : you must give a <code>Pilot</code> without his id set
	 * 			so, entityManager will add it to the DB
	 * Update operation : you must giv a <code>Pilot</code> with his id set
	 * 			so entityManager will know wich entity to update
	 * 
	 * @return	The page to display
	 */
	public String addOrUpdatePilot() {
		club.addOrUpdatePilot(pilot, licence);
		
		return "adminForm";
	}
	
	/**
	 * Remove a pilot from the database. You have to pass the pilot object and
	 * be careful that the pilot object has his id set otherwise entityManage won't 
	 * find it
	 * 
	 * @param pilot	the <code>Pilot</code> object to delete
	 * @return The page to display
	 */
	public String removePilot(Pilot pilot) {	
		try {
			club.removePilot(pilot.getId());
		} catch (EJBException e){
			FacesMessage message = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage( null, message );
		} 
		
		return "adminForm";
	}
	
	/**
	 * Add or update a <code>Site</code> in the database. 
	 * Add operation : you must give a <code>Site</code> without his id set
	 * 			so, entityManager will add it to the DB
	 * Update operation : you must giv a <code>Site</code> with his id set
	 * 			so entityManager will know wich entity to update
	 * 
	 * @return	The page to display
	 */
	public String addOrUpdateSite() {
		club.addOrUpdateSite(site);
		
		return "adminForm";
	}
	
	/**
	 * Remove a site from the database. You have to pass the site object and
	 * be careful that the site object has his id set otherwise entityManage won't 
	 * find it
	 * 
	 * @param site	the <code>Site</code> object to delete
	 * @return The page to display
	 */
	 public String removeSite(Site site) {	
		 try {
			 club.removeSite(site.getId());
	     } catch (EJBException e){
	    	  FacesMessage message = new FacesMessage(e.getMessage());
		      FacesContext.getCurrentInstance().addMessage( null, message );
		 } 
		 
		 return "adminForm";
	 }
}
