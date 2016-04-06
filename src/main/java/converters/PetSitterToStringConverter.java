package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.PetSitter;

@Component
@Transactional
public class PetSitterToStringConverter implements Converter<PetSitter, String>{ 
	
	@Override
	public String convert(PetSitter petSitter) {
		String result;
		
		if(petSitter == null){
			result = null;
		}else{
			result = String.valueOf(petSitter.getId());
		}
		return result;
		
	}
}
