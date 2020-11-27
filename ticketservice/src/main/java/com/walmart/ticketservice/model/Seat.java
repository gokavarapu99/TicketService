/**
 * 
 */
package com.walmart.ticketservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author sgokavar
 *
 */



public class Seat {
	

	private Long seatId;

	private Integer rowNumber;

	private Integer seatNumber;

	private String description;
	

	public Seat(Long seatId, Integer rowNumber, Integer seatNumber, String description) {
		super();
		this.seatId = seatId;
		this.rowNumber = rowNumber;
		this.seatNumber = seatNumber;
		this.description = description;
	}
	public Long getSeatId() {
		return seatId;
	}
	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}
	public Integer getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}
	public Integer getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
