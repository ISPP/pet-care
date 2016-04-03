package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
	
	@Autowired
	private ComplaintService complaintService;
	
	@Autowired
	private ActorService actorService;
	
	public Comment create(Complaint complaint) {
		Comment result;
		Actor actor;
		actor = actorService.findActorByUserId();
		Assert.notNull(actor, "No hay ningun actor conectado");
		Assert.isTrue(complaint.getAdministrator().getId()==actor.getId()||complaint.getCustomer().getId()==actor.getId(), "Intentando acceder a un sitio sin permisos");
		result = new Comment();
		result.setCreationMoment(new Date(System.currentTimeMillis()-1000));
		result.setActor(actor);
		result.setComplaint(complaint);
		
		
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
	
	/*
	public Collection<Comment> findCommentByActorId(){
		Collection<Comment> result;
		Actor actor;
		actor = actorService.findActorByUserId();
		
		result = commentRepository.findCommentByActorId(actor.getId());
		
		return result;
		
	}*/
	
	
	public Collection<Comment> findCommentByActorId(Integer id){
		Collection<Comment> result;
		Actor actor;
		actor = actorService.findActorByUserId();
		Assert.notNull(actor, "No hay ningun actor conectado");
		Complaint complaint;
		complaint = complaintService.findOne(id);
		Assert.isTrue(complaint.getAdministrator().getId()==actor.getId()||complaint.getCustomer().getId()==actor.getId(), "Intentando acceder a un sitio sin permisos");
		
		result = commentRepository.findCommentByComplaintId(id);
		
		
		
		
		return result;
		
	}
	
	public void flush(){
		commentRepository.flush();
	}


}