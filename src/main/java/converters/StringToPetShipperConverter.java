package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.PetShipperRepository;
import domain.PetShipper;

@Component
@Transactional
public class StringToPetShipperConverter implements Converter<String, PetShipper>{
	 @Autowired PetShipperRepository petShipperRepository;

	@Override
	public PetShipper convert(String text) {
		PetShipper result;
		int id;
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id=Integer.valueOf(text);
				result = petShipperRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
