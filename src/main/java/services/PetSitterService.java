package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Booking;
import domain.Comment;
import domain.Complaint;
import domain.CreditCard;
import domain.Message;
import domain.MessageFolder;
import domain.Pet;
import domain.PetSitter;
import domain.PetShipper;
import domain.PetSitter;
import domain.Review;
import forms.PetSitterForm;

import repositories.MessageFolderRepository;
import repositories.PetSitterRepository;
import security.Authority;
import security.UserAccount;



@Service
@Transactional
public class PetSitterService {

	public PetSitterService(){
		super();
	}
	
	@Autowired
	private PetSitterRepository petSitterRepository;
	
	@Autowired
	private MessageFolderService folderService;
	
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
		authority.setAuthority(Authority.PETOWNER);
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
		result.setAddress(petSitterForm.getAddress());
		result.setDescription(petSitterForm.getDescription());
		result.setUser(userAccount);

		return result;
	}


}
