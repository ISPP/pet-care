package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.TripRepository;
import domain.Trip;

@Component
@Transactional
public class StringToTripConverter implements Converter<String, Trip> {

	@Autowired
	TripRepository tripRepository;

	@Override
	public Trip convert(String s) {
		Trip result;
		int id;
		try {
			if (s.isEmpty()) {
				result = null;
			} else {
				id = Integer.valueOf(s);
				result = tripRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}

