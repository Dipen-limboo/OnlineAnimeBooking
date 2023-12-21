package com.springbootAnmte.animte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootAnmte.animte.entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Long >{
	
}
