package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository administratorRepository;

	public AdministratorService() {
		super();
	}

	public Administrator create() {
		Administrator result;
		result = new Administrator();
		return result;
	}

	public Administrator save(Administrator administrator) {
		Administrator result;
		result = administratorRepository.saveAndFlush(administrator);
		return result;
	}

	public void delete(Administrator administrator) {
		administratorRepository.delete(administrator);
	}

	public Collection<Administrator> findAll() {
		Collection<Administrator> result;
		result = administratorRepository.findAll();
		return result;
	}

	public Administrator findOne(Integer id) {
		Administrator result;
		result = administratorRepository.findOne(id);
		return result;
	}

	// --------------------------
	public Administrator findAdminByUsername(String username) {
		Administrator administrator;
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.notNull(user, "El usuario no puede ser nulo");
		administrator = administratorRepository.findAdminByUsername(user.getUsername());
		return administrator;
	}

	public Administrator getLoggedAdmin() {
		Administrator result;
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.notNull(user, "El usuario no puede ser nulo");
		result = administratorRepository.findAdminByUsername(user.getUsername());
		return result;
	}

	public void flush() {
		administratorRepository.flush();
	}


}
