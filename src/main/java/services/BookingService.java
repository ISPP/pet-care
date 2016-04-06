package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Booking;
import domain.PetOwner;
import domain.PetSitter;
import domain.Supplier;
import forms.BookingForm;
import repositories.BookingRepository;

@Service
@Transactional
public class BookingService {

	public BookingService() {
		super();
	}

	// Managed repository -----------------------------------------------------

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private SupplierService supplierService;
	

	// Supporting services -----------------------------------------------------

	@Autowired
	private PetSitterService petSitterService;
	/*
	 * @Autowired private PetShipperService petShipperService;
	 * 
	 * @Autowired private CompanyService companyService;
	 * 
	 * @Autowired private TripService tripService;
	 */
	@Autowired
	private PetOwnerService petOwnerService;

	// Simple CRUD methods -----------------------------------------------------

	public Booking createSitter(int sitterId, boolean night, Date startMoment,
			Date endMoment) {
		Booking result;
		PetOwner petOwner;
		Date creationMoment;
		Double price;
		String code;
		UUID uuidCode;
		PetSitter petSitter;

		petOwner = petOwnerService.findOneByPrincipal();
		petSitter = petSitterService.findOne(sitterId);
		// supplier = supplierService.findOne(petSitter.getSupplier().getId());
		result = new Booking();
		creationMoment = new Date(System.currentTimeMillis() - 1000);
		if (night) {
			price = petSitter.getPriceNight();// Price es el total o sólo esto?
		} else {
			price = petSitter.getPriceHour();
		}
		uuidCode = UUID.randomUUID();
		code = uuidCode.toString().replaceAll("-", ""); // Eliminar guiones del
														// code

		result.setCreationMoment(creationMoment);
		result.setCode(code);
		result.setStartMoment(startMoment);
		result.setEndMoment(endMoment);
		result.setPrice(price);
		result.setStatus("PENDING");
		result.setNight(night);
		result.setPetOwner(petOwner);
		result.setSupplier(petSitter);

		return result;
	}

	// Cambiar aquí el argumento night, seguramente no sea necesario utilizarlo,
	// porque un shipper siempre
	// hará su trabajo en X horas, no en días. Es decir, night siempre a false
	// en el caso de Shippers.
	// Pero por ahora lo dejo así.
	// Además, sería conveniente que un owner cuando crea una reserva con un
	// shipper, haya creado antes todos
	// los datos del viaje (haya creado un trip, y por lo tanto este se le pase
	// como argumento ahora).
	/*
	 * public Booking createShipper(int shipperId, boolean night, Date
	 * startMoment, Date endMoment, int tripId){ Booking result; PetOwner
	 * petOwner; Date creationMoment; Double price; String code; UUID uuidCode;
	 * Supplier supplier; PetShipper petShipper; Trip trip;
	 * 
	 * trip = tripService.findOne(tripId); petShipper =
	 * petShipperService.findOne(shipperId); //supplier =
	 * supplierService.findOne(petShipper.getSupplier().getId()); result = new
	 * Booking(); creationMoment = new Date(System.currentTimeMillis()-1);
	 * Assert.isTrue(!night); price = trip.getCost(); uuidCode =
	 * UUID.randomUUID(); code = uuidCode.toString().replaceAll("-", ""); //Para
	 * eliminar guiones del code //petOwner =
	 * petOwnerService.findOneByPrincipal();
	 * 
	 * //result.setCreationMoment(creationMoment); result.setCode(code);
	 * //result.setStartMoment(startMoment); //result.setEndMoment(endMoment);
	 * result.setPrice(price); result.setStatus("PENDING");
	 * result.setNight(night); //result.setPetOwner(petOwner);
	 * result.setSupplier(supplier);
	 * 
	 * return result; }
	 */

	// ¿Cómo vamos a hacer esto? ¿Nosotros manejamos las reservas con las
	// companies?
	/*
	 * public Booking createCompany(Long companyId, boolean night, ZonedDateTime
	 * startMoment, ZonedDateTime endMoment){ Booking result; PetOwner petOwner;
	 * Date creationMoment; Double price; String code; UUID uuidCode; Supplier
	 * supplier; Company company;
	 * 
	 * company = companyService.findOne(companyId); supplier =
	 * supplierService.findOne(company.getSupplier().getId()); result = new
	 * Booking(); creationMoment = new Date(System.currentTimeMillis()-1);
	 * if(night){ price = company.getPriceNight(); }else{ price =
	 * company.getPriceHour(); } uuidCode = UUID.randomUUID(); code =
	 * uuidCode.toString().replaceAll("-", ""); //Eliminar guiones del code
	 * petOwner = petOwnerService.findOneByPrincipal();
	 * 
	 * result.setCreationMoment(creationMoment); result.setCode(code);
	 * result.setStartMoment(startMoment); result.setEndMoment(endMoment);
	 * result.setPrice(price); result.setStatus("PENDING");
	 * result.setNight(night); result.setPetOwner(petOwner);
	 * result.setSupplier(supplier);
	 * 
	 * return result; }
	 */

	/*
	 * public Booking save(Booking booking){ Assert.notNull(booking); Booking
	 * result; Date arrival; Date departure; int nights; Date date; int beds;
	 * 
	 * date = new Date(System.currentTimeMillis() - 1); nights =
	 * booking.getNights(); arrival = booking.getArrival();
	 * Assert.isTrue(arrival.after(date)); departure = new
	 * Date(arrival.getYear(), arrival.getMonth(), arrival.getDate() + nights -
	 * 1); booking.setCreationMoment(new Date(System.currentTimeMillis() -1));
	 * booking.setDeparture(departure);
	 * 
	 * beds = findBedsByLodgeDay(arrival, nights, booking);
	 * 
	 * checkBookingIsCorrect(booking, beds); result =
	 * bookingRepository.save(booking); return result; }
	 */

	public void delete(Booking booking) {
		bookingRepository.delete(booking);
	}

	public Collection<Booking> findAll() {
		Collection<Booking> result;
		result = bookingRepository.findAll();
		return result;
	}

	public Booking findOne(int bookingId) {
		Booking result;

		result = bookingRepository.findOne(bookingId);

		return result;
	}

	// Other business methods ------------------------------------------------

	public List<Booking> findByDateSitter(Date startDate, Date endDate,
			int sitterId) {
		List<Booking> result;

		result = new ArrayList<Booking>(bookingRepository.findByDateSitter(
				startDate, endDate, sitterId));

		return result;
	}

	public BookingForm create() {
		BookingForm result;
		result = new BookingForm();
		return result;
	}

	public Booking reconstruct(BookingForm bookingForm) {
		Booking result;
		result = new Booking();
		//result.setCancelled(bookingForm.isCancelled());
		result.setNight(bookingForm.isNight());
		result.setStartMoment(bookingForm.getStartMoment());
		result.setEndMoment(bookingForm.getendMoment());
		result.setSupplier(bookingForm.getSupplier());

		return result;
	}

	public Booking save(Booking booking) {
		Booking result;
		result = bookingRepository.save(booking);
		return result;
	}

	private double calculateCostForPetSitterBookings(Booking b) {
		Double result;
		Date startMoment, endMoment;
		PetSitter petSitter;
		petSitter=(PetSitter) b.getSupplier();
		//if it is taking into account nights, that is, days
		startMoment = b.getStartMoment();
		endMoment = b.getEndMoment();
		if(b.isNight()){
			
			int days = Days.daysBetween(new DateTime(startMoment),
					new DateTime(endMoment)).getDays();
			result=days*petSitter.getPriceNight();
		}else{
			int hours=Hours.hoursBetween(new DateTime(startMoment),
					new DateTime(endMoment)).getHours();
			result=hours*petSitter.getPriceHour();
		}
		
		
		return result;

	}

	public Booking registerPetSitterBooking(Booking booking){
    	Booking result;
    	booking.setCreationMoment(new Date(System.currentTimeMillis()-1000));
    	booking.setStatus("PENDING");
    	String code=RandomStringUtils.randomAlphanumeric(10);
    	booking.setCode(code);
    	booking.setCancelled(false);
    	PetOwner petOwner;
    	petOwner=petOwnerService.findOneByPrincipal();
    	booking.setPetOwner(petOwner);
    	
    	double cost=calculateCostForPetSitterBookings(booking);
    	booking.setPrice(cost);
    	result=save(booking);
    	return result;
    	
    }
	
	
	public Collection<Booking> findBokkingAcceptedBySupplierId(){
		Collection<Booking> res;
		Supplier supplier =  supplierService.getLoggedSupplier();
		Assert.notNull(supplier, "no hay un supplier logueado");
		res = bookingRepository.findBokkingAcceptedBySupplierId(supplier.getId());
			
		return res;
	}
	
	
	
	
	public Collection<Booking> findBookingCanCancelByPetOwnerId(){
		Collection<Booking> res;
		PetOwner petOwner = petOwnerService.findOneByPrincipal();
		Assert.notNull(petOwner, "No hay un usuario logueado");
		res = bookingRepository.findBookingCanCancelByPetOwnerId(petOwner.getId());
		
		return res;
	}

	public void cancelBooking(Integer id) {
		PetOwner petOwner = petOwnerService.findOneByPrincipal();
		Assert.notNull(petOwner, "No hay un pet owner conectado");
		Booking booking = bookingRepository.findOne(id);
		Assert.isTrue(booking.getPetOwner().getId()==petOwner.getId(), "accediendo a un sitio sin permisos");
		booking.setCancelled(true);
		bookingRepository.save(booking);
		
	}
	
	
	public Collection<Booking> findBookingCanAceptRejectedByCustomerId(){
		Collection<Booking> res;
		Supplier supplier =  supplierService.getLoggedSupplier();
		Assert.notNull(supplier, "no hay un supplier logueado");
		res = bookingRepository.findBookingCanAceptRejectedByCustomerId(supplier.getId());		
		return res;
	}
	
	public void aceptedBooking(Integer id){
		Supplier supplier =  supplierService.getLoggedSupplier();
		Assert.notNull(supplier, "no hay un supplier logueado");
		Booking booking = bookingRepository.findOne(id);
		Assert.isTrue(booking.getSupplier().getId()==supplier.getId(), "accediendo a un sitio sin permisos");
		booking.setStatus("ACCEPTED");
		bookingRepository.save(booking);
	
	}
	
	
	public void rejectedBooking(Integer id){
		Supplier supplier =  supplierService.getLoggedSupplier();
		Assert.notNull(supplier, "no hay un supplier logueado");
		Booking booking = bookingRepository.findOne(id);
		Assert.isTrue(booking.getSupplier().getId()==supplier.getId(), "accediendo a un sitio sin permisos");
		booking.setStatus("REJECTED");
		bookingRepository.save(booking);
	
	}
	
	
	
	
}
