package com.book;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.book.component.BookingComponent;
import com.book.entity.Inventory;
import com.book.repository.BookingRepository;
import com.book.repository.InventoryRepository;

@SpringBootApplication
@EnableEurekaClient
public class Application implements CommandLineRunner{
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private BookingComponent bookingComponent;
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		
//		Inventory[] invs = { 
//					new Inventory("F100", "22-SEP-16", 100),
//					new Inventory("F101", "22-SEP-16", 100),
//					new Inventory("F102", "22-SEP-16", 100),
//					new Inventory("F103", "22-SEP-16", 100),
//					new Inventory("F104", "22-SEP-16", 100),
//					new Inventory("F105", "22-SEP-16", 100),
//					new Inventory("F106", "22-SEP-16", 100)};
//		Arrays.asList(invs).forEach(inventory -> inventoryRepository.save(inventory));
	
	}
	
}
