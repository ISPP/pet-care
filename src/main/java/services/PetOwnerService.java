package services;

import java.util.ArrayList;
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
import forms.PetOwnerForm;
import repositories.PetOwnerRepository;
import security.Authority;
import security.LoginService;

import security.UserAccount;
import domain.Booking;
import domain.Comment;
import domain.Complaint;
import domain.Message;
import domain.MessageFolder;
import domain.Pet;
import domain.Supplier;


@Service
@Transactional
public class PetOwnerService {
	public PetOwnerService(){
		super();
	}
	
	@Autowired
	private PetOwnerRepository petOwnerRepository;
	
	@Autowired
	private MessageFolderService messageFolderService;

	
	public PetOwner create() {
		PetOwner result;
		
		Collection<MessageFolder> messageFolders;
		Collection<Pet> pets;
		Collection<Review> reviews;
		Collection<Booking> bookings;
		Collection<Comment> comments;
		Collection<Complaint> complaints;
		
		result = new PetOwner();
		
		messageFolders = new ArrayList<MessageFolder>();
		pets = new ArrayList<Pet>();
		bookings = new ArrayList<Booking>();
		comments= new ArrayList<Comment>();
		complaints = new ArrayList<Complaint>();
		reviews = new ArrayList<Review>();
		
		//We set all the initial values to the petOwner
		result.setMessageFolders(messageFolders);
		result.setPets(pets);
		result.setComments(comments);
		result.setComplaints(complaints);
		result.setBookings(bookings);
		result.setReviews(reviews);
		
		return result;
	}

	public PetOwner save(PetOwner petOwner) {
		Assert.notNull(petOwner);
		PetOwner result;
		MessageFolder inbox;
		MessageFolder outbox;
		MessageFolder trash;
		
		inbox = messageFolderService.create();
		outbox = messageFolderService.create();
		trash= messageFolderService.create();
		
		inbox.setMessages(new ArrayList<Message>());
		inbox.setName("Inbox");
		inbox.setOwner(petOwner);
		
		outbox.setMessages(new ArrayList<Message>());
		outbox.setName("Outbox");
		outbox.setOwner(petOwner);
		
		trash.setMessages(new ArrayList<Message>());
		trash.setName("Trashbox");
		trash.setOwner(petOwner);
		
		petOwner.getMessageFolders().add(inbox);
		petOwner.getMessageFolders().add(outbox);
		petOwner.getMessageFolders().add(trash);
		
		result = petOwnerRepository.saveAndFlush(petOwner);
		
		//We set the new petOwner to the folders
		inbox.setOwner(result);
		outbox.setOwner(result);
		trash.setOwner(result);
		
		//We have to save the folders after the petOwner
		messageFolderService.save(inbox);
		messageFolderService.save(outbox);
		messageFolderService.save(trash);
		
		return result;
	}
	
	public void saveEdited(PetOwner petOwner) {
		Assert.notNull(petOwner);
		
		petOwnerRepository.saveAndFlush(petOwner);
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

		return result;
	}
	
	public PetOwner reconstructEdited(PetOwnerForm petOwnerForm) {
		Assert.isTrue(petOwnerForm.getPassword().equals(
				petOwnerForm.getPasswordConfirm()));
		Assert.isTrue(petOwnerForm.getId() > 0);
		
		PetOwner result;
		CreditCard creditCard;
		UserAccount userAccount;
		BCryptPasswordEncoder encoder;
		
		result = findOne(petOwnerForm.getId()); 
		
		creditCard = result.getCreditCard();
		userAccount = result.getUser();

		// CreditCard
		creditCard.setBrandName(petOwnerForm.getBrandName());
		creditCard.setCVV(petOwnerForm.getCvvCode());
		creditCard.setExpirationMonth(petOwnerForm.getExpirationMonth());
		creditCard.setExpirationYear(petOwnerForm.getExpirationYear());
		creditCard.setHolderName(petOwnerForm.getHolderName());
		creditCard.setNumber(petOwnerForm.getNumber());

		// UserAccount
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
		result.setAddress(petOwnerForm.getAddress());
		result.setDescription(petOwnerForm.getDescription());
		result.setUser(userAccount);

		return result;
	}

	public PetOwnerForm fragment(PetOwner petOwner) {
		Assert.notNull(petOwner);
		
		PetOwnerForm result;

		result = new PetOwnerForm();
		
		// CreditCard
		result.setBrandName(petOwner.getCreditCard().getBrandName());
		result.setCvvCode(petOwner.getCreditCard().getCVV());
		result.setExpirationMonth(petOwner.getCreditCard().getExpirationMonth());
		result.setExpirationYear(petOwner.getCreditCard().getExpirationYear());
		result.setHolderName(petOwner.getCreditCard().getHolderName());
		result.setNumber(petOwner.getCreditCard().getNumber());

		// UserAccount
		result.setUsername(petOwner.getUser().getUsername());
		result.setPassword(petOwner.getUser().getPassword());
		result.setPasswordConfirm(petOwner.getUser().getPassword());

		result.setEmail(petOwner.getEmail());
		result.setName(petOwner.getName());
		result.setHomePage(petOwner.getHomePage());
		result.setContactPhone(petOwner.getContactPhone());
		result.setSurname(petOwner.getSurname());
		result.setAddress(petOwner.getAddress());
		result.setDescription(petOwner.getDescription());
		result.setId(petOwner.getId());

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

	public PetOwner getLogged() {
		PetOwner result;
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.notNull(user, "El usuario no puede ser nulo");
		result = petOwnerRepository.findPetOwnerByUsername(user.getUsername());
		return result;
	}
}

