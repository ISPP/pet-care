package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Vehicle;

@Component
@Transactional
public class VehicleToStringConverter implements Converter<Vehicle, String>{ 
	
	@Override
	public String convert(Vehicle vehicle) {
		String result;
		
		if(vehicle == null){
			result = null;
		}else{
			result = String.valueOf(vehicle.getId());
		}
		return result;
		
	}
}