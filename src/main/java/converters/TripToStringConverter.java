package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Trip;

@Component
@Transactional
public class TripToStringConverter implements Converter<Trip, String> {

	@Override
	public String convert(Trip a) {
		String result;
		if (a == null) {
			result = null;
		} else {
			result = String.valueOf(a.getId());
		}
		return result;
	
	}


}



