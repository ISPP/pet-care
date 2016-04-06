package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.PhotoRepository;
import domain.Photo;

@Component
@Transactional
public class StringToPhotoConverter implements Converter<String, Photo>{
	 @Autowired PhotoRepository photoRepository;

	@Override
	public Photo convert(String text) {
		Photo result;
		int id;
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id=Integer.valueOf(text);
				result = photoRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
		
	}

}
