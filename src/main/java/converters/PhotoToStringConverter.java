package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Photo;

@Component
@Transactional
public class PhotoToStringConverter implements Converter<Photo, String>{ 
	
	@Override
	public String convert(Photo photo) {
		String result;
		
		if(photo == null){
			result = null;
		}else{
			result = String.valueOf(photo.getId());
		}
		return result;
		
	}

}
