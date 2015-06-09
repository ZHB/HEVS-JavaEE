package ch.hevs.converters;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import ch.hevs.businessobject.Site;
import ch.hevs.clubservice.Club;

@Model
@FacesConverter("convert.site")
public class SiteConverter implements Converter {

	@Inject
    private Club club; 
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		System.out.println("###" + value + "===");
		
		if (value == null || value.isEmpty() || value.contains("Choose")) {
            return null;
        }

        try {
              
            Object obj =  club.getSiteById(Long.valueOf(value));
            
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConverterException(new FacesMessage(String.format("Cannot convert %s to Site", value)), e);
        }
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		
		if (!(value instanceof Site)) {
            return null;
        }

       String s =  String.valueOf(((Site) value).getId());
       
       return s;
	}	
}
