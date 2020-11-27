/**
 * 
 */
package com.walmart.ticketservice.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import com.walmart.ticketservice.dao.SeatDAO;
import com.walmart.ticketservice.dao.SeatDAOImpl;
import com.walmart.ticketservice.model.SeatHold;


/**
 * @author sgokavar
 *
 */

public class TickerServiceDatabaseTest {
	
	private static final Logger log = LoggerFactory.getLogger(TickerServiceDatabaseTest.class);

	@Autowired
	private SeatDAO seatDAO;


	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	 @Before
	  public void setup() {
		 log.info("HI In Databsae setup");
	      EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
	              .setType(EmbeddedDatabaseType.H2)
	              .addScript("data.sql")
	              .build();
	      seatDAO = new SeatDAOImpl();
	      seatDAO.setDataSource(db);
	      
	  }
	
	@Test
	public void testnumOfAvailableSeats() {
		assertTrue(seatDAO.numOfAvailableSeats() > 0);
	}
	

	@Test
	public void testReserveSeats() {
		SeatHold seathold = seatDAO.findAndHoldSeats(1, "test@gmail.com");
		assertEquals(seatDAO.reserveSeats(seathold.getSeatHoldId().intValue(), "test1@gmail.com"), seathold.getSeatHoldId().intValue());
	}
	
	@Test
	public void testFindAndHoldSeats() {
		log.info("TickerServiceDatabaseTest , testFindAndHoldSeats");
		SeatHold seathold = seatDAO.findAndHoldSeats(1, "satya@gmail.com");
		log.info("testFindAndHoldSeats"+seathold);
		assertTrue(seathold.getSeat().size() > 0);
	}
	


}
