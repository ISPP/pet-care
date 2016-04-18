package test;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import services.ActorService;
import services.CommentService;
import services.ComplaintService;
import services.PetOwnerService;
import domain.Comment;
import domain.Complaint;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@TransactionConfiguration(defaultRollback = false)
public class CommentServicePositiveTest extends AbstractTest {

	@Autowired
	private CommentService commentService;
	@Autowired
	private ComplaintService complaintService;
	@Autowired
	private PetOwnerService petOwnerService;
	
	@Autowired
	private ActorService actorService;

	// todos los comentarios
	@Test
	public void testFindAllComments() {
		Collection<Comment> comments;
		comments = commentService.findAll();
		Assert.isTrue(comments.size() == 2);
	}

	// crear un comentario
	@Test
	public void testCreateComment() {
		authenticate("petOwner1");
		Comment comment;
		Complaint complaint = complaintService.findOne(160);
		Collection<Comment> comments;
		comment = commentService.create(complaint);
		comment.setText("Comment test");
		commentService.save(comment);
		comments = commentService.findAll();
		Assert.isTrue(comments.size() == 3);
		unauthenticate();
	}

	
	@Test
	public void testFindCommentByActorId(){
		authenticate("petOwner1");
		Complaint complaint;
		complaint = complaintService.findOne(160);
		
		Collection<Comment> comments = commentService.findCommentByActorId(complaint.getId());
		Assert.isTrue(comments.size()==2);
		
		unauthenticate();
	}

}