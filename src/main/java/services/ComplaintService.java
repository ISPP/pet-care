package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ComplaintRepository;
import domain.Complaint;
import forms.ComplaintForm;


@Service
@Transactional
public class ComplaintService {
	
	public ComplaintService(){
		super();
	}
	
	@Autowired
	private ComplaintRepository complaintRepository;
	
	
	public Complaint create() {
		Complaint result;
		result = new Complaint();
		return result;
	}

	public Complaint save(Complaint complaint) {
		Complaint result;
		result = complaintRepository.saveAndFlush(complaint);
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

}
