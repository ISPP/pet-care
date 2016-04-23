package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PetSitterRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Booking;
import domain.Comment;
import domain.Complaint;
import domain.CreditCard;
import domain.Customer;
import domain.Message;
import domain.MessageFolder;
import domain.Pet;
import domain.PetSitter;
import domain.Review;
import forms.PetSitterForm;



@Service
@Transactional
public class PetSitterService {

	public PetSitterService(){
		super();
	}
	
	//Managed repository ----------------------------------------------
	
	@Autowired
	private PetSitterRepository petSitterRepository;
	
	//Supporting services ----------------------------------------------
	
	// CRUD methods

	@Autowired
	private MessageFolderService messageFolderService;
	
	@Autowired
	private CustomerService customerService;

	@Autowired
	private BookingService bookingService;

	
	public PetSitter create() {
		PetSitter result;
		
		Collection<MessageFolder> messageFolders;
		Collection<Pet> pets;
		Collection<Review> reviews;
		Collection<Booking> bookings;
		Collection<Comment> comments;
		Collection<Complaint> complaints;
		
		result = new PetSitter();
		
		messageFolders = new ArrayList<MessageFolder>();
		pets = new ArrayList<Pet>();
		bookings = new ArrayList<Booking>();
		comments= new ArrayList<Comment>();
		complaints = new ArrayList<Complaint>();
		reviews = new ArrayList<Review>();
		
		//We set all the initial values to the petSitter
		result.setMessageFolders(messageFolders);
		result.setPets(pets);
		result.setComments(comments);
		result.setComplaints(complaints);
		result.setBookings(bookings);
		result.setReviews(reviews);
		
		return result;
	}
	public PetSitter findPetSitterByInvitationCode(String invitationCode) {
		
		return petSitterRepository.findPetSitterByInvitationCode(invitationCode);
	}
	
	public PetSitter register(PetSitter petSitter,String codeToRegister) {
		PetSitter result;
		String invitationCode;
		invitationCode=RandomStringUtils.randomAlphanumeric(10);

		Assert.notNull(findPetSitterByInvitationCode(codeToRegister));
		petSitter.setInvitationCode(invitationCode);
		petSitter.setDaysBeforeCancel(1);
		result=save(petSitter);
		return result;
	}
	
	
	public PetSitter save(PetSitter petSitter) {
		Assert.notNull(petSitter);
		PetSitter result;
		MessageFolder inbox;
		MessageFolder outbox;
		MessageFolder trash;
		
		
		inbox = messageFolderService.create();
		outbox = messageFolderService.create();
		trash= messageFolderService.create();
		
		inbox.setMessages(new ArrayList<Message>());
		inbox.setName("Inbox");
		inbox.setOwner(petSitter);
		
		outbox.setMessages(new ArrayList<Message>());
		outbox.setName("Outbox");
		outbox.setOwner(petSitter);
		
		trash.setMessages(new ArrayList<Message>());
		trash.setName("Trashbox");
		trash.setOwner(petSitter);
		
		petSitter.getMessageFolders().add(inbox);
		petSitter.getMessageFolders().add(outbox);
		petSitter.getMessageFolders().add(trash);
		
		result = petSitterRepository.saveAndFlush(petSitter);
		
		//We set the new petSitter to the folders
		inbox.setOwner(result);
		outbox.setOwner(result);
		trash.setOwner(result);
		
		//We have to save the folders after the petSitter
		messageFolderService.save(inbox);
		messageFolderService.save(outbox);
		messageFolderService.save(trash);
		
		return result;
	}

	public void delete(PetSitter petSitter) {
		petSitterRepository.delete(petSitter);
	}

	public Collection<PetSitter> findAll() {
		Collection<PetSitter> result;
		result = petSitterRepository.findAll();
		return result;
	}

	public PetSitter findOne(Integer id) {
		PetSitter result;
		result = petSitterRepository.findOne(id);
		return result;
	}
	
	public void saveEdited(PetSitter petSitter) {
		Assert.notNull(petSitter);
		
		petSitterRepository.saveAndFlush(petSitter);
	}
	
	public PetSitter reconstruct(PetSitterForm petSitterForm) {
		Assert.isTrue(petSitterForm.getPassword().equals(
				petSitterForm.getPasswordConfirm()));

		PetSitter result;
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
		authority.setAuthority(Authority.PETSITTER);
		authorities.add(authority);

		// CreditCard
		creditCard.setBrandName(petSitterForm.getBrandName());
		creditCard.setCVV(petSitterForm.getCvvCode());
		creditCard.setExpirationMonth(petSitterForm.getExpirationMonth());
		creditCard.setExpirationYear(petSitterForm.getExpirationYear());
		creditCard.setHolderName(petSitterForm.getHolderName());
		creditCard.setNumber(petSitterForm.getNumber());

		// UserAccount
		userAccount.setAuthorities(authorities);
		userAccount.setUsername(petSitterForm.getUsername());
		encoder = new BCryptPasswordEncoder();
		userAccount.setPassword(
				encoder.encode(petSitterForm.getPassword()));

		result.setCreditCard(creditCard);
		result.setEmail(petSitterForm.getEmail());
		result.setName(petSitterForm.getName());
		result.setHomePage(petSitterForm.getHomePage());
		result.setContactPhone(petSitterForm.getContactPhone());
		result.setSurname(petSitterForm.getSurname());
		result.setUser(userAccount);
		result.setAddress(petSitterForm.getAddress());
		result.setDescription(petSitterForm.getDescription());

		return result;
	}
	
	public PetSitter reconstructEdited(PetSitterForm petSitterForm) {
		Assert.isTrue(petSitterForm.getPassword().equals(
				petSitterForm.getPasswordConfirm()));
		Assert.isTrue(petSitterForm.getId() > 0);
		
		PetSitter result;
		CreditCard creditCard;
		UserAccount userAccount;
		BCryptPasswordEncoder encoder;
		
		result = findOne(petSitterForm.getId()); 
		
		creditCard = result.getCreditCard();
		userAccount = result.getUser();

		// CreditCard
		creditCard.setBrandName(petSitterForm.getBrandName());
		creditCard.setCVV(petSitterForm.getCvvCode());
		creditCard.setExpirationMonth(petSitterForm.getExpirationMonth());
		creditCard.setExpirationYear(petSitterForm.getExpirationYear());
		creditCard.setHolderName(petSitterForm.getHolderName());
		creditCard.setNumber(petSitterForm.getNumber());

		// UserAccount
		userAccount.setUsername(petSitterForm.getUsername());
		encoder = new BCryptPasswordEncoder();
		userAccount.setPassword(
				encoder.encode(petSitterForm.getPassword()));

		result.setCreditCard(creditCard);
		result.setEmail(petSitterForm.getEmail());
		result.setName(petSitterForm.getName());
		result.setHomePage(petSitterForm.getHomePage());
		result.setContactPhone(petSitterForm.getContactPhone());
		result.setSurname(petSitterForm.getSurname());
		result.setAddress(petSitterForm.getAddress());
		result.setDescription(petSitterForm.getDescription());
		result.setPriceHour(petSitterForm.getPriceHour());
		result.setPriceNight(petSitterForm.getPriceNight());
		result.setDaysBeforeCancel(petSitterForm.getDaysBeforeCancel());
		result.setUser(userAccount);

		return result;
	}
	
	public PetSitterForm fragment(PetSitter petSitter) {
		Assert.notNull(petSitter);
		
		PetSitterForm result;

		result = new PetSitterForm();
		
		// CreditCard
		result.setBrandName(petSitter.getCreditCard().getBrandName());
		result.setCvvCode(petSitter.getCreditCard().getCVV());
		result.setExpirationMonth(petSitter.getCreditCard().getExpirationMonth());
		result.setExpirationYear(petSitter.getCreditCard().getExpirationYear());
		result.setHolderName(petSitter.getCreditCard().getHolderName());
		result.setNumber(petSitter.getCreditCard().getNumber());

		// UserAccount
		result.setUsername(petSitter.getUser().getUsername());
		result.setPassword(petSitter.getUser().getPassword());
		result.setPasswordConfirm(petSitter.getUser().getPassword());

		result.setEmail(petSitter.getEmail());
		result.setName(petSitter.getName());
		result.setHomePage(petSitter.getHomePage());
		result.setContactPhone(petSitter.getContactPhone());
		result.setSurname(petSitter.getSurname());
		result.setAddress(petSitter.getAddress());
		result.setPriceHour(petSitter.getPriceHour());
		result.setPriceNight(petSitter.getPriceNight());
		result.setDaysBeforeCancel(petSitter.getDaysBeforeCancel());
		result.setId(petSitter.getId());

		return result;
	}

	public List<PetSitter> searchSitters(Date startDate, Date endDate, String address){
		List<PetSitter> result;
		List<PetSitter> sitters;
		List<Booking> bookings;
		Date current;
		
		current = new Date();
		Assert.isTrue(current.before(startDate));
		Assert.isTrue(startDate.before(endDate) || startDate.equals(endDate));
		
		result = new ArrayList<PetSitter>();
		sitters = new ArrayList<PetSitter>(petSitterRepository.searchSitters(address));
		
		for(PetSitter i: sitters){
			bookings = bookingService.findByDateSupplier(startDate, endDate, i.getId());
			if(bookings.isEmpty()){
				result.add(i);
			}
		}
		
		return result;
	}

	public Collection<Review> findReviews(int petSitterId) {
		Collection<Review> result;
		
		result = petSitterRepository.findOne(petSitterId).getReviews();
		
		return result;
	}
	
	public void block(PetSitter petSitter) {
		Assert.notNull(petSitter);
		petSitter.setBlocked(true);
		save(petSitter);
	}
	
	
	public PetSitter findPetSitterByUsername(){
		PetSitter result;
		UserAccount user;
		user=LoginService.getPrincipal();
		Assert.notNull(user, "El usuario no puede ser nulo");
		result = petSitterRepository.findPetSitterByUsername(user.getUsername());
		return result;
	}
	
	public PetSitter findOneByPrincipal() {
		PetSitter result;
		
		result = petSitterRepository.findOneByPrincipal(LoginService.getPrincipal().getId());
		
		return result;
	}

	public boolean isPrincipal(int petSitterId) {
		boolean result;
		
		result = findOne(petSitterId).getUser().equals(LoginService.getPrincipal());
				
		return result;
	}

}
