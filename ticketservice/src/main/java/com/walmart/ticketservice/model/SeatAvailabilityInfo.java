/**
 * 
 */
package com.walmart.ticketservice.model;

/**
 * @author sgokavar
 *
 */
public class SeatAvailabilityInfo {
	private Integer noOfSeats;
	private String message;

	public SeatAvailabilityInfo(Integer noOfSeats, String message) {
		
		this.noOfSeats = noOfSeats;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(Integer noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	@Override
	public String toString() {
		return "SeatAvailabilityInfo [noOfSeats=" + noOfSeats + ", message=" + message + "]";
	}
	

}
