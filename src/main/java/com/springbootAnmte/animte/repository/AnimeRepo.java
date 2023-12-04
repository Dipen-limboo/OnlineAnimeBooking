package com.springbootAnmte.animte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootAnmte.animte.entity.Anime;

public interface AnimeRepo extends JpaRepository<Anime, Long>{

}
