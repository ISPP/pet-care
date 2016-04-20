package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CompanyRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Booking;
import domain.Comment;
import domain.Company;
import domain.Complaint;
import domain.CreditCard;
import domain.Message;
import domain.MessageFolder;
import domain.Review;
import forms.CompanyForm;



@Service
@Transactional
public class CompanyService {

	public CompanyService(){
		super();
	}
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private MessageFolderService messageFolderService;
	
	
	public Company create() {
		Company result;
		Collection<MessageFolder> messageFolders;
		Collection<Review> reviews;
		Collection<Booking> bookings;
		Collection<Comment> comments;
		Collection<Complaint> complaints;
		
		result = new Company();
		
		messageFolders = new ArrayList<MessageFolder>();
		bookings = new ArrayList<Booking>();
		comments= new ArrayList<Comment>();
		complaints = new ArrayList<Complaint>();
		reviews = new ArrayList<Review>();
		
		//We set all the initial values to the company
		result.setMessageFolders(messageFolders);
		result.setComments(comments);
		result.setComplaints(complaints);
		result.setBookings(bookings);
		result.setReviews(reviews);
		return result;
	}

	public Company save(Company company) {
		Assert.notNull(company);
		Company result;
		MessageFolder inbox;
		MessageFolder outbox;
		MessageFolder trash;
		
		
		inbox = messageFolderService.create();
		outbox = messageFolderService.create();
		trash= messageFolderService.create();
		
		inbox.setMessages(new ArrayList<Message>());
		inbox.setName("Inbox");
		inbox.setOwner(company);
		
		outbox.setMessages(new ArrayList<Message>());
		outbox.setName("Outbox");
		outbox.setOwner(company);
		
		trash.setMessages(new ArrayList<Message>());
		trash.setName("Trashbox");
		trash.setOwner(company);
		
		company.getMessageFolders().add(inbox);
		company.getMessageFolders().add(outbox);
		company.getMessageFolders().add(trash);
		
		result = companyRepository.saveAndFlush(company);
		
		//We set the new company to the folders
		inbox.setOwner(result);
		outbox.setOwner(result);
		trash.setOwner(result);
		
		//We have to save the folders after the company
		messageFolderService.save(inbox);
		messageFolderService.save(outbox);
		messageFolderService.save(trash);
		
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
	
	public Company reconstruct(CompanyForm companyForm) {
		Assert.isTrue(companyForm.getPassword().equals(
				companyForm.getPasswordConfirm()));

		Company result;
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
		authority.setAuthority(Authority.COMPANY);
		authorities.add(authority);

		// CreditCard
		creditCard.setBrandName(companyForm.getBrandName());
		creditCard.setCVV(companyForm.getCvvCode());
		creditCard.setExpirationMonth(companyForm.getExpirationMonth());
		creditCard.setExpirationYear(companyForm.getExpirationYear());
		creditCard.setHolderName(companyForm.getHolderName());
		creditCard.setNumber(companyForm.getNumber());

		// UserAccount
		userAccount.setAuthorities(authorities);
		userAccount.setUsername(companyForm.getUsername());
		encoder = new BCryptPasswordEncoder();
		userAccount.setPassword(
				encoder.encode(companyForm.getPassword()));

		result.setCreditCard(creditCard);
		result.setEmail(companyForm.getEmail());
		result.setName(companyForm.getName());
		result.setHomePage(companyForm.getHomePage());
		result.setContactPhone(companyForm.getContactPhone());
		result.setSurname(companyForm.getSurname());
		result.setUser(userAccount);
		result.setAddress(companyForm.getAddress());
		result.setDescription(companyForm.getDescription());
		result.setPricePerDay(companyForm.getPricePerDay());
		result.setTic(companyForm.getTic());

		return result;
	}
	
	public void saveEdited(Company company) {
		Assert.notNull(company);
		
		companyRepository.saveAndFlush(company);
	}
	
	public Company reconstructEdited(CompanyForm companyForm) {
		Assert.isTrue(companyForm.getPassword().equals(
				companyForm.getPasswordConfirm()));
		Assert.isTrue(companyForm.getId() > 0);
		
		Company result;
		CreditCard creditCard;
		UserAccount userAccount;
		BCryptPasswordEncoder encoder;
		
		result = findOne(companyForm.getId()); 
		
		creditCard = result.getCreditCard();
		userAccount = result.getUser();

		// CreditCard
		creditCard.setBrandName(companyForm.getBrandName());
		creditCard.setCVV(companyForm.getCvvCode());
		creditCard.setExpirationMonth(companyForm.getExpirationMonth());
		creditCard.setExpirationYear(companyForm.getExpirationYear());
		creditCard.setHolderName(companyForm.getHolderName());
		creditCard.setNumber(companyForm.getNumber());

		// UserAccount
		userAccount.setUsername(companyForm.getUsername());
		encoder = new BCryptPasswordEncoder();
		userAccount.setPassword(
				encoder.encode(companyForm.getPassword()));

		result.setCreditCard(creditCard);
		result.setEmail(companyForm.getEmail());
		result.setName(companyForm.getName());
		result.setHomePage(companyForm.getHomePage());
		result.setContactPhone(companyForm.getContactPhone());
		result.setSurname(companyForm.getSurname());
		result.setAddress(companyForm.getAddress());
		result.setDescription(companyForm.getDescription());
		result.setDaysBeforeCancel(companyForm.getDaysBeforeCancel());
		result.setPricePerDay(companyForm.getPricePerDay());
		result.setTic(companyForm.getTic());
		result.setUser(userAccount);

		return result;
	}
	
	public CompanyForm fragment(Company company) {
		Assert.notNull(company);
		
		CompanyForm result;

		result = new CompanyForm();
		
		// CreditCard
		result.setBrandName(company.getCreditCard().getBrandName());
		result.setCvvCode(company.getCreditCard().getCVV());
		result.setExpirationMonth(company.getCreditCard().getExpirationMonth());
		result.setExpirationYear(company.getCreditCard().getExpirationYear());
		result.setHolderName(company.getCreditCard().getHolderName());
		result.setNumber(company.getCreditCard().getNumber());

		// UserAccount
		result.setUsername(company.getUser().getUsername());
		result.setPassword(company.getUser().getPassword());
		result.setPasswordConfirm(company.getUser().getPassword());

		result.setEmail(company.getEmail());
		result.setName(company.getName());
		result.setHomePage(company.getHomePage());
		result.setContactPhone(company.getContactPhone());
		result.setSurname(company.getSurname());
		result.setAddress(company.getAddress());
		result.setDescription(company.getDescription());
		result.setDaysBeforeCancel(company.getDaysBeforeCancel());
		result.setPricePerDay(company.getPricePerDay());
		result.setId(company.getId());

		return result;
	}

	public Collection<Review> findReviews(int companyId) {
		Collection<Review> result;
		
		result = companyRepository.findOne(companyId).getReviews();
		
		return result;
	}
	
	public boolean isPrincipal(int companyId) {
		boolean result;
		
		result = findOne(companyId).getUser().equals(LoginService.getPrincipal());
				
		return result;
	}

	public Company findOneByPrincipal() {
		Company result;
		
		result = companyRepository.findOneByPrincipal(LoginService.getPrincipal().getId());
		
		return result;
	}


}
