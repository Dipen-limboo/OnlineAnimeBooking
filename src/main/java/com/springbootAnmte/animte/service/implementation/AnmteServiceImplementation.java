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

import com.springbootAnmte.animte.entity.Anime;
import com.springbootAnmte.animte.entity.Event;
import com.springbootAnmte.animte.repository.AnimeRepo;
import com.springbootAnmte.animte.repository.AnmteEntityRepository;
import com.springbootAnmte.animte.service.AnmteService;

@Service
public class AnmteServiceImplementation implements AnmteService{
	AnmteEntityRepository anmteRepository;
	AnimeRepo anp;
	
	
	public AnmteServiceImplementation(AnmteEntityRepository anmteRepository, AnimeRepo anp) {
		super();
		this.anmteRepository = anmteRepository;
		this.anp = anp;
	}






	//------------------------------------Event Panel---------------------------
	private final Path root = Paths.get("./src/main/resources/static/Images");

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
	
	
	
	
	

	//--------------------------Anime Panel-------------------------------
	
	private final Path videoRoot = Paths.get("./src/main/resources/static/Videos");
	private final Path trailerRoot = Paths.get("./src/main/resources/static/Trailer");

	


	@Override
	public Anime saveAnime(Anime anime) {
		return anp.save(anime);
	}
	
	@Override
	public String saveAnime(MultipartFile image, Anime anime) {
	    try {
	        String imageName = image.getOriginalFilename();
	        Files.copy(image.getInputStream(), this.root.resolve(imageName));
	        return imageName;
	    } catch (Exception e) {
	        if (e instanceof FileAlreadyExistsException) {
	            throw new RuntimeException("A file of that name already exists.");
	        }
	        throw new RuntimeException(e.getMessage());
	    }
	}


	@Override
	public String saveVideo(MultipartFile video, Anime anime) {
		try {
	        String videoName = video.getOriginalFilename();
	        Files.copy(video.getInputStream(), this.videoRoot.resolve(videoName));
	        return videoName;
	    } catch (Exception e) {
	        if (e instanceof FileAlreadyExistsException) {
	            throw new RuntimeException("A file of that name already exists.");
	        }
	        throw new RuntimeException(e.getMessage());
	    }
	}


	@Override
	public String saveTrailer(MultipartFile trailer, Anime anime) {
		try {
	        String trailerName = trailer.getOriginalFilename();
	        Files.copy(trailer.getInputStream(), this.trailerRoot.resolve(trailerName));
	        return trailerName;
	    } catch (Exception e) {
	        if (e instanceof FileAlreadyExistsException) {
	            throw new RuntimeException("A file of that name already exists.");
	        }
	        throw new RuntimeException(e.getMessage());
	    }
	}

	@Override
	public List<Anime> getallAnime() {
		return anp.findAll();
	}

	@Override
	public Resource loadVideo(String filename) {
		try {
			Path path = videoRoot.resolve(filename);
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

	@Override
	public Resource loadTrailer(String filename) {
		try {
			Path path = trailerRoot.resolve(filename);
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

	@Override
	public Anime getAnimeById(Long id) {
		return anp.findById(id).get();
		}

	@Override
	public Anime updateAnime(Anime existingAnime) {
		return anp.save(existingAnime);
	}

	@Override
	public void deleteAnimeById(Long id) {
		anp.deleteById(id);
	}

	@Override
	public List<Event> getTrendingEvents() {
		return anmteRepository.findByTrending(Boolean.TRUE);
	}

	@Override
	public Event getEventByEventTitle(String search) {
		return anmteRepository.findByEventTitle(search);
	}

	@Override
	public Anime getAnimeByAnimeName(String search) {
		return anp.findByAnimeName(search);
	}

	@Override
	public Anime getAnimeByCategory(String search) {
		return anp.findByAnimeCategory(search);
	}


}
