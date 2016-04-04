package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.CreditCard;
import domain.PetOwner;
import domain.Review;
import repositories.PetOwnerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class PetOwnerService {
	public PetOwnerService(){
		super();
	}
	
	@Autowired
	private PetOwnerRepository petOwnerRepository;
	
	

	public PetOwner create() {
		PetOwner result;
		result = new PetOwner();
		return result;
	}

	public PetOwner save(PetOwner petOwner) {
		PetOwner result;
		result = petOwnerRepository.saveAndFlush(petOwner);
		return result;
	}
	

	public PetOwner reconstruct(PetOwnerForm petOwnerForm) {
		Assert.isTrue(petOwnerForm.getPassword().equals(
				petOwnerForm.getPasswordConfirm()));

		PetOwner result;
		CreditCard creditCard;
		UserAccount userAccount;
		Collection<Authority> authorities;
		Authority authority;
		BCryptPasswordEncoder encoder;
		

		result = create();
		creditCard = new CreditCard();
		userAccount = new UserAccount();
		authorities = new HashSet<Authority>();
		authority = new Authority();

		
		// authority and authorities
		authority.setAuthority(Authority.PETOWNER);
		authorities.add(authority);

		// CreditCard
		creditCard.setBrandName(petOwnerForm.getBrandName());
		creditCard.setCVV(petOwnerForm.getCvvCode());
		creditCard.setExpirationMonth(petOwnerForm.getExpirationMonth());
		creditCard.setExpirationYear(petOwnerForm.getExpirationYear());
		creditCard.setHolderName(petOwnerForm.getHolderName());
		creditCard.setNumber(petOwnerForm.getNumber());

		// UserAccount
		userAccount.setAuthorities(authorities);
		userAccount.setUsername(petOwnerForm.getUsername());
		encoder = new BCryptPasswordEncoder();
		userAccount.setPassword(
				encoder.encode(petOwnerForm.getPassword()));

		result.setCreditCard(creditCard);
		result.setEmail(petOwnerForm.getEmail());
		result.setName(petOwnerForm.getName());
		result.setHomePage(petOwnerForm.getHomePage());
		result.setContactPhone(petOwnerForm.getContactPhone());
		result.setSurname(petOwnerForm.getSurname());
		result.setUser(userAccount);
		result.setAddress(petOwnerForm.getAddress());
		result.setDescription(petOwnerForm.getDescription());
		result.setAddress(petOwnerForm.getAddress());
		result.setDescription(petOwnerForm.getDescription());
		result.setUser(userAccount);

		return result;
	}

	public void delete(PetOwner petOwner) {
		petOwnerRepository.delete(petOwner);
	}

	public Collection<PetOwner> findAll() {
		Collection<PetOwner> result;
		result = petOwnerRepository.findAll();
		return result;
	}

	public PetOwner findOne(Integer id) {
		PetOwner result;
		result = petOwnerRepository.findOne(id);
		return result;
	}

	public PetOwner findOneByPrincipal() {
		PetOwner result;
		
		result = petOwnerRepository.findOneByPrincipal(LoginService.getPrincipal().getId());
		
		return result;
	}

	public Collection<Review> findReviews(int petOwnerId) {
		Collection<Review> result;
		
		result = petOwnerRepository.findOne(petOwnerId).getReviews();
		
		return result;
	}

	public boolean isPrincipal(int petOwnerId) {
		boolean result;
		
		result = findOne(petOwnerId).getUser().equals(LoginService.getPrincipal());
				
		return result;
	}



}
