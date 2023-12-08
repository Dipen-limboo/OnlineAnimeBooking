package com.springbootAnmte.animte.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springbootAnmte.animte.entity.Anime;
import com.springbootAnmte.animte.entity.Event;
import com.springbootAnmte.animte.service.AnmteService;

@Controller
public class DisplayController {
	@Autowired
	private AnmteService anmteService;

	public DisplayController(AnmteService anmteService) {
		super();
		this.anmteService = anmteService;
	}
	
	
	//------------------------display controller-------------------------------
	
	@GetMapping("/home")
	public String dispalyEvents(Model model) {
		model.addAttribute("animes", anmteService.getallAnime());
		List<Event> trendingEvents = anmteService.getTrendingEvents();
		model.addAttribute("events", anmteService.getallEvent());	
		model.addAttribute("trending", trendingEvents);
		
		return "home";
	}
	
	@GetMapping("/eventpanel")
	public String showEvents(Model model) {
		Event ev1 = anmteService.getEventById(1L);
		Event ev2 = anmteService.getEventById(3L);
		List<Event> trendingEvents = anmteService.getTrendingEvents();
		model.addAttribute("events", anmteService.getallEvent());	
		model.addAttribute("event", ev1);
		model.addAttribute("event2", ev2);
		model.addAttribute("trending", trendingEvents);
		
		return "event";
	}
	
	@GetMapping("/ticket/evsd{evId}")
	public String eventShow(@PathVariable Long evId, Model model) {
	Event event = anmteService.getEventById(evId);
	model.addAttribute("events", event);
	return "eventShow";
	}
	
	
	
//	------------------------video-------------------------------
	
	@GetMapping("/video")
	public String showVideo(Model model) {
		Anime anime = anmteService.getAnimeById(1l);
		model.addAttribute("animes", anmteService.getallAnime());
		model.addAttribute("anime", anime);
		return "video";
	}
	
	@GetMapping("/video/linksbs{id}")
	public String linkVideo(@PathVariable Long id, Model model) {
		Anime anime = anmteService.getAnimeById(id);
		model.addAttribute("anime", anime);
		return "animeShow";
	}
	
	
	
	
}




















