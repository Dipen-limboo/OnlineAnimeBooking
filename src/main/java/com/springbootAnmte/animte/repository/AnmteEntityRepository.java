package com.springbootAnmte.animte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootAnmte.animte.entity.Event;


public interface AnmteEntityRepository extends JpaRepository<Event, Long> {
	
}
