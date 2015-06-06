package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

import ch.hevs.businessobject.Flight;
import ch.hevs.businessobject.Gender;
import ch.hevs.businessobject.Pilot;
import ch.hevs.businessobject.Plane;
import ch.hevs.businessobject.Site;
import ch.hevs.clubservice.Club;

/**
 * TransferBean.java
 * 
 */
@ManagedBean(name="siteBean")
@NoneScoped
public class SiteBean
{
	
	private Site site;
	
	
	@EJB(name = "ClubBean") 
	private Club club;
	
	/**
	 * 
	 */
	@PostConstruct
    public void initialize() {
 		
		FacesContext fc = FacesContext.getCurrentInstance();
		long test = Long.parseLong(getSiteParam(fc));

		site = club.getSiteById(test);
    }

	public Site getSite() {
		
		
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}
	
	//get value from "f:param"
	public String getSiteParam(FacesContext fc){

		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("id");
 
	}
}
