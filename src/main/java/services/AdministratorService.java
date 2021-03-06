package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import forms.AdministratorForm;

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
	
	public void saveEdited(Administrator administrator) {
		Assert.notNull(administrator);
		
		administratorRepository.saveAndFlush(administrator);
	}
	
	public Administrator reconstructEdited(AdministratorForm administratorForm) {
		Assert.isTrue(administratorForm.getPassword().equals(
				administratorForm.getPasswordConfirm()));
		Assert.isTrue(administratorForm.getId() > 0);
		
		Administrator result;
		UserAccount userAccount;
		BCryptPasswordEncoder encoder;
		
		result = findOne(administratorForm.getId()); 
		
		userAccount = result.getUser();

		// UserAccount
		userAccount.setUsername(administratorForm.getUsername());
		encoder = new BCryptPasswordEncoder();
		userAccount.setPassword(
				encoder.encode(administratorForm.getPassword()));

		result.setEmail(administratorForm.getEmail());
		result.setName(administratorForm.getName());
		result.setSurname(administratorForm.getSurname());
		result.setUser(userAccount);

		return result;
	}
	
	public AdministratorForm fragment(Administrator administrator) {
		Assert.notNull(administrator);
		
		AdministratorForm result;

		result = new AdministratorForm();
		
		// UserAccount
		result.setUsername(administrator.getUser().getUsername());
		result.setPassword(administrator.getUser().getPassword());
		result.setPasswordConfirm(administrator.getUser().getPassword());

		result.setEmail(administrator.getEmail());
		result.setName(administrator.getName());
		result.setSurname(administrator.getSurname());
		result.setId(administrator.getId());

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
	
	public Administrator findAdministratorByUsername(String username) {
		Administrator administrator;
		administrator = administratorRepository.findAdminByUsername(username);
		Assert.notNull(administrator, "El usuario no puede ser nulo");
		
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

	public Administrator findOneByPrincipal() {
		Administrator result;
		
		result = administratorRepository.findOneByPrincipal(LoginService.getPrincipal().getId());
		
		return result;
	}

	public boolean isPrincipal(int administratorId) {
		boolean result;
		
		result = findOne(administratorId).getUser().equals(LoginService.getPrincipal());
				
		return result;
	}

}
