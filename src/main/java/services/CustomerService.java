package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.LoginService;
import security.UserAccount;
import domain.Customer;
import domain.Pet;
import domain.Photo;


@Service
@Transactional
public class CustomerService {
	
	// Managed repository -----------------------------------------------------
	
		@Autowired
		private CustomerRepository customerRepository;
		@Autowired
		private PhotoService photoService;
		
		// Supporting services-----------------------------------------------------
		
		// Constructors -----------------------------------------------------------
		
		public CustomerService() {
			super();
		}

		// Simple CRUD methods-------------------------------------------------------------

		public Customer create() {
			Customer result;
			result = new Customer();
			return result;
		}
		
		public void save(Customer customer) {
			Assert.notNull(customer,"intentando guardar un cliente nulo");
			customerRepository.save(customer);
		}

		public Customer findOne(Integer id) {
			Assert.isTrue(id!=0,"tried to find a customer with id zero");
			return customerRepository.findOne(id);
		}

		
		public Collection<Customer> findAll() {
			return customerRepository.findAll();
		}

		public void Customer(Customer i) {
			Assert.notNull(i,"intentando borrar un cliente nulo");
			customerRepository.delete(i);
		}

		// Other business methods -------------------------------------------------
		
		public Customer getLoggedCustomer() {
			Customer result;
			UserAccount user;
			user = LoginService.getPrincipal();
			result = customerRepository.findActorByUsername(user.getUsername());
			return result;
		}
		
		public void addPhoto(Customer customer, Photo photo) {
			Assert.notNull(customer);
			Assert.notNull(photo);
			
			photo.setAvatar(false);
			Collection<Photo> photos;
			
			if(customer.getPhotos()!=null){
				photos = customer.getPhotos();
				
			}
			else{
				photos = new ArrayList<Photo>();
			}
			
			
			photos.add(photo);
			customer.setPhotos(photos);
			
			customerRepository.save(customer);
		}
		
		
		public void changeAvatar(Customer customer, Photo photo) {
			Assert.notNull(customer);
			Assert.notNull(photo);
			for(Photo p : customer.getPhotos()){
				if(p.getAvatar()==true){
					p.setAvatar(false);
					photoService.saveAvatar(p);
					break;
				}
			}
			photo.setAvatar(true);
			photoService.saveAvatar(photo);
		}
	
}
