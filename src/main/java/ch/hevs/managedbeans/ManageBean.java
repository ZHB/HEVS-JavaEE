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
import ch.hevs.clubservice.Club;

@ManagedBean(name="manageBean")
@RequestScoped
public class ManageBean {
	
	
	@EJB(name = "ManageBean") 
	private Club club;
	
	private Long pilotId;
	
	private List<Pilot> pilots;
	
	private Pilot pilot;
	private Licence licence;

	@PostConstruct
    public void initialize() {
		pilots = club.getPilots();
		
		pilot = new Pilot();
		licence = new Licence();
    }
	
	public String loadPilot() {
		if(pilotId == null) {
			return null;
		}
		pilot = club.getPilotById(pilotId);
		licence = pilot.getLicence();
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

	public Long getPilotId() {
		return pilotId;
	}

	public void setPilotId(Long pilotId) {
		this.pilotId = pilotId;
	}

	public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
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
	
	/**
	 * Add a pilot to DB
	 * @return
	 */
	public String addOrUpdatePilot() {
		club.addOrUpdatePilot(pilot, licence);
		
		return "adminForm";
	}
	
	public String removePilot() {	
		if(pilotId == null) {
			return null;
		}
		
		System.out.println("#############" + pilotId);
		club.removePilot(pilotId);
		return "adminForm";
	}
}
