package ch.hevs.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIParameter;

import ch.hevs.businessobject.Gender;
import ch.hevs.businessobject.Licence;
import ch.hevs.businessobject.Pilot;
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
	
	private Pilot pilot;
	private Licence licence;
	private Site site;

	@PostConstruct
    public void initialize() {
		pilots = club.getPilots();
		sites = club.getSites();
		
		pilot = new Pilot();
		licence = new Licence();
		site = new Site();
    }
	
	public String loadPilot() {
		if(pilotId == null) {
			return null;
		}
		pilot = club.getPilotById(pilotId);
		licence = pilot.getLicence();
	    return null;
	}
	
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
	 * @return
	 */
	public Gender[] getGenders() {
		return Gender.values();
	}

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

	/**
	 * Add a pilot to DB
	 * @return
	 */
	public String addOrUpdatePilot() {
		club.addOrUpdatePilot(pilot, licence);
		
		return "adminForm";
	}
	
	public String removePilot(Pilot pilot) {	
		club.removePilot(pilot.getId());
		return "adminForm";
	}
	
	public String addOrUpdateSite() {
		club.addOrUpdateSite(site);
		
		return "adminForm";
	}
	
	public String removeSite(Site site) {	
		club.removeSite(site.getId());
		return "adminForm";
	}
}
