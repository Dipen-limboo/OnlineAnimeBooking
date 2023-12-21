package com.springbootAnmte.animte.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springbootAnmte.animte.entity.Booking;
import com.springbootAnmte.animte.service.BookingService;

@Controller
public class BookingController {
	
	private BookingService bookingService;

	public BookingController(BookingService bookingService) {
		super();
		this.bookingService = bookingService;
	}
	
	@PostMapping("/booking/eas{eid}anduisd{uid}")
	public String makeBooking(@ModelAttribute("bookings") Booking booking, @PathVariable Long eid, @PathVariable Long uid,  Model model) {
		bookingService.makeBooking(booking, uid, eid);
		return "bookedSuccesfull";
	}
}
