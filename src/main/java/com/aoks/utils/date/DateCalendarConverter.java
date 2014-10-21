package com.aoks.utils.date;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named("dateCalendarConverter")
@FacesConverter(forClass=Calendar.class, value="dateCalendarConverter")
public class DateCalendarConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Calendar parsed = DateUtils.parseDate8(value);
		if (parsed != null)
			return new GregorianCalendar(parsed.get(Calendar.YEAR), parsed.get(Calendar.MONTH), parsed.get(Calendar.DAY_OF_MONTH));
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return DateUtils.format8((Calendar) value);
	}

}
