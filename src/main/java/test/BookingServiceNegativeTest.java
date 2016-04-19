package test;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.BookingService;
import services.PetOwnerService;
import services.PetSitterService;
import domain.Booking;
import domain.PetOwner;
import domain.PetSitter;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class BookingServiceNegativeTest extends AbstractTest{

	@Autowired
	private PetOwnerService petOwnerService;
	@Autowired
	private PetSitterService petSitterService;
	@Autowired
	private BookingService bookingService;
	
	/*@Test
	public void testcreateSitter(){
		authenticate("petOwner1");
		PetOwner petOwner = petOwnerService.getLogged();
		PetSitter petSitter = petSitterService.findOne(76);
		Date startMoment = new Date(System.currentTimeMillis()+100000);
		Date endMoment= new Date(System.currentTimeMillis()+1000);
		Booking b = bookingService.createSitter(petSitter.getId(),false, startMoment, endMoment);
		Assert.isTrue(bookingService.findAll().size()==5);
		bookingService.save(b);
		Assert.isTrue(bookingService.findAll().size()==6);
		unauthenticate();
	}*/
	
	/*@Test
	public void testfindByDateSitter(){
		PetSitter petSitter = petSitterService.findOne(76);
		Date startMoment = new Date(System.currentTimeMillis()+100000);
		Date endMoment= new Date(System.currentTimeMillis()+100);
		Collection<Booking> bookings = bookingService.findByDateSitter(startMoment, endMoment, petSitter.getId());
		Assert.isTrue(bookings.size()==0);
	}*/
	
	/*@Test
	public void testregisterPetSitterBooking(){
		authenticate("petOwner1");
		Booking b = bookingService.findOne(154);
		Booking b2 = bookingService.registerPetSitterBooking(b);	
		
		unauthenticate();
	}*/
	
	@Test(expected = IllegalArgumentException.class)
	public void testfindBokkingAcceptedBySupplierId(){
		authenticate("petOwner1");
		Collection<Booking>bookings = bookingService.findBokkingAcceptedBySupplierId();
		
		unauthenticate();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testfindBokkingAcceptedBySupplierId2(){
		authenticate("admin1");
		Collection<Booking>bookings = bookingService.findBokkingAcceptedBySupplierId();
		
		unauthenticate();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testfindBookingCanCancelByPetOwnerId(){
		authenticate("admin1");
		Collection<Booking>bookings = bookingService.findBookingCanCancelByPetOwnerId();
		unauthenticate();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testfindBookingCanCancelByPetOwnerId2(){
		authenticate("petSitter1");
		Collection<Booking>bookings = bookingService.findBookingCanCancelByPetOwnerId();
		unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testfindBookingCanCancelByPetOwnerId3(){
		authenticate("petShipper1");
		Collection<Booking>bookings = bookingService.findBookingCanCancelByPetOwnerId();
		unauthenticate();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testfindBookingCanCancelByPetOwnerId4(){
		authenticate("company1");
		Collection<Booking>bookings = bookingService.findBookingCanCancelByPetOwnerId();
		unauthenticate();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testcancelBooking(){
		authenticate("admin1");
		List<Booking>bookings = (List<Booking>) bookingService.findBookingCanCancelByPetOwnerId();
		Booking b = bookings.get(0);
		bookingService.cancelBooking(b.getId());
		Booking b2 = bookingService.findOne(154);
		Assert.isTrue(b2.getCancelled()==true);
		unauthenticate();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testcancelBooking1(){
		authenticate("company1");
		List<Booking>bookings = (List<Booking>) bookingService.findBookingCanCancelByPetOwnerId();
		Booking b = bookings.get(0);
		bookingService.cancelBooking(b.getId());
		Booking b2 = bookingService.findOne(154);
		Assert.isTrue(b2.getCancelled()==true);
		unauthenticate();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testfindBookingCanAceptRejectedByCustomerId(){
		authenticate("petOwner1");
		
		Collection<Booking> bookings = bookingService.findBookingCanAceptRejectedByCustomerId();
		
		unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testaceptedBooking(){
		authenticate("petShipper1");

		bookingService.aceptedBooking(158);
		Booking b = bookingService.findOne(158);
		Assert.isTrue(b.getStatus().equals("ACCEPTED"));
		unauthenticate();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaceptedBooking2(){
		authenticate("petShipper1");

		bookingService.aceptedBooking(154);
		Booking b = bookingService.findOne(154);
		
		unauthenticate();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testrejectedBooking(){
		authenticate("petShipper4");

		bookingService.rejectedBooking(154);
		Booking b = bookingService.findOne(154);
		Assert.isTrue(b.getStatus().equals("REJECTED"));
		unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testrejectedBooking1(){
		authenticate("petShipper1");

		bookingService.rejectedBooking(154);
		Booking b = bookingService.findOne(154);
		Assert.isTrue(b.getStatus().equals("REJECTED"));
		unauthenticate();
	}


}
