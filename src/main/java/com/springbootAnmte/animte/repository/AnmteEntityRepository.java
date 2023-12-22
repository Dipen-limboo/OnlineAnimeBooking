package com.springbootAnmte.animte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootAnmte.animte.entity.Event;


public interface AnmteEntityRepository extends JpaRepository<Event, Long> {
	List<Event> findByTrending(Boolean trending);
	
	Event findByEventTitle(String search);
}
