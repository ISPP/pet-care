package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.PetOwner;

@Component
@Transactional
public class PetOwnerToStringConverter implements Converter<PetOwner, String>{ 
	
	@Override
	public String convert(PetOwner petOwner) {
		String result;
		
		if(petOwner == null){
			result = null;
		}else{
			result = String.valueOf(petOwner.getId());
		}
		return result;
		
	}
}