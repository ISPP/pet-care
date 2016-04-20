package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.CreditCard;
import domain.PetShipper;
import domain.Review;
import forms.PetShipperForm;
import repositories.PetShipperRepository;
import security.LoginService;
import security.UserAccount;


@Service
@Transactional
public class PetShipperService {
	public PetShipperService(){
		super();
	}
	
	@Autowired
	private PetShipperRepository petShipperRepository;
	

	public PetShipper create() {
		PetShipper result;
		result = new PetShipper();
		return result;
	}

	public PetShipper save(PetShipper petShipper) {
		PetShipper result;
		result = petShipperRepository.saveAndFlush(petShipper);
		return result;
	}

	public void delete(PetShipper petShipper) {
		petShipperRepository.delete(petShipper);
	}

	public Collection<PetShipper> findAll() {
		Collection<PetShipper> result;
		result = petShipperRepository.findAll();
		return result;
	}

	public PetShipper findOne(Integer id) {
		PetShipper result;
		result = petShipperRepository.findOne(id);
		return result;
	}
	public PetShipper findOneByPrincipal() {
		PetShipper result;
		
		result = petShipperRepository.findOneByPrincipal(LoginService.getPrincipal().getId());
		
		return result;
	}
	
	public void saveEdited(PetShipper petShipper) {
		Assert.notNull(petShipper);
		
		petShipperRepository.saveAndFlush(petShipper);
	}
	
	public PetShipper reconstructEdited(PetShipperForm petShipperForm) {
		Assert.isTrue(petShipperForm.getPassword().equals(
				petShipperForm.getPasswordConfirm()));
		Assert.isTrue(petShipperForm.getId() > 0);
		
		PetShipper result;
		CreditCard creditCard;
		UserAccount userAccount;
		BCryptPasswordEncoder encoder;
		
		result = findOne(petShipperForm.getId()); 
		
		creditCard = result.getCreditCard();
		userAccount = result.getUser();

		// CreditCard
		creditCard.setBrandName(petShipperForm.getBrandName());
		creditCard.setCVV(petShipperForm.getCvvCode());
		creditCard.setExpirationMonth(petShipperForm.getExpirationMonth());
		creditCard.setExpirationYear(petShipperForm.getExpirationYear());
		creditCard.setHolderName(petShipperForm.getHolderName());
		creditCard.setNumber(petShipperForm.getNumber());

		// UserAccount
		userAccount.setUsername(petShipperForm.getUsername());
		encoder = new BCryptPasswordEncoder();
		userAccount.setPassword(
				encoder.encode(petShipperForm.getPassword()));

		result.setCreditCard(creditCard);
		result.setEmail(petShipperForm.getEmail());
		result.setName(petShipperForm.getName());
		result.setHomePage(petShipperForm.getHomePage());
		result.setContactPhone(petShipperForm.getContactPhone());
		result.setSurname(petShipperForm.getSurname());
		result.setAddress(petShipperForm.getAddress());
		result.setDescription(petShipperForm.getDescription());
		result.setDaysBeforeCancel(petShipperForm.getDaysBeforeCancel());
		result.setUser(userAccount);

		return result;
	}
	
	public PetShipperForm fragment(PetShipper petShipper) {
		Assert.notNull(petShipper);
		
		PetShipperForm result;

		result = new PetShipperForm();
		
		// CreditCard
		result.setBrandName(petShipper.getCreditCard().getBrandName());
		result.setCvvCode(petShipper.getCreditCard().getCVV());
		result.setExpirationMonth(petShipper.getCreditCard().getExpirationMonth());
		result.setExpirationYear(petShipper.getCreditCard().getExpirationYear());
		result.setHolderName(petShipper.getCreditCard().getHolderName());
		result.setNumber(petShipper.getCreditCard().getNumber());

		// UserAccount
		result.setUsername(petShipper.getUser().getUsername());
		result.setPassword(petShipper.getUser().getPassword());
		result.setPasswordConfirm(petShipper.getUser().getPassword());

		result.setEmail(petShipper.getEmail());
		result.setName(petShipper.getName());
		result.setHomePage(petShipper.getHomePage());
		result.setContactPhone(petShipper.getContactPhone());
		result.setSurname(petShipper.getSurname());
		result.setAddress(petShipper.getAddress());
		result.setDaysBeforeCancel(petShipper.getDaysBeforeCancel());
		result.setId(petShipper.getId());

		return result;
	}

	public Collection<Review> findReviews(int petShipperId) {
		Collection<Review> result;
		
		result = petShipperRepository.findOne(petShipperId).getReviews();
		
		return result;
	}
	
	public boolean isPrincipal(int petShipperId) {
		boolean result;
		
		result = findOne(petShipperId).getUser().equals(LoginService.getPrincipal());
				
		return result;
	}

}
