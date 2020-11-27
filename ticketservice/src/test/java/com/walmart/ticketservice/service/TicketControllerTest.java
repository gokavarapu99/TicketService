/**
 * 
 */
package com.walmart.ticketservice.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.walmart.ticketservice.controller.TicketController;
import com.walmart.ticketservice.model.BookingConfirmation;
import com.walmart.ticketservice.model.Seat;
import com.walmart.ticketservice.model.SeatHold;

import static org.mockito.Mockito.doReturn;


/**
 * @author sgokavar
 *
 */

@RunWith(MockitoJUnitRunner.class) 
public class TicketControllerTest {

	@InjectMocks
	public TicketController ticketController = new TicketController();

	@Spy
	private TicketService ticketService = new TicketService();
	
	
	@Test public void testhome() {
		assertEquals(ticketController.home(),"Hi, Welcome to Ticketing Service app.");
	}

	@Test
	public void testnumOfAvailableSeatsPositiveCase() {
		doReturn(10).when(ticketService).getnumOfAvailableSeats();
		assertEquals(ticketController.getNumberOfSeats().getStatusCode(),HttpStatus.OK);
		assertEquals(ticketController.getNumberOfSeats().getBody().getNoOfSeats(),10);
	}


	@Test public void testfindandHoldSeatsPositiveCase() { 
		Seat seat1 = new Seat((long)(1),1,1,"Stadium View");
		Seat seat2 = new Seat((long)(1),1,1,"Stadium View"); List<Seat> seat = new ArrayList<>();
		seat.add(seat1); 
		seat.add(seat2); 
		SeatHold seatHold = new SeatHold((long)1,"test@gmail.com",seat);

		doReturn(seatHold).when(ticketService).findandHoldSeats(2, "test@gmail.com");
		ResponseEntity<SeatHold>  result = ticketController.findAndHoldSeats(2, "test@gmail.com");
		assertEquals(result.getStatusCode(),HttpStatus.OK);
		assertEquals(result.getBody().getSeat().size(),2);
	}

	@Test public void testreserveSeatsPositiveCase() {

		doReturn(1).when(ticketService).reserveSeats(1, "test@gmail.com"); 
		ResponseEntity<BookingConfirmation> result = ticketController.reserveSeats(1, "test@gmail.com"); 
		assertEquals(result.getBody().getMessage(), "Hurray...!!!! your tickets got Booked and your ConfirmationCode is 1");

	}


}
