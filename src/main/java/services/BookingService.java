package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Administrator;
import domain.Booking;

import repositories.BookingRepository;

@Service
@Transactional
public class BookingService {

	public BookingService(){
		super();
	}
	
	@Autowired
	private BookingRepository bookingRepository;
	
	public Booking create() {
		Booking result;
		result = new Booking();
		return result;
	}

	public Booking save(Booking booking) {
		Booking result;
		result = bookingRepository.saveAndFlush(booking);
		return result;
	}

	public void delete(Booking booking) {
		bookingRepository.delete(booking);
	}

	public Collection<Booking> findAll() {
		Collection<Booking> result;
		result = bookingRepository.findAll();
		return result;
	}

	public Booking findOne(Integer id) {
		Booking result;
		result = bookingRepository.findOne(id);
		return result;
	}
	
	
}
