package com.search;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.search.entity.Flight;
import com.search.repository.FlightRepository;

@SpringBootApplication
@EnableEurekaClient
public class Application implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	@Autowired
	private FlightRepository flightRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Override
	public void run(String... strings) throws Exception {
//		List<Flight> flights = new ArrayList<>();
//		flights.add(new Flight("F100", "DLH","BNG","22-SEP-16","5000", 100));
//		flights.add(new Flight("F101", "CHN","MUM","22-SEP-16","6000", 100));
//		flights.add(new Flight("F105", "MUM","BNG","22-SEP-16","4000", 100));
//		flights.add(new Flight("F106", "DLH","BNG","22-SEP-16","3000", 100));
//		flights.add(new Flight("F102", "CHN","MUM","22-SEP-16","5500", 100));
//		flights.add(new Flight("F103", "MUM","DLH","22-SEP-16","4500", 100));
//		flights.add(new Flight("F104", "KOL","DLH","22-SEP-16","6500", 100));
//	    
//		flightRepository.save(flights);
//		
//		logger.info("Looking to load flights...");
//		for (Flight flight : flightRepository.findByOriginAndDestinationAndFlightDate("DLH", "BNG", "22-SEP-16")) {
//	        logger.info(flight.toString());
//	    }
	}
	 
}
