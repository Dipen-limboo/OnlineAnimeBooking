package com.springbootAnmte.animte.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springbootAnmte.animte.entity.Booking;
import com.springbootAnmte.animte.entity.Event;
import com.springbootAnmte.animte.entity.User;
import com.springbootAnmte.animte.repository.BookingRepo;
import com.springbootAnmte.animte.service.AnmteService;
import com.springbootAnmte.animte.service.BookingService;
import com.springbootAnmte.animte.service.UserService;

@Service
public class BookingServiceImplementation implements BookingService{

	private BookingRepo bookingRepo;
	private AnmteService eventService;
	private UserService userService;

	public BookingServiceImplementation(BookingRepo bookingRepo, AnmteService eventService, UserService userService) {
		super();
		this.bookingRepo = bookingRepo;
		this.eventService = eventService;
		this.userService = userService;
	}

	@Override
	public void makeBooking(Booking book, Long userId, Long eventId) {
		Booking booking = new Booking();
		booking.setBookingDate(LocalDate.now());
		booking.setNumberOfTickets(book.getNumberOfTickets());
		Event event = eventService.getEventById(eventId);
	    User user = userService.getUserById(userId);

	    booking.setEvents(event);
	    booking.setUsers(user);

	    event.getBookings().add(booking);
	    user.getBookings().add(booking);
		
		bookingRepo.save(booking);
	}

	@Override
	public List<Booking> allBookings() {
		return bookingRepo.findAll();
	}

	@Override
	public Booking getBookingById(Long bookingId) {
		return bookingRepo.findById(bookingId).get();
	}

	@Override
	public Booking deleteBookingById(Long bookingId) {
		Optional<Booking> optionalBooking = bookingRepo.findById(bookingId);

	    if (optionalBooking.isPresent()) {
	        Booking booking = optionalBooking.get();
	        booking.getUsers().getBookings().remove(booking);
	        booking.getEvents().getBookings().remove(booking);
	        bookingRepo.deleteById(bookingId);
	        return booking;
	    } else {
	        return null;
	    }
	}
	
}
