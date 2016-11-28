package com.search.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.search.entity.Flight;

public interface FlightRepository extends CrudRepository<Flight, Long> {
	List<Flight> findByOriginAndDestinationAndFlightDate(String origin,String destination, String flightDate);

	Flight findByFlightNumberAndFlightDate(String flightNumber, String flightDate);
} 