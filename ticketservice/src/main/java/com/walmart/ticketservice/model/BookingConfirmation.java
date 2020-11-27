/**
 * 
 */
package com.walmart.ticketservice.model;

/**
 * @author sgokavar
 *
 */
public class BookingConfirmation {
	
	private String message;
	private String customerEmail;
	private Integer confirmationNumber;
	
	public BookingConfirmation(String message,  Integer confirmationNumber,String customerEmail) {
		this.message = message;
		this.customerEmail = customerEmail;
		this.confirmationNumber = confirmationNumber;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public Integer getConfirmationNumber() {
		return confirmationNumber;
	}
	public void setConfirmationNumber(Integer confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}
	@Override
	public String toString() {
		return "BookingConfirmation [message=" + message + ", customerEmail=" + customerEmail + ", confirmationNumber="
				+ confirmationNumber + "]";
	}
	
	

}
