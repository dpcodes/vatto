package com.aoks.budget.control.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("debitCreditTranslator_pt")
public class DebitCreditPortugueseConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String submittedValue) {
	
		if (submittedValue.trim().equals("")) {  
            return "";  
        } else {  
            try {
            	
            } catch(NumberFormatException exception) {  
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid company"));  
            }  
        }  
  
        return null;  
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value == null || value.equals("")) {  
            return "";  
        } else {  
        	return "";
        }
		
	}

	
	
}
