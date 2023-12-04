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
	    return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"" + file.getFilename() + "\"")
	            .body(file);
	}

}