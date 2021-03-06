package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Booking;

@Component
@Transactional
public class BookingToStringConverter implements Converter<Booking, String>{ 
	
	@Override
	public String convert(Booking booking) {
		String result;
		
		if(booking == null){
			result = null;
		}else{
			result = String.valueOf(booking.getId());
		}
		return result;
		
	}

}
