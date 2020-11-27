/**
 * 
 */
package com.walmart.ticketservice.dao;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.stereotype.Component;

import com.walmart.ticketservice.model.SeatHold;

/**
 * @author sgokavar
 *
 */
@Component
public interface SeatDAO {
	
    public int numOfAvailableSeats();
    
     SeatHold findAndHoldSeats(int numSeats, String customerEmail);
    
    public int reserveSeats(int seatHoldId, String customerEmail);

	public void setDataSource(EmbeddedDatabase db);

}
