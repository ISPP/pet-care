package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.PetOwnerRepository;
import domain.PetOwner;

@Component
@Transactional
public class StringToPetOwnerConverter implements Converter<String, PetOwner>{
	 @Autowired PetOwnerRepository petOwnerRepository;

	@Override
	public PetOwner convert(String text) {
		PetOwner result;
		int id;
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id=Integer.valueOf(text);
				result = petOwnerRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
