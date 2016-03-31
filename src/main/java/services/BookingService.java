package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrator;
import domain.Booking;
import domain.PetOwner;
import domain.PetShipper;
import domain.PetSitter;
import domain.Supplier;
import domain.Trip;
import repositories.BookingRepository;

@Service
@Transactional
public class BookingService {

	public BookingService(){
		super();
	}
	
	//Managed repository -----------------------------------------------------
	
	@Autowired
	private BookingRepository bookingRepository;
	
	// Supporting services -----------------------------------------------------

	@Autowired
    private SupplierService supplierService;
	@Autowired
    private PetSitterService petSitterService;
	@Autowired
    private PetShipperService petShipperService;
	@Autowired
    private CompanyService companyService;
	@Autowired
    private TripService tripService;
	@Autowired
    private PetOwnerService petOwnerService;

    // Simple CRUD methods -----------------------------------------------------
	
	public Booking createSitter(int sitterId, boolean night, Date startMoment, Date endMoment){
        Booking result;
        PetOwner petOwner;
        Date creationMoment;
        Double price;
        String code;
        UUID uuidCode;
        Supplier supplier;
        PetSitter petSitter;

        petSitter = petSitterService.findOne(sitterId);
        //supplier = supplierService.findOne(petSitter.getSupplier().getId());
        result = new Booking();
        creationMoment = new Date(System.currentTimeMillis()-1);
        if(night){
            price = petSitter.getPriceNight();//Price es el total o sólo esto?
        }else{
            price = petSitter.getPriceHour();
        }
        uuidCode = UUID.randomUUID();
        code = uuidCode.toString().replaceAll("-", ""); //Eliminar guiones del code
        //petOwner = petOwnerService.findOneByPrincipal();

        //result.setCreationMoment(creationMoment);
        result.setCode(code);
        //result.setStartMoment(startMoment);
        //result.setEndMoment(endMoment);
        result.setPrice(price);
        result.setStatus("PENDING");
        result.setNight(night);
        //result.setPetOwner(petOwner);
        //result.setSupplier(supplier);

        return result;
    }

    //Cambiar aquí el argumento night, seguramente no sea necesario utilizarlo, porque un shipper siempre
    //hará su trabajo en X horas, no en días. Es decir, night siempre a false en el caso de Shippers.
    //Pero por ahora lo dejo así.
    //Además, sería conveniente que un owner cuando crea una reserva con un shipper, haya creado antes todos
    //los datos del viaje (haya creado un trip, y por lo tanto este se le pase como argumento ahora).
    /*public Booking createShipper(int shipperId, boolean night, Date startMoment, Date endMoment, int tripId){
        Booking result;
        PetOwner petOwner;
        Date creationMoment;
        Double price;
        String code;
        UUID uuidCode;
        Supplier supplier;
        PetShipper petShipper;
        Trip trip;

        trip = tripService.findOne(tripId);
        petShipper = petShipperService.findOne(shipperId);
        //supplier = supplierService.findOne(petShipper.getSupplier().getId());
        result = new Booking();
        creationMoment = new Date(System.currentTimeMillis()-1);
        Assert.isTrue(!night);
        price = trip.getCost();
        uuidCode = UUID.randomUUID();
        code = uuidCode.toString().replaceAll("-", ""); //Para eliminar guiones del code
        //petOwner = petOwnerService.findOneByPrincipal();

        //result.setCreationMoment(creationMoment);
        result.setCode(code);
        //result.setStartMoment(startMoment);
        //result.setEndMoment(endMoment);
        result.setPrice(price);
        result.setStatus("PENDING");
        result.setNight(night);
        //result.setPetOwner(petOwner);
        result.setSupplier(supplier);

        return result;
    }*/

    //¿Cómo vamos a hacer esto? ¿Nosotros manejamos las reservas con las companies?
    /*public Booking createCompany(Long companyId, boolean night, ZonedDateTime startMoment, ZonedDateTime endMoment){
        Booking result;
        PetOwner petOwner;
        Date creationMoment;
        Double price;
        String code;
        UUID uuidCode;
        Supplier supplier;
        Company company;

        company = companyService.findOne(companyId);
        supplier = supplierService.findOne(company.getSupplier().getId());
        result = new Booking();
        creationMoment = new Date(System.currentTimeMillis()-1);
        if(night){
            price = company.getPriceNight();
        }else{
            price = company.getPriceHour();
        }
        uuidCode = UUID.randomUUID();
        code = uuidCode.toString().replaceAll("-", ""); //Eliminar guiones del code
        petOwner = petOwnerService.findOneByPrincipal();

        result.setCreationMoment(creationMoment);
        result.setCode(code);
        result.setStartMoment(startMoment);
        result.setEndMoment(endMoment);
        result.setPrice(price);
        result.setStatus("PENDING");
        result.setNight(night);
        result.setPetOwner(petOwner);
        result.setSupplier(supplier);

        return result;
    }*/

    /*public Booking save(Booking booking){
        Assert.notNull(booking);
        Booking result;
        Date arrival;
        Date departure;
        int nights;
        Date date;
        int beds;

        date = new Date(System.currentTimeMillis() - 1);
        nights = booking.getNights();
        arrival = booking.getArrival();
        Assert.isTrue(arrival.after(date));
        departure = new Date(arrival.getYear(), arrival.getMonth(), arrival.getDate() + nights - 1);
        booking.setCreationMoment(new Date(System.currentTimeMillis() -1));
        booking.setDeparture(departure);

        beds = findBedsByLodgeDay(arrival, nights, booking);

        checkBookingIsCorrect(booking, beds);
        result = bookingRepository.save(booking);
        return result;
    }*/

	public void delete(Booking booking) {
		bookingRepository.delete(booking);
	}

	public Collection<Booking> findAll() {
		Collection<Booking> result;
		result = bookingRepository.findAll();
		return result;
	}

    public Booking findOne(int bookingId){
        Booking result;

        result = bookingRepository.findOne(bookingId);

        return result;
    }

    //Other business methods ------------------------------------------------
    
    public List<Booking> findByDateSitter(Date startDate, Date endDate, int sitterId) {
    	List<Booking> result;
    	
    	result = new ArrayList<Booking>(bookingRepository.findByDateSitter(startDate, endDate, sitterId));
    	
		return result;
	}

    /*public Booking cancelBooking(Booking booking){
        Assert.notNull(booking);
        Booking result;
        checkIsOwner(booking);
        Date date;

        date = new Date(System.currentTimeMillis() - 1);
        date.setDate(date.getDate()+1);
        booking.setCancelled(true);
        Assert.isTrue(booking.getArrival().after(date));
        result = bookingRepository.save(booking);

        return result;
    }*/


    /*public List<Booking> findAllByOwner(){
        List<Booking> result;
        int id;

        id = LoginService.getPrincipal().getId();
        checkIsPilgrim();

        result  = bookingRepository.findAllByPilgrim(id);

        return result;
    }

    public Collection<Booking> findAllByInnkeeper(){
        Collection<Booking> result;
        int id;

        id = LoginService.getPrincipal().getId();
        result  = bookingRepository.findAllByInnkeeper(id);

        return result;
    }

    public Integer findBedsByLodgeDay(Date arrival, int nights, Booking booking){
        Integer result, aux;
        int lodgeId = booking.getLodge().getId();
        aux = 0;
        result = lodgeService.findOne(lodgeId).getBeds();
        Date auxDate;

        auxDate = arrival;
        for(int i = 1; i <= nights; i++){
            try{
                int asd = bookingRepository.findBedsByLodgeDay(lodgeId, auxDate, booking.getId());
                if(aux < asd){
                    aux = asd;
                }
            }catch(NullPointerException oops){}
            auxDate = new Date(arrival.getYear(), arrival.getMonth(), arrival.getDate() + i);
        }

        result = result - aux;

        return result;
    }

    public void checkBookingIsCorrect(Booking booking, int beds){
        Assert.notNull(booking);
        Assert.isTrue(booking.getBeds() <= beds);
    }

    public void checkIsOwner(Booking booking){
        UserAccount principal;
        UserAccount owner;

        principal = LoginService.getPrincipal();
        owner = booking.getPilgrim().getUserAccount();

        Assert.isTrue(principal.equals(owner));
    }

    public void checkIsPilgrim(){
        Authority pilgrim;

        pilgrim = new Authority();
        pilgrim.setAuthority(Authority.PILGRIM);
        Assert.isTrue(LoginService.getPrincipal().getAuthorities().contains(pilgrim));
    }


    public Collection<Booking> bookingsHistory(){
        Collection<Booking> result;
        int id;

        id = LoginService.getPrincipal().getId();
        result = bookingRepository.bookingsHistory(id);
        return result;
    }

    public Collection<Booking> findWithoutInvoiceByInnkeeper() {
        Collection<Booking> result;
        int id;

        id = LoginService.getPrincipal().getId();
        result  = bookingRepository.findWithoutInvoiceByInnkeeper(id);

        return result;
    }

    public Integer findBedsByInnkeeperDay(int innkeeperId, Date date){
        Assert.notNull(date);
        Integer result;

        result = bookingRepository.findBedsByInnkeeperDay(innkeeperId, date);
        if(result == null){
            result = 0;
        }
        return result;
	}*/
}
