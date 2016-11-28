package com.client;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class SiteController {
	private static final Logger logger = LoggerFactory
			.getLogger(SiteController.class);

	//RestTemplate searchClient = new RestTemplate();
	//RestTemplate bookingClient = new RestTemplate();
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String greetingForm(Model model) {
		SearchQuery query = new SearchQuery("DLH", "BNG", "22-SEP-16");
		UIData uiData = new UIData();
		uiData.setSearchQuery(query);
		model.addAttribute("uidata", uiData);
		return "search";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String greetingSubmit(@ModelAttribute UIData uiData, Model model) {
//		Flight[] flights = searchClient.postForObject(
//				"http://localhost:8090/search/get", uiData.getSearchQuery(),
//				Flight[].class);
		Flight[] flights = restTemplate.postForObject(
				"http://10.0.2.15:8090/search/get", uiData.getSearchQuery(),
				Flight[].class);
		uiData.setFlights(Arrays.asList(flights));
		model.addAttribute("uidata", uiData);
		return "result";
	}

	@RequestMapping(value = "/book/{flightNumber}/{origin}/{destination}/{flightDate}/{fare}", method = RequestMethod.GET)
	public String bookQuery(@PathVariable String flightNumber,
			@PathVariable String origin, @PathVariable String destination,
			@PathVariable String flightDate, @PathVariable String fare,
			Model model) {
		UIData uiData = new UIData();
		Flight flight = new Flight(flightNumber, origin, destination,
				flightDate, fare);
		uiData.setSelectedFlight(flight);
		uiData.setPassenger(new Passenger());
		model.addAttribute("uidata", uiData);
		return "book";
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public String ConfirmBooking(@ModelAttribute UIData uiData, Model model) {
		Flight flight = uiData.getSelectedFlight();
		BookingRecord booking = new BookingRecord(flight.getFlightNumber(),
				flight.getOrigin(), flight.getDestination(),
				flight.getFlightDate(), null, flight.getFare());
		Set<Passenger> passengers = new HashSet<Passenger>();
		Passenger pax = uiData.getPassenger();
		pax.setBookingRecord(booking);
		passengers.add(uiData.getPassenger());
		booking.setPassengers(passengers);
		long bookingId = 0;
		try {
			// long bookingId =
			// bookingClient.postForObject("http://book-service/booking/create",
			// booking, long.class);
//			bookingId = bookingClient
//					.postForObject("http://localhost:8060/booking/create",
//							booking, long.class);
			BookingRecord bookingRecord = restTemplate
					.postForObject("http://airline-book/booking/create",
							booking, BookingRecord.class);
			bookingId = bookingRecord.getId();
			logger.info("Booking created " + bookingId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("BOOKING SERVICE NOT AVAILABLE...!!!");
		}
		model.addAttribute("message",
				"Your Booking is confirmed. Reference Number is " + bookingId);
		return "confirm";
	}

}