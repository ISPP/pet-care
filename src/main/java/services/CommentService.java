package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.*;

import repositories.CommentRepository;


@Service
@Transactional
public class CommentService {
	
	public CommentService(){
		super();
	}
	
	@Autowired
	private CommentRepository commentRepository;
	
	public Comment create() {
		Comment result;
		result = new Comment();
		return result;
	}

	public Comment save(Comment comment) {
		Comment result;
		result = commentRepository.saveAndFlush(comment);
		return result;
	}

	public void delete(Comment comment ) {
		commentRepository.delete(comment);
	}

	public Collection<Comment> findAll() {
		Collection<Comment> result;
		result = commentRepository.findAll();
		return result;
	}

	public Comment findOne(Integer id) {
		Comment result;
		result = commentRepository.findOne(id);
		return result;
	}


}
