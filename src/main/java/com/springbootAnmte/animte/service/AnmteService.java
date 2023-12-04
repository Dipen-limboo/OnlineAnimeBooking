package com.springbootAnmte.animte.service;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.springbootAnmte.animte.entity.Event;

public interface AnmteService {
	List <Event> getallEvent();  
	Event saveEvent(Event event);
	Event getEventById(Long id);
	Event updateEvents(Event existingEvent);
	void deleteEventById(Long id);	
	public Resource load(String filename);
	String save(MultipartFile file, Event event);
	
}
