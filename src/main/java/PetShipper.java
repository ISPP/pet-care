import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import domain.Supplier;



@Entity
@Access(AccessType.PROPERTY)
public class PetShipper extends Supplier{
	public PetShipper(){
		super();
	}

}
