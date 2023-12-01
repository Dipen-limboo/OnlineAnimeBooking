package com.springbootAnmte.animte.service;

import java.util.List;

import com.springbootAnmte.animte.entity.Event;

public interface AnmteService {
	List <Event> getallEvent();  
	Event saveEvent(Event event);
	Event getEventById(Long id);
	Event updateEvents(Event existingEvent);
	void deleteEventById(Long id);	
}
