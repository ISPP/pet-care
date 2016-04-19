package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.PetShipper;

@Component
@Transactional
public class PetShipperToStringConverter implements Converter<PetShipper, String>{ 
	
	@Override
	public String convert(PetShipper petShipper) {
		String result;
		
		if(petShipper == null){
			result = null;
		}else{
			result = String.valueOf(petShipper.getId());
		}
		return result;
		
	}
}
