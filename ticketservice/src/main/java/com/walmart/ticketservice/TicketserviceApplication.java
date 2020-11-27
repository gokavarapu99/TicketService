package com.walmart.ticketservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.walmart.ticketservice.dao.SeatDAOImpl;

@SpringBootApplication
public class TicketserviceApplication {

	private static final Logger log = LoggerFactory.getLogger(TicketserviceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TicketserviceApplication.class, args);
		log.info("Welcome to Walmart Ticketing System");
	}

}
