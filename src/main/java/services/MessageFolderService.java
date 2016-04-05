package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.MessageFolder;

import repositories.MessageFolderRepository;


@Service
@Transactional
public class MessageFolderService {
	
	public MessageFolderService(){
		super();
	}
	
	@Autowired
	private MessageFolderRepository messageFolderRepository;
	
	public MessageFolder create() {
		MessageFolder result;
		result = new MessageFolder();
		return result;
	}

	public MessageFolder save(MessageFolder messageFolder) {
		MessageFolder result;
		result = messageFolderRepository.saveAndFlush(messageFolder);
		return result;
	}

	public void delete(MessageFolder messageFolder) {
		messageFolderRepository.delete(messageFolder);
	}

	public Collection<MessageFolder> findAll() {
		Collection<MessageFolder> result;
		result = messageFolderRepository.findAll();
		return result;
	}

	public MessageFolder findOne(Integer id) {
		MessageFolder result;
		result = messageFolderRepository.findOne(id);
		return result;
	}

}
