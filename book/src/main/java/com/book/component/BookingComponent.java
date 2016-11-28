package com.book.component;

import java.util.Date;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.book.entity.BookingRecord;
import com.book.entity.Inventory;
import com.book.entity.Passenger;
import com.book.repository.BookingRepository;
import com.book.repository.InventoryRepository;

@Component
public class BookingComponent {
	private static final Logger logger = LoggerFactory
			.getLogger(BookingComponent.class);

	BookingRepository bookingRepository;
	InventoryRepository inventoryRepository;

	@Autowired
	public BookingComponent(BookingRepository bookingRepository,
			InventoryRepository inventoryRepository) {
		this.bookingRepository = bookingRepository;
		this.inventoryRepository = inventoryRepository;
	}

	public BookingRecord book(BookingRecord record) {

		logger.info("calling inventory to get inventory");
		// check inventory
		Inventory inventory = inventoryRepository
				.findByFlightNumberAndFlightDate(record.getFlightNumber(),
						record.getFlightDate());
		if (!inventory.isAvailable(record.getPassengers().size())) {
			throw new BookingException("No more seats avaialble");
		}
		logger.info("successfully checked inventory" + inventory);
		logger.info("calling inventory to update inventory");
		// update inventory
		inventory.setAvailable(inventory.getAvailable()
				- record.getPassengers().size());
		inventoryRepository.save(inventory);
		logger.info("sucessfully updated inventory");
		// save booking
		record.setStatus(BookingStatus.BOOKING_CONFIRMED);
		Set<Passenger> passengers = record.getPassengers();
		passengers.forEach(passenger -> passenger.setBookingRecord(record));
		record.setBookingDate(new Date());
		BookingRecord bookingRecord = bookingRepository.save(record);
		logger.info("Successfully saved booking:"+bookingRecord.getId());
		return bookingRecord;
	}

	public BookingRecord getBooking(long id) {
		return bookingRepository.findOne(id);
	}

	public void updateStatus(String status, long bookingId) {
		BookingRecord record = bookingRepository.findOne(bookingId);
		record.setStatus(status);
	}

}
