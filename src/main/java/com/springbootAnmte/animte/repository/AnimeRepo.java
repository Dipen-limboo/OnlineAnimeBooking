package com.springbootAnmte.animte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootAnmte.animte.entity.Anime;
import com.springbootAnmte.animte.entity.Event;

public interface AnimeRepo extends JpaRepository<Anime, Long>{

	
}
