package com.aoks.portalmanager.event;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.aoks.portalmanager.model.PortalApplication;


@ApplicationLoaded
@SessionScoped
public class ApplicationLoadingHandlerImpl implements ApplicationLoadingHandler, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Event<PortalApplication> applicationLoaded;
	
	public void handleApplicationLoading(PortalApplication application){
		applicationLoaded.fire(application);
	}
	
}
