package ch.hevs.converters;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import ch.hevs.businessobject.Plane;
import ch.hevs.clubservice.Club;

@Model
@FacesConverter("convert.plane")
public class PlaneConverter implements Converter {
	@Inject
    private Club club; 
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		if (value == null || value.isEmpty() || value.contains("Choose")) {
            return null;
        }

        try {
              
            Object obj =  club.getPlaneById(Long.valueOf(value));
            
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConverterException(new FacesMessage(String.format("Cannot convert %s to Plane", value)), e);
        }
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		
		if (!(value instanceof Plane)) {
            return null;
        }

       String s =  String.valueOf(((Plane) value).getId());
       
       return s;
	}	
}
