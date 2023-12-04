package com.springbootAnmte.animte.service.implementation;

import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springbootAnmte.animte.entity.Event;
import com.springbootAnmte.animte.repository.AnmteEntityRepository;
import com.springbootAnmte.animte.service.AnmteService;

@Service
public class AnmteServiceImplementation implements AnmteService{
	AnmteEntityRepository anmteRepository;
	private final Path root = Paths.get("./src/main/resources/static/Images");

	

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

	
	@Override
	public String save(MultipartFile file, Event event) {
	    try {
	        String imageName = file.getOriginalFilename();
	        Files.copy(file.getInputStream(), this.root.resolve(imageName));
	        return imageName;
	    } catch (Exception e) {
	        if (e instanceof FileAlreadyExistsException) {
	            throw new RuntimeException("A file of that name already exists.");
	        }
	        throw new RuntimeException(e.getMessage());
	    }
	}


	@Override
	public Resource load(String filename) {
		try {
			Path path = root.resolve(filename);
			Resource resource = new UrlResource(path.toUri());
			if(resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("A file is not readable");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: "+ e.getMessage());
		}
	}


}
