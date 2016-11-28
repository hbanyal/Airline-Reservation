package com.book.repository;


import org.springframework.data.repository.CrudRepository;

import com.book.entity.BookingRecord;

public interface BookingRepository extends CrudRepository<BookingRecord, Long> {
	
}
