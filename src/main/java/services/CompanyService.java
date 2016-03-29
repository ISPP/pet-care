package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.CompanyRepository;
import domain.Company;



@Service
@Transactional
public class CompanyService {

	public CompanyService(){
		super();
	}
	
	@Autowired
	private CompanyRepository companyRepository;
	
	
	public Company create() {
		Company result;
		result = new Company();
		return result;
	}

	public Company save(Company company) {
		Company result;
		result = companyRepository.saveAndFlush(company);
		return result;
	}

	public void delete(Company company) {
		companyRepository.delete(company);
	}

	public Collection<Company> findAll() {
		Collection<Company> result;
		result = companyRepository.findAll();
		return result;
	}

	public Company findOne(Integer id) {
		Company result;
		result = companyRepository.findOne(id);
		return result;
	}

}
