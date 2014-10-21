package com.aoks.portalmanager.event;

import java.io.Serializable;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.aoks.portalmanager.model.PortalAggregate;


public class AggregateLoadedHandlerImpl implements AggregateLoadedHandler, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Event<PortalAggregate> aggregateLoaded;

	@Override
	public void handleAggregateLoading(PortalAggregate aggregate) {
		aggregateLoaded.fire(aggregate);
	}

}
