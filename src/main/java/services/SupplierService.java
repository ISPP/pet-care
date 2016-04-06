package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Supplier;

import repositories.SupplierRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class SupplierService {

	public SupplierService(){
		super();
	}
	
	
	@Autowired
	private SupplierRepository supplierRepository;
	
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
}
