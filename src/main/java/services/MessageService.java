package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.MessageRepository;
import domain.Message;


@Service
@Transactional
public class MessageService {
	
	public MessageService(){
		super();
	}
	
	@Autowired
	private MessageRepository messageRepository;
	
	
	public Message create() {
		Message result;
		result = new Message();
		return result;
	}

	public Message save(Message message) {
		Message result;
		result = messageRepository.saveAndFlush(message);
		return result;
	}

	public void delete(Message message) {
		messageRepository.delete(message);
	}

	public Collection<Message> findAll() {
		Collection<Message> result;
		result = messageRepository.findAll();
		return result;
	}

	public Message findOne(Integer id) {
		Message result;
		result = messageRepository.findOne(id);
		return result;
	}


}
