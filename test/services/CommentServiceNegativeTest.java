package services;

import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Actor;
import domain.Comment;
import domain.Complaint;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class CommentServiceNegativeTest extends AbstractTest {

	@Autowired
	private CommentService commentService;
	@Autowired
	private ComplaintService complaintService;
	@Autowired
	private ActorService actorService;

	// crear un comentario sin complaint asociada
	@Test(expected = IllegalArgumentException.class)
	public void testCreateComment() {
		authenticate("admin");
		Comment comment;
		comment = commentService.create(null);
		comment.setText("Comment test");
		commentService.save(comment);
		commentService.flush();
		unauthenticate();
	}
	
	// crear un comentario sin complaint asociada
	@Test(expected = IllegalArgumentException.class)
	public void testCreateComment2() {
		authenticate("petOwner2");
		Complaint complaint = complaintService.findOne(160);
		Comment comment;
		comment = commentService.create(complaint);
		comment.setText("Comment test");
		commentService.save(comment);
		commentService.flush();
		unauthenticate();
	}
	
	

	// crear un comentario nulo
	@Test(expected = IllegalArgumentException.class)
	public void testCreateCommentNull() {
		authenticate("admin");
		Comment comment;
		Complaint complaint;
		List<Complaint> complaints;
		complaints = (List<Complaint>) complaintService.findAll();
		complaint = complaints.get(0);

		comment = commentService.create(complaint);
		comment.setText("Comment test");
		//comment.setComplaint(complaint);
		commentService.save(null);
		commentService.flush();
		unauthenticate();
	}

	// crear un comentario sin authenticarse
	@Test(expected = IllegalArgumentException.class)
	public void testCreateCommentNotAuthenticate() {
		authenticate(null);
		Comment comment;
		Complaint complaint;

		List<Complaint> complaints;
		complaints = (List<Complaint>) complaintService.findAll();
		complaint = complaints.get(0);
		comment = commentService.create(complaint);
		comment.setText("Comment test");
		comment.setComplaint(complaint);
		commentService.save(comment);
		unauthenticate();
	}
	
	@Test(expected=NullPointerException.class)
	public void testfindCommentByActorId(){
		authenticate("petOwner2");
		Actor actor = actorService.findActorByUserId();
		
		List<Comment> comment = (List<Comment>) commentService.findCommentByActorId(actor.getId());
		
		
		unauthenticate();
	}

}