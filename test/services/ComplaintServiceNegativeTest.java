package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrator;
import domain.Complaint;
import forms.ComplaintForm;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class ComplaintServiceNegativeTest extends AbstractTest {

	@Autowired
	private ComplaintService complaintService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private AdministratorService  administratorService;

	
	@Test(expected= IllegalArgumentException.class)
	public void testCreateComplaint(){
		authenticate("admin");
		
		ComplaintForm complaintForm;
		complaintForm = complaintService.createComplaintForm();
		complaintForm.setDescription("description");
		complaintForm.setTitle("title");
		
		Complaint complaint;
		complaint = complaintService.reconstruct(complaintForm);
		complaintService.save(complaint);

		unauthenticate();
	}


	@Test(expected= IllegalArgumentException.class)
	public void testfindComplaintByCustommerIdAndResolution(){
		authenticate("petOwner1");
		
		Collection<Complaint> complaints = complaintService.findComplaintByCustommerIdAndResolution();
		Assert.isTrue(complaints.size()==4);
		
		unauthenticate();
	}
	
	
	@Test(expected= IllegalArgumentException.class)
	public void testfindComplaintByCustommerIdAndNotResolution(){
		
		
		Collection<Complaint> complaints = complaintService.findComplaintByCustommerIdAndNotResolution();
		Assert.isTrue(complaints.size()==2);
		
		
	}
	

	@Test(expected= IllegalArgumentException.class)
	public void testAssignComplaint(){
		authenticate("petSitter1");
		Complaint complaint = complaintService.findOne(163);
		complaintService.assignAdminToComplaint(complaint);
		complaintService.save(complaint);
			
		unauthenticate();
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testSolveComplaint(){
		authenticate("petSitter1");
		Complaint complaint = complaintService.findOne(161);
		ComplaintForm complaintForm = complaintService.solveComplaintForm(complaint);
		complaintForm.setResolution("resuelta");
		
		Complaint complaintResuelta = complaintService.reconstructToSolve(complaintForm);
		
		complaintService.save(complaintResuelta);
		Administrator admin = administratorService.getLoggedAdmin();
		Assert.notNull(admin);
		
		unauthenticate();
	}
	
	
	
	
}