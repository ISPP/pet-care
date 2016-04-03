package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.PetSitterRepository;
import domain.PetSitter;

@Component
@Transactional
public class StringToPetSitterConverter implements Converter<String, PetSitter> {

	@Autowired
	PetSitterRepository petSitterRepository;

	@Override
	public PetSitter convert(String s) {
		PetSitter result;
		int id;
		try {
			if (s.isEmpty()) {
				result = null;
			} else {
				id = Integer.valueOf(s);
				result = petSitterRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}

