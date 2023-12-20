package com.springbootAnmte.animte.entity;



import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long eventId;

    @Column(name ="event_title")
    @NotBlank(message ="Event title is required!!")
    private String eventTitle;

    @Column(name="start_date", nullable=false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name="end_date", nullable=false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Column(name="start_time", nullable=false)
    @DateTimeFormat(pattern = "hh:mm a")
    private LocalTime startTime;

    @Column(name="end_time", nullable=false)
    @DateTimeFormat(pattern = "hh:mm a")
    private LocalTime endTime;

    @Column(name="event_location")
    @NotBlank(message ="Event location is required!!")
    private String eventLocation;

    @Column(name="allocated")
    @NotNull(message ="Enter the allocated tickets for event!!")
    private long allocatedTickets;

    @Column(name="event_image")
    @NotBlank(message ="Image is required!!")
    private String eventImage;
    
    @Column(name="image_location")
    @Value("${image.dir:defaultValue}")
    private String imageLocation;
    
    

    @Column(name="event_description")
    private String eventDescription;

    @Column(name="ticket_price")
    @NotNull(message ="Price of ticket is required!!")
    private double ticketPrice;
    
    @Column(name="trending")
    private boolean trending = false;

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Event(@NotBlank(message = "Event title is required!!") String eventTitle, LocalDate startDate,
			LocalDate endDate, LocalTime startTime, LocalTime endTime,
			@NotBlank(message = "Event location is required!!") String eventLocation,
			@NotNull(message = "Enter the allocated tickets for event!!") long allocatedTickets,
			@NotBlank(message = "Image is required!!") String eventImage, String imageLocation, String eventDescription,
			@NotNull(message = "Price of ticket is required!!") double ticketPrice, boolean trending) {
		super();
		this.eventTitle = eventTitle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.eventLocation = eventLocation;
		this.allocatedTickets = allocatedTickets;
		this.eventImage = eventImage;
		this.imageLocation = imageLocation;
		this.eventDescription = eventDescription;
		this.ticketPrice = ticketPrice;
		this.trending = trending;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public long getAllocatedTickets() {
		return allocatedTickets;
	}

	public void setAllocatedTickets(long allocatedTickets) {
		this.allocatedTickets = allocatedTickets;
	}

	public String getEventImage() {
		return eventImage;
	}

	public void setEventImage(String eventImage) {
		this.eventImage = eventImage;
	}

	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public boolean isTrending() {
		return trending;
	}

	public void setTrending(boolean trending) {
		this.trending = trending;
	}

	
    
}

