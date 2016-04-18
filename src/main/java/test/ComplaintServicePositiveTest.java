package test;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.ActorService;
import services.CommentService;
import services.ComplaintService;
import domain.Complaint;
import forms.ComplaintForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class ComplaintServicePositiveTest extends AbstractTest {

	// Service under
	// test---------------------------------------------------------
	@Autowired
	private ComplaintService complaintService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ActorService actorService;

	// List the complaints in the system
	@Test
	public void testFindAllComplaints() {
		
		Collection<Complaint> complaints;
		complaints = complaintService.findAll();
		Assert.isTrue(complaints.size() == 4);
		}

	@Test
	public void testCreateComplaint(){
		authenticate("petOwner1");
		
		ComplaintForm complaintForm;
		complaintForm = complaintService.createComplaintForm();
		complaintForm.setDescription("description");
		complaintForm.setTitle("title");
		
		Complaint complaint;
		complaint = complaintService.reconstruct(complaintForm);
		complaintService.save(complaint);

		unauthenticate();
	}
	
	
	@Test
	public void testfindComplaintByCustommerIdAndResolution(){
		authenticate("petOwner1");
		
		Collection<Complaint> complaints = complaintService.findComplaintByCustommerIdAndResolution();
		Assert.isTrue(complaints.size()==2);
		
		unauthenticate();
	}
	
	
	@Test
	public void testfindComplaintByCustommerIdAndNotResolution(){
		authenticate("petOwner1");
		
		Collection<Complaint> complaints = complaintService.findComplaintByCustommerIdAndNotResolution();
		Assert.isTrue(complaints.size()==2);
		
		unauthenticate();
	}
	
	/*
	@Test
	public void testAssignComplaint(){
		authenticate("admin");
		Complaint complaint = complaintService.findOne(163);
		complaintService.assignAdminToComplaint(complaint);
		
		System.out.println("======================" +complaint.getAdministrator().getId());
		Assert.notNull(complaint.getAdministrator());
		
		unauthenticate();
	}*/
	
	
	
	
	
	@Test
	public void testSolveComplaint(){
		authenticate("petOwner1");
		Complaint complaint = complaintService.findOne(160);
		ComplaintForm complaintForm = complaintService.solveComplaintForm(complaint);
		complaintForm.setResolution("resuelta");
		
		Complaint complaintResuelta = complaintService.reconstructToSolve(complaintForm);
		
		complaintService.save(complaintResuelta);
	
		
		unauthenticate();
	}
}