
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.ActorRepository;
import repositories.SupplierRepository;
import domain.Actor;
import domain.Supplier;

@Component
@Transactional
public class StringToSupplierConverter implements Converter<String, Supplier> {

	@Autowired
	SupplierRepository supplierRepository;

	@Override
	public Supplier convert(String s) {
		Supplier result;
		int id;
		try {
			if (s.isEmpty()) {
				result = null;
			} else {
				id = Integer.valueOf(s);
				result = supplierRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
