package com.springbootAnmte.animte.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String saveNewEvent(@ModelAttribute("event") Event event) {
		anmteService.saveEvent(event);
		return "redirect:/view";
	}
	
	

	@GetMapping("/view")
	public String listEvents(Model model) {
		model.addAttribute("events", anmteService.getallEvent());
		return "viewEvents";
	}
	
	@GetMapping("/event/edit/{id}")
	public String editEvents(@PathVariable Long id, Model model) {
		model.addAttribute("events", anmteService.getEventById(id));
	
		return "editEvents";
		
	}
	
	@PostMapping("event/{id}")
	public String updateEvents(@PathVariable Long id, @ModelAttribute("event") Event event, Model model) {
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
		existingEvent.setEventImage(event.getEventImage());
		
		anmteService.updateEvents(existingEvent);
		return "redirect:/view";
	}
	
	@GetMapping("event/{id}")
	public String deleteEvents(@PathVariable Long id) {
		anmteService.deleteEventById(id);
		return "redirect:/view";
	}
	
}
