package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Administrator;
import domain.Review;

import repositories.ReviewRepository;



@Service
@Transactional
public class ReviewService {
	public ReviewService(){
		super();
	}
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	public Review create() {
		Review result;
		result = new Review();
		return result;
	}

	public Review save(Review review) {
		Review result;
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

}
