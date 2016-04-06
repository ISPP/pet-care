package forms;

import javax.persistence.Lob;

public class PhotoForm {

	private int petId;
	private int id;
	private byte[] content;
	
	@Lob
	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}	

	
}