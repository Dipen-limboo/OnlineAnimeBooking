package com.springbootAnmte.animte.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="bookings")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;
	
	@Column(name ="booking_date")
	@DateTimeFormat(pattern="yyyy-MM--dd")
	private LocalDate bookingDate;
	
	@Column(name="buy_ticket")
	@NotNull(message="please enter the number of Tickets")
	private int numberOfTickets;
	
	@ManyToOne
	@JoinColumn(name="event_id")
	private Event events;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User users;

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Booking(Long bookingId, LocalDate bookingDate,
			@NotBlank(message = "please enter the number of Tickets") int numberOfTickets, Event events, User users) {
		super();
		this.bookingId = bookingId;
		this.bookingDate = bookingDate;
		this.numberOfTickets = numberOfTickets;
		this.events = events;
		this.users = users;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	public Event getEvents() {
		return events;
	}

	public void setEvents(Event events) {
		this.events = events;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}
	
	

}
