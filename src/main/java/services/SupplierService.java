package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Place;
import domain.Supplier;

import repositories.SupplierRepository;

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

}
