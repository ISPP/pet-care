package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SupplierRepository;
import security.LoginService;
import security.UserAccount;
import domain.Supplier;

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

}
