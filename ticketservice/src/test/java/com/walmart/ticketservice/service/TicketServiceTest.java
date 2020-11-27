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
import com.walmart.ticketservice.dao.SeatDAO;
import com.walmart.ticketservice.dao.SeatDAOImpl;
import com.walmart.ticketservice.model.Seat;
import com.walmart.ticketservice.model.SeatHold;

import static org.mockito.Mockito.doReturn;


/**
 * @author sgokavar
 *
 */

@RunWith(MockitoJUnitRunner.class) 
public class TicketServiceTest {

    @InjectMocks
	public TicketService ticketService = new TicketService();
	
	@Spy
    private SeatDAO seatDAOMock = new SeatDAOImpl();
	

	
	@Test
	public void testnumOfAvailableSeats() {
		doReturn(10).when(seatDAOMock).numOfAvailableSeats();
		//when(seatDAOMock.numOfAvailableSeats()).thenReturn(10);

		assertEquals(ticketService.getnumOfAvailableSeats(),10);
	}
	
	@Test
	public void testfindandHoldSeats() {
		Seat seat1 = new Seat((long)(1),1,1,"Stadium View");
		Seat seat2 = new Seat((long)(1),1,1,"Stadium View");
		List<Seat> seat = new ArrayList<>();
		seat.add(seat1);
		seat.add(seat2);
		SeatHold seatHold = new SeatHold((long)1,"test@gmail.com",seat);
	
		doReturn(seatHold).when(seatDAOMock).findAndHoldSeats(2, "test@gmail.com");
		int result = ticketService.findandHoldSeats(2, "test@gmail.com").getSeat().size();
		assertEquals(result, 2);
	}
	
	@Test
	public void testreserveSeats() {

		doReturn(2).when(seatDAOMock).reserveSeats(1, "test@gmail.com");
		int result = ticketService.reserveSeats(1, "test@gmail.com");
		assertEquals(result, 2);
	}

}
