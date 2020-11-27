/**
 * 
 */
package com.walmart.ticketservice.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author sgokavar
 *
 */

public class SeatHold {


	private Long seatHoldId;

	private String email;

	private List<Seat> seat;
	
	
	public SeatHold(Long seatHoldId, String email, List<Seat> seat) {
		this.seatHoldId = seatHoldId;
		this.email = email;
		this.seat = seat;
	}
	public SeatHold() {
		// TODO Auto-generated constructor stub
	}
	public Long getSeatHoldId() {
		return seatHoldId;
	}
	public void setSeatHoldId(Long seatHoldId) {
		this.seatHoldId = seatHoldId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Seat> getSeat() {
		return seat;
	}
	public void setSeat(List<Seat> seat) {
		this.seat = seat;
	}
	@Override
	public String toString() {
		return "SeatHold [seatHoldId=" + seatHoldId + ", email=" + email + ", seat=" + seat + "]";
	}
	
	
	
}
