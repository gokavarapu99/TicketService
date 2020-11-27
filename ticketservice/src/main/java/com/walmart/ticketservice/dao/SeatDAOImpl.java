/**
 * 
 */
package com.walmart.ticketservice.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.walmart.ticketservice.model.Seat;
import com.walmart.ticketservice.model.SeatHold;

/**
 * @author sgokavar
 *
 */
@Component
public class SeatDAOImpl implements SeatDAO {
	
	private static final Logger log = LoggerFactory.getLogger(SeatDAOImpl.class);

	 @Autowired
	 JdbcTemplate jdbcTemplate;
	 
	 
	 @Override
		public void setDataSource(EmbeddedDatabase db) {
	      jdbcTemplate = new JdbcTemplate(db);
			
		}



	@Override
	public int numOfAvailableSeats() {
	    log.info("Querying for number of Seats available which are not on hold and reserved.");
	    int numOfAvailableSeats = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Seat WHERE  (DATEDIFF(second,LAST_HELD_TIME,CURRENT_TIMESTAMP()) > 900 and STATUS = 1) OR STATUS = 0",Integer.class); 
	    return numOfAvailableSeats;		
	}

	@Override
	@Transactional
	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
		log.info("SeatDAOImpl : findAndHoldSeats");
	    log.info("Querying for number of Seats available which are not on hold and reserved.");
	    String sqlSelect = "SELECT SEAT_ID,ROW_NUMBER,SEAT_NUMBER,DESCRIPTION FROM SEAT "
				+ "WHERE (DATEDIFF(second,LAST_HELD_TIME,CURRENT_TIMESTAMP()) > 900 and STATUS = 1) OR STATUS = 0"
				+ " ORDER BY ROW_NUMBER , SEAT_NUMBER LIMIT "+numSeats;
	    log.info(sqlSelect);
	    log.info(customerEmail);

	    List<Seat> seats = jdbcTemplate.query(sqlSelect,
                (rs, rowNum) ->
                        new Seat(
                                rs.getLong("SEAT_ID"),
                                rs.getInt("ROW_NUMBER"),
                                rs.getInt("SEAT_NUMBER"),
                                rs.getString("DESCRIPTION")
                        )
        );
		log.info("SeatDAOImpl : findAndHoldSeats"+seats.size());
		String sqlInsertSeatHold = "Insert into SEATHOLD (email) values(?)";
	    log.info(sqlInsertSeatHold);

		jdbcTemplate.update(sqlInsertSeatHold, customerEmail);
	    log.info(sqlInsertSeatHold);

		String sqlSelectSeatHold = "SELECT max(SEAT_HOLD_ID) from SEATHOLD WHERE email= '"+ customerEmail+"'";
	    log.info(sqlSelectSeatHold);
		Long seatHoldId = jdbcTemplate.queryForObject(sqlSelectSeatHold,Long.class);
		
		String sqlUpdateSeats = "UPDATE SEAT SET STATUS=1, LAST_HELD_TIME = CURRENT_TIMESTAMP(),SEAT_HOLD_ID=? "
				+ "WHERE SEAT_ID=?";
		for (Seat seat : seats) {
			this.jdbcTemplate.update(sqlUpdateSeats, seatHoldId,
					seat.getSeatId());
		}
	    log.info(customerEmail);

		return new SeatHold(seatHoldId, customerEmail, seats);
}
	

	@Override
	@Transactional
	public int reserveSeats(int seatHoldId, String customerEmail) {
	    log.info(customerEmail);

		String sql = "UPDATE SEAT SET STATUS = 2 WHERE ((DATEDIFF(second,LAST_HELD_TIME,CURRENT_TIMESTAMP()) < 900 and STATUS = 1)) AND SEAT_HOLD_ID = ?";
		int rows = jdbcTemplate.update(sql, seatHoldId);
		if (rows<=0) seatHoldId=-1;
		return seatHoldId;
	}

	
}
