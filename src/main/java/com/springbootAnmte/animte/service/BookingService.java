package com.springbootAnmte.animte.service;

import java.util.List;

import com.springbootAnmte.animte.entity.Booking;
import com.springbootAnmte.animte.entity.Event;
import com.springbootAnmte.animte.entity.User;

public interface BookingService {
	
	
	public List<Booking> allBookings();
	
	public Booking getBookingById(Long bookingId);
	
	public Booking deleteBookingById(Long bookingId);

	void makeBooking(Booking book, Long userId, Long eventId);
}
