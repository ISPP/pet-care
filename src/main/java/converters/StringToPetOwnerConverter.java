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
public class StringToPetOwnerConverter implements Converter<String, PetOwner> {

	@Autowired
	PetOwnerRepository petOwnerRepository;

	@Override
	public PetOwner convert(String s) {
		PetOwner result;
		int id;
		try {
			if (s.isEmpty()) {
				result = null;
			} else {
				id = Integer.valueOf(s);
				result = petOwnerRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}

