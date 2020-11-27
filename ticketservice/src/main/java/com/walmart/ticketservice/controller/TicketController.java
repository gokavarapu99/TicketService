/**
 * 
 */
package com.walmart.ticketservice.controller;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.ticketservice.TicketserviceApplication;
import com.walmart.ticketservice.dao.SeatDAO;
import com.walmart.ticketservice.model.BookingConfirmation;
import com.walmart.ticketservice.model.SeatAvailabilityInfo;
import com.walmart.ticketservice.model.SeatHold;
import com.walmart.ticketservice.service.TicketService;

/**
 * @author sgokavar
 *
 */
@RestController
public class TicketController {

	private static final Logger log = LoggerFactory.getLogger(TicketController.class);

	@Autowired
	private TicketService ticketService;

	@GetMapping("/")
	public String home() {
		return "Hi, Welcome to Ticketing Service app.";
	}

	@GetMapping("/seats")
	public ResponseEntity<SeatAvailabilityInfo> getNumberOfSeats() {
		int noOfseats = ticketService.getnumOfAvailableSeats();
		Integer numberOfSeats = Integer.valueOf(noOfseats);
		SeatAvailabilityInfo seatAvailabilityInfo;
		if (noOfseats <= 0) {
			log.info("All seats are Sold out. We don't any more available Seats");
			seatAvailabilityInfo = new SeatAvailabilityInfo(numberOfSeats,"Sorry, We are sold out !");
			return new ResponseEntity<SeatAvailabilityInfo>(seatAvailabilityInfo,HttpStatus.OK);
		}
		log.info("Number Of Available seats "+noOfseats);
		seatAvailabilityInfo = new SeatAvailabilityInfo(numberOfSeats,"Hi , We have "+noOfseats+" seats");
		return new ResponseEntity<SeatAvailabilityInfo>(seatAvailabilityInfo,HttpStatus.OK);
	}


	@PutMapping("/hold")
	public ResponseEntity<SeatHold> findAndHoldSeats(
			@Min(value=1, message = "Please select atleast one seat to Hold.")
			@Max(value=10, message = "Sorry. We have a limit of 10 seats per request. Please select up to max 10 seats.") 
			@RequestParam(value = "numSeats", required = true) int numSeats,
			@RequestParam(value = "customerEmail", required = true) String customerEmail) {
		
			SeatHold seatHold =  ticketService.findandHoldSeats(numSeats, customerEmail);

			if(seatHold.getSeat().size() <= 0) {
				log.info("We are unable to hold seats. We are sold out.");
				return new ResponseEntity<SeatHold>(seatHold,HttpStatus.OK);
			}
			log.info("We are able to hold your seats.");
			return new ResponseEntity<SeatHold>(seatHold,HttpStatus.OK);
	} 

	@PutMapping("/reserve")
	public ResponseEntity<BookingConfirmation> reserveSeats(
			@RequestParam(value = "seatHoldId", required = true) int seatHoldId,@Email(message="Please enter valid email address.") 
			@RequestParam(value = "customerEmail", required = true) String customerEmail) {


		int confirmationCode = ticketService.reserveSeats(seatHoldId,
				customerEmail);
		Integer code = Integer.valueOf(confirmationCode);
		BookingConfirmation bookingConfirmation;
		if (confirmationCode <= 0) {
			log.info("We are unable to reserve seats. We are sold out.");
			bookingConfirmation = new BookingConfirmation("Unable to Reserve tickets. We are sold out !",code,customerEmail);
			return new ResponseEntity<BookingConfirmation>(bookingConfirmation,HttpStatus.OK);
		}
		log.info("We are able to reserve your seats and the confirmation code is "+confirmationCode);
		bookingConfirmation = new BookingConfirmation("Hurray...!!!! your tickets got Booked and your ConfirmationCode is "+code,code,customerEmail);
		return new ResponseEntity<BookingConfirmation>(bookingConfirmation,HttpStatus.OK);


	}



}



