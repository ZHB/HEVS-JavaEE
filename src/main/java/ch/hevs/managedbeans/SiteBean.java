package ch.hevs.managedbeans;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.context.FacesContext;

import ch.hevs.businessobject.Site;
import ch.hevs.clubservice.Club;

@ManagedBean(name="siteBean")
@NoneScoped
public class SiteBean
{
	/**
	 * The current site to display
	 */
	private Site site;
	
	@EJB(name = "ClubBean") 
	private Club club;
	
	/**
	 * This method is called after the class instantiation
	 * and after the constructor call to be sure that the bean was
	 * fully initialized before doing calls
	 */
	@PostConstruct
    public void initialize() {
 		
		FacesContext fc = FacesContext.getCurrentInstance();
		long id = Long.parseLong(getSiteParam(fc));
		
		site = club.getSiteById(id);
    }

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}
	
	/**
	 * Get query string value (from f:param)
	 * 
	 * @param fc
	 * @return A Map of params.
	 */
	public String getSiteParam(FacesContext fc){

		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		
		return params.get("id");
	}
}

