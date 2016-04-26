package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ReviewRepository;
import domain.Booking;
import domain.PetOwner;
import domain.Review;
import domain.Supplier;

@Service
@Transactional
public class ReviewService {
	public ReviewService(){
		super();
	}
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private PetOwnerService petOwnerService;
	
	@Autowired
	private SupplierService supplierService;
	
	
	public Review create(Booking booking) {
		Review result;
		PetOwner petOwner;
		try{
			petOwner = petOwnerService.findOneByPrincipal();
		}catch(Throwable t){
			petOwner = null;
		}
		result = new Review();
		result.setBooking(booking);
		result.setReviewer(petOwner);
		result.setReviewed(booking.getSupplier());
		result.setCreationMoment(new Date());
		return result;
	}

	public Review save(Review review) {
		Review result;
		review.setCreationMoment(new Date());
		result = reviewRepository.saveAndFlush(review);
		return result;
	}

	public void delete(Review review) {
		reviewRepository.delete(review);
	}

	public Collection<Review> findAll() {
		Collection<Review> result;
		result = reviewRepository.findAll();
		return result;
	}

	public Review findOne(Integer id) {
		Review result;
		result = reviewRepository.findOne(id);
		return result;
	}
	
	public boolean checkValidDate(Booking booking){
		boolean result,reviewed,passed,canceled;
		Date today,bookingDate;
		
		result = true;
		today = new Date();
		bookingDate = booking.getEndMoment();
		try{
			reviewed = booking.getReview().getId()>0;
		}catch(Throwable t){
			reviewed = false;
		}
		passed = today.after(bookingDate);
		canceled = booking.getCancelled();
		if( canceled || !passed || reviewed==true){
			result = false;
		}
		Assert.isTrue(result);
		
		return result;
	}
	
	
	//List the reviews where they are the reviewed ones(customer)
	public Collection<Review> findReviewBySupplierId(){
		Collection<Review> result;
		Supplier supplier;
		supplier = supplierService.getLoggedSupplier();
		
		result = reviewRepository.findReviewBySupplierId(supplier.getId());
		
		return result;
	}

	public Collection<Review> findReviewByPetOwnerId() {
		Collection<Review> result;
		PetOwner petOwner = petOwnerService.getLogged();
		
		result = reviewRepository.findReviewByPetOwnerId(petOwner.getId());
		
		return result;
	}


}
