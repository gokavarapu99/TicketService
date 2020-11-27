/**
 * 
 */
package com.walmart.ticketservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.ticketservice.dao.SeatDAOImpl;
import com.walmart.ticketservice.model.SeatHold;

/**
 * @author sgokavar
 *
 */
@Service
public class TicketService {
	@Autowired
	private SeatDAOImpl seatDao;

	public int getnumOfAvailableSeats() {
		int totalAvailableSeats = seatDao.numOfAvailableSeats();
		return totalAvailableSeats;
	}
	
	public SeatHold findandHoldSeats(int numSeats,String customerEmail) {
		SeatHold seathold = seatDao.findAndHoldSeats(numSeats, customerEmail);
		return seathold;
	}
	
	public int reserveSeats(int seatHoldId, String customerEmail) {
		return seatDao.reserveSeats(seatHoldId, customerEmail);
		
	}
	
	
}
