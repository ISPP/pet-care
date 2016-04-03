package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.PetSitterRepository;
import domain.PetSitter;

@Component
@Transactional
public class StringToPetSitterConverter implements Converter<String, PetSitter>{
	 @Autowired PetSitterRepository petSitterRepository;

	@Override
	public PetSitter convert(String text) {
		PetSitter result;
		int id;
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id=Integer.valueOf(text);
				result = petSitterRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
		
	}

}
