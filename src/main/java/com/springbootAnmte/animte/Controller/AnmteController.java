package com.springbootAnmte.animte.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.springbootAnmte.animte.entity.Anime;
import com.springbootAnmte.animte.entity.Event;
import com.springbootAnmte.animte.service.AnmteService;

@Controller

public class AnmteController {
	
	@Autowired
	private AnmteService anmteService;

	public AnmteController(AnmteService anmteService) {
		super();
		this.anmteService = anmteService;
	}
		
	//------------------------------------Event Panel---------------------------
	@GetMapping("/event")
	public String createEvent(Model model) {
		Event event = new Event();
		model.addAttribute("event", event);
		return "event_panel";
	}
	
	
	@PostMapping("/event")
	public String saveNewEvent(@ModelAttribute("event") Event event, Model model, @RequestParam("image") MultipartFile file) {
	    String imageName = anmteService.save(file, event); // Save the file and get the image name
	    event.setEventImage(imageName); // Set the image name in the Event object
	    anmteService.saveEvent(event); // Save the event data with the image name
	    return "redirect:/view";
	}

		
	@GetMapping("/view")
	public String listEvents(Model model) {
		model.addAttribute("events", anmteService.getallEvent());
		return "viewEvents";
	}
	
	@GetMapping("/event/edit/{id}")
	public String editEvent(@PathVariable Long id, Model model) {
	    Event event = anmteService.getEventById(id);
	    event.setEventImage("/images/" + event.getEventImage());
	    event.setEventId(id);
	    model.addAttribute("events", event);
	    return "editEvents";
	}

	@PostMapping("event/{id}")
	public String updateEvents(@PathVariable Long id, @ModelAttribute("event") Event event, Model model,  @RequestParam("image") MultipartFile file) {
		String imageName = anmteService.save(file, event);
		Event existingEvent = anmteService.getEventById(id);
		existingEvent.setEventTitle(event.getEventTitle());
		existingEvent.setStartDate(event.getStartDate());
		existingEvent.setEndDate(event.getEndDate());
		existingEvent.setStartTime(event.getStartTime());
		existingEvent.setEndTime(event.getEndTime());
		existingEvent.setAllocatedTickets(event.getAllocatedTickets());
		existingEvent.setTicketPrice(event.getTicketPrice());
		existingEvent.setEventLocation(event.getEventLocation());
		existingEvent.setEventDescription(event.getEventDescription());
		existingEvent.setEventImage(imageName);
		
		anmteService.updateEvents(existingEvent);
		return "redirect:/view";
	}
	
	@GetMapping("event/{id}")
	public String deleteEvents(@PathVariable Long id) {
		anmteService.deleteEventById(id);
		return "redirect:/view";
	}
	
	@GetMapping("/images/{filename}")
	@ResponseBody
	public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
	    Resource file = anmteService.load(filename);
	    System.out.println("Loaded File: " + file.getFilename());
	    return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"" + file.getFilename() + "\"")
	            .body(file);
	}
	
	
	
	

	
	
	
	
	//--------------------------Anime Panel-------------------------------
	@GetMapping("/anime")
	public String addAnime(Model model) {
		Anime anime = new Anime();
		model.addAttribute("anime", anime);
		return "addAnime";
	}
	
	@PostMapping("/anime")
	public String saveNewAnime(@ModelAttribute("anime") Anime anime, Model model, @RequestParam("image") MultipartFile image, @RequestParam("video") MultipartFile video, @RequestParam("trailer") MultipartFile trailer ) {
	    String imageName = anmteService.saveAnime(image,anime);
	    String videoName = anmteService.saveVideo(video, anime);
	    String trailerName = anmteService.saveTrailer(trailer, anime);
	    anime.setAnimeImage(imageName); // Set the image name in the Event object
	    anime.setAnimeVideo(videoName);
	    anime.setAnimeTrailer(trailerName);
	    anmteService.saveAnime(anime); // Save the event data with the image name
	    return "redirect:/viewAnime";
	}
	
	@GetMapping("/viewAnime")
	public String listAnime(Model model) {
		model.addAttribute("animes", anmteService.getallAnime());
		return "animeDetails";
	}
	
	
	@GetMapping("/videos/{filename}")
	@ResponseBody
	public ResponseEntity<Resource> serveVideo(@PathVariable String filename) {
	    Resource file = anmteService.loadVideo(filename);
	    return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"" + file.getFilename() + "\"")
	            .body(file);
	}
	
	@GetMapping("/trailer/{filename}")
	@ResponseBody
	public ResponseEntity<Resource> serveTrailer(@PathVariable String filename) {
	    Resource file = anmteService.loadTrailer(filename);
	    return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"" + file.getFilename() + "\"")
	            .body(file);
	}
	
	@GetMapping("/anime/edit/{id}")
	public String editAnime(@PathVariable Long id, Model model) {
		Anime anime = anmteService.getAnimeById(id);
		
	    anime.setAnimeImage("/images/" + anime.getAnimeImage());
	    
	    anime.setAnimeTrailer("/trailer/" +anime.getAnimeTrailer());
	    anime.setAnimeVideo("/videos/" +anime.getAnimeVideo());
	    anime.setId(id);
	    model.addAttribute("animes", anime);
	    System.out.println(anime.getAnimeImage());
	    return "editAnime";
	}
	
	@PostMapping("anime/{id}")
	public String updateAnime(@PathVariable Long id, 
			@ModelAttribute("anime") Anime anime, 
			Model model,  
			@RequestParam("image") MultipartFile image,
			@RequestParam("video") MultipartFile video,
			@RequestParam("trailer") MultipartFile trailer) {
		String imageName = anmteService.saveAnime(image, anime);
		String videoName = anmteService.saveVideo(video, anime);
		String trailerName = anmteService.saveTrailer(trailer, anime);
		Anime existingAnime = anmteService.getAnimeById(id);
		existingAnime.setAnimeName(anime.getAnimeName());
		existingAnime.setAnimeCategory(anime.getAnimeCategory());
		existingAnime.setAnimeWriter(anime.getAnimeWriter());
		existingAnime.setAnimeStory(anime.getAnimeStory());
		existingAnime.setAnimeImage(imageName);
		existingAnime.setAnimeVideo(videoName);
		existingAnime.setAnimeTrailer(trailerName);
		
		anmteService.updateAnime(existingAnime);
		return "redirect:/viewAnime";
	}
	
	@GetMapping("anime/{id}")
	public String deleteAnime(@PathVariable Long id) {
		anmteService.deleteAnimeById(id);
		return "redirect:/viewAnime";
	}
	

	
	
	
}