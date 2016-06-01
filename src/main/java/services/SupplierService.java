package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SupplierRepository;
import security.LoginService;
import security.UserAccount;
import domain.Booking;
import domain.Customer;
import domain.Review;
import domain.Supplier;
import forms.SearchSuppliersForm;

@Service
@Transactional
public class SupplierService {

	public SupplierService(){
		super();
	}
	
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private BookingService bookingService;
	
	public Supplier create() {
		Supplier result;
		result = new Supplier();
		return result;
	}

	public Supplier save(Supplier supplier) {
		Supplier result;
		result = supplierRepository.saveAndFlush(supplier);
		return result;
	}

	public void delete(Supplier supplier) {
		supplierRepository.delete(supplier);
	}

	public Collection<Supplier> findAll() {
		Collection<Supplier> result;
		result = supplierRepository.findAll();
		return result;
	}

	public Supplier findOne(Integer id) {
		Supplier result;
		result = supplierRepository.findOne(id);
		return result;
	}
	
	public Supplier getLoggedSupplier() {
		Supplier result;
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.notNull(user, "El usuario no puede ser nulo");
		result = supplierRepository.findSupplierByUsername(user.getUsername());
		return result;
	}

	public void blockSupplier(Supplier supplier){
		supplier.setBlocked(true);
	}
	
	public Collection<Supplier> findSupplierWithZeroBookings(){
		return supplierRepository.findSupplierWithBookings(0);
	}
	
	public Collection<Supplier> findSupplierWithMoreBookings(){
		return supplierRepository.findSupplierWithMoreBookings();
	}
	
	public Collection<Supplier> findSuppliersWithMoreThan10ReviewsWithZeroRating(){
		return supplierRepository.findSuppliersWithNumberReviewWithRating(10, 0);
	}

	public Collection<Review> findReviews(int supplierId) {
		Collection<Review> result;
		
		result = supplierRepository.findOne(supplierId).getReviews();
		
		return result;
	}

	public List<Supplier> searchSuppliers(Date startDate, Date endDate, SearchSuppliersForm searchSuppliersForm) {
		List<Supplier> result;
		List<Supplier> suppliers;
		List<Booking> bookings;
		Date current;
		
		current = new Date();
		Assert.isTrue(current.before(startDate));
		Assert.isTrue(startDate.before(endDate) || startDate.equals(endDate));
		
		result = new ArrayList<Supplier>();
		
		if(searchSuppliersForm.getType() == 1){
			suppliers = new ArrayList<Supplier>(supplierRepository.searchSitters(searchSuppliersForm.getAddress()));
		}else if(searchSuppliersForm.getType() == 2){
			suppliers = new ArrayList<Supplier>(supplierRepository.searchShippers(searchSuppliersForm.getAddress()));
		}else if(searchSuppliersForm.getType() == 3){
			suppliers = new ArrayList<Supplier>(supplierRepository.searchCompanies(searchSuppliersForm.getAddress()));
		}else{
			suppliers = new ArrayList<Supplier>();
		}
		
		for(Supplier i: suppliers){
			bookings = bookingService.findByDateSupplier(startDate, endDate, i.getId());
			if(bookings.isEmpty()){
				result.add(i);
			}
		}
		
		return result;
	}



public Supplier findSupplierByInvitationCode(String invitationCode) {
	
	return supplierRepository.findSuppByInvitationCode(invitationCode);
}
}
