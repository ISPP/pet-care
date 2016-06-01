package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Booking;
import domain.Comment;
import domain.Complaint;
import domain.CreditCard;
import domain.Customer;
import domain.MessageFolder;
import domain.Pet;
import domain.PetShipper;
import domain.PetSitter;
import domain.Review;
import forms.PetShipperForm;
import forms.PetSitterForm;
import repositories.PetShipperRepository;
import security.Authority;
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
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SupplierService supplierService;
	


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
	
	public Customer findPetShipperByInvitationCode(String invitationCode) {
		
		return petShipperRepository.findPetShipperByInvitationCode(invitationCode);
	}
	
	public PetShipper register(PetShipper petShipper,String codeToRegister) {
		PetShipper result;
		String invitationCode;
		invitationCode=RandomStringUtils.randomAlphanumeric(20);

		Assert.notNull(supplierService.findSupplierByInvitationCode(codeToRegister));
		petShipper.setInvitationCode(invitationCode);
		petShipper.setDaysBeforeCancel(1);
		result=save(petShipper);
		return result;
	}
	

	public PetShipper create() {
		PetShipper result;
		
		Collection<MessageFolder> messageFolders;
	
		Collection<Review> reviews;
		Collection<Booking> bookings;
		Collection<Comment> comments;
		Collection<Complaint> complaints;
		
		result = new PetShipper();
		
		messageFolders = new ArrayList<MessageFolder>();
	
		bookings = new ArrayList<Booking>();
		comments= new ArrayList<Comment>();
		complaints = new ArrayList<Complaint>();
		reviews = new ArrayList<Review>();
		
		//We set all the initial values to the petSitter
		result.setMessageFolders(messageFolders);
	
		result.setComments(comments);
		result.setComplaints(complaints);
		result.setBookings(bookings);
		result.setReviews(reviews);
		
		return result;
	}
	
	
	
	
	public PetShipper reconstruct(PetShipperForm petShipperForm) {
		Assert.isTrue(petShipperForm.getPassword().equals(
				petShipperForm.getPasswordConfirm()));

		PetShipper result;
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
		authority.setAuthority(Authority.PETSHIPPER);
		authorities.add(authority);

		// CreditCard
		creditCard.setBrandName(petShipperForm.getBrandName());
		creditCard.setCVV(petShipperForm.getCvvCode());
		creditCard.setExpirationMonth(petShipperForm.getExpirationMonth());
		creditCard.setExpirationYear(petShipperForm.getExpirationYear());
		creditCard.setHolderName(petShipperForm.getHolderName());
		creditCard.setNumber(petShipperForm.getNumber());

		// UserAccount
		userAccount.setAuthorities(authorities);
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
		result.setUser(userAccount);
		result.setAddress(petShipperForm.getAddress());
		result.setDescription(petShipperForm.getDescription());

		return result;
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
