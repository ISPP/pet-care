package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.VehicleRepository;
import domain.Vehicle;

@Component
@Transactional
public class StringToVehicleConverter implements Converter<String, Vehicle>{
	 @Autowired VehicleRepository vehicleRepository;

	@Override
	public Vehicle convert(String text) {
		Vehicle result;
		int id;
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id=Integer.valueOf(text);
				result = vehicleRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
		
	}

}
