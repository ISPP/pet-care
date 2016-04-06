package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Supplier;

@Component
@Transactional
public class SupplierToStringConverter implements Converter<Supplier, String>{ 
	
	@Override
	public String convert(Supplier supplier) {
		String result;
		
		if(supplier == null){
			result = null;
		}else{
			result = String.valueOf(supplier.getId());
		}
		return result;
		
	}

}
