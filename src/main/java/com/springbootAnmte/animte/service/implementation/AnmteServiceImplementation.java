package com.springbootAnmte.animte.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springbootAnmte.animte.entity.Event;
import com.springbootAnmte.animte.repository.AnmteEntityRepository;
import com.springbootAnmte.animte.service.AnmteService;

@Service
public class AnmteServiceImplementation implements AnmteService{
	AnmteEntityRepository anmteRepository;
	
	

	public AnmteServiceImplementation(AnmteEntityRepository anmteRepository) {
		super();
		this.anmteRepository = anmteRepository;
	}


	@Override
	public List<Event> getallEvent() {
		return anmteRepository.findAll();
	}
	
	@Override
	public Event saveEvent(Event event) {
		return anmteRepository.save(event);
	}


	@Override
	public Event getEventById(Long id) {
		return anmteRepository.findById(id).get();
	}


	@Override
	public Event updateEvents(Event existingEvent) {
		return anmteRepository.save(existingEvent);
	}


	@Override
	public void deleteEventById(Long id) {
		anmteRepository.deleteById(id);
	}


}
