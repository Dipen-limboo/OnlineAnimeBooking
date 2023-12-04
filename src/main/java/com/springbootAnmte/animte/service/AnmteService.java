package com.springbootAnmte.animte.service;

import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import com.springbootAnmte.animte.entity.Anime;
import com.springbootAnmte.animte.entity.Event;

public interface AnmteService {
	List <Event> getallEvent();  
	Event saveEvent(Event event);
	Event getEventById(Long id);
	Event updateEvents(Event existingEvent);
	void deleteEventById(Long id);	
	public Resource load(String filename);
	String save(MultipartFile file, Event event);
	
	
	//anime;
	String saveAnime(MultipartFile image, Anime anime);
	String saveVideo(MultipartFile video, Anime anime);
	String saveTrailer(MultipartFile trailer, Anime anime);
	Anime saveAnime(Anime anime);
	List<Anime> getallAnime();
	public Resource loadVideo(String filename);
	public Resource loadTrailer(String filename);
	Anime getAnimeById(Long id);
	
}
