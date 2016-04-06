package services;

import java.util.Collection;
import java.util.Date;


import org.springframework.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ComplaintRepository;
import domain.Administrator;
import domain.Complaint;
import domain.Customer;
import forms.ComplaintForm;


@Service
@Transactional
public class ComplaintService {
	
	public ComplaintService(){
		super();
	}
	
	@Autowired
	private ComplaintRepository complaintRepository;
	
	@Autowired
	private CustomerService  customerService;
	
	@Autowired
	private AdministratorService administratorService;
	
	
	public Complaint create() {
		Complaint result;
		result = new Complaint();
		return result;
	}

	
	
	
	public Complaint save(Complaint complaint) {
		Complaint result;
		result = complaintRepository.save(complaint);
		return result;
	}

	public void delete(Complaint complaint) {
		complaintRepository.delete(complaint);
	}

	public Collection<Complaint> findAll() {
		Collection<Complaint> result;
		result = complaintRepository.findAll();
		return result;
	}

	public Complaint findOne(Integer id) {
		Complaint result;
		result = complaintRepository.findOne(id);
		return result;
	}
	
	
	public ComplaintForm createComplaintForm() {

		ComplaintForm result;
		result = new ComplaintForm();
		return result;

	}
	public ComplaintForm solveComplaintForm(Complaint complaint){
		ComplaintForm result;
		result=new ComplaintForm();
		result.setDescription(complaint.getDescription());
		result.setId(complaint.getId());
		result.setTitle(complaint.getTitle());
		return result;
		
	}
	
	
	
	
	public Complaint reconstruct(ComplaintForm complaintForm) {
		Complaint result;
		result = create();
		result.setDescription(complaintForm.getDescription());
		result.setTitle(complaintForm.getTitle());
		result.setCreationMoment(new Date(System.currentTimeMillis() - 1000));
		Customer customer;

		customer = customerService.getLoggedCustomer();
		result.setCustomer(customer);

		result.setStatus("OPEN");
		
		return result;

	}
	
	public Complaint reconstructToSolve(ComplaintForm complaintForm) {
		Complaint result;
		result = findOne(complaintForm.getId());
		result.setResolution(complaintForm.getResolution());
		return result;

	}
	
	public void solveComplaint(Complaint complaint){
		Administrator admin;
		admin=administratorService.getLoggedAdmin();
		//check the administrator that is solving the complaint is the assigned one
		Assert.isTrue(complaint.getAdministrator().getId()==admin.getId(), "Intentando acceder a un sitio sin permisos");
		Assert.notNull(admin, "no hay un administrador logueado");
		save(complaint);
		
	}
	
	

    //List the complaints he/she has made that havent been solved yet.
    public Collection<Complaint> findComplaintByCustommerIdAndResolution(){
    	Collection<Complaint> result;
        Customer customer;
       customer = customerService.getLoggedCustomer();
       Assert.notNull(customer,"no hay logueado un customer");
        result = complaintRepository.findComplaintByCustommerIdAndResolution(customer.getId());
       
       
        //Assert.isTrue(customer.getComplaints().contains(result),"el customer no tiene asosiadas las complaint");

        return result;
    }
    //List his/her complaints that have been solved by an administrator
    public Collection<Complaint> findComplaintByCustommerIdAndNotResolution(){
    	Collection<Complaint> result;
        Customer customer;
        customer = customerService.getLoggedCustomer();
        Assert.notNull(customer,"no hay logueado un customer");
        result = complaintRepository.findComplaintByCustommerIdAndNotResolution(customer.getId());

       // Assert.isTrue(customer.getComplaints().contains(result),"el customer no tiene asosiadas las complaint");
        return result;
    }

    //Lista las complaint que no tiene ningun administrador (el paso previo para asignarselo)
    public Collection<Complaint> findComplaintNotAdministrator(){
    	Collection<Complaint> result;
        Administrator administrator;
        administrator = administratorService.getLoggedAdmin();
        Assert.notNull(administrator,"no hay un administrador logueado");
        result = complaintRepository.findComplaintNotAdministrator();
        return result;
    }

   public Collection<Complaint> findComplaintByAdministratorIdAndNotResolution(){
	   Collection<Complaint> result;
       Administrator administrator;
       administrator = administratorService.getLoggedAdmin();
       Assert.notNull(administrator, "Tiene que haber un administrador logueado");
        result = complaintRepository.findComplaintByAdministratorIdAndNotResolution(administrator.getId());


       return result;
   }


    //metodo que asigna una complaint sin administrador al administrador que esta logueado
    public void assignAdminToComplaint(Complaint complaint){

        Administrator administrator;
        administrator = administratorService.getLoggedAdmin();
        Assert.notNull(administrator,"no hay un administrador logueado");

        Assert.isTrue(complaint.getAdministrator()==null, "la complaint ya está asignada");

        complaint.setAdministrator(administrator);

        complaintRepository.save(complaint);
    }
    
    public boolean exists(Complaint complaint) {
		boolean result;
		result = complaintRepository.exists(complaint.getId());
		return result;
	}

}
