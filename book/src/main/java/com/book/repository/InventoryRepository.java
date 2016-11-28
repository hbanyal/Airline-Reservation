package com.book.repository;


import org.springframework.data.repository.CrudRepository;

import com.book.entity.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {

	Inventory findByFlightNumberAndFlightDate(String flightNumber, String flightDate);
	
}
