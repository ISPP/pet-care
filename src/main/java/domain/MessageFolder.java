package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class MessageFolder extends DomainEntity {
	// Constructor-------------------------------------------------
	public MessageFolder() {
		messages = new HashSet<Message>();
	}

	// Attributes---------------------------------------------
	private String name;
	

	
	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	// Relationships------------------------------------------
	private Collection<Message> messages;
	private Actor owner;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Actor getOwner() {
		return owner;
	}

	public void setOwner(Actor owner) {
		this.owner = owner;
	}

	@NotNull
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "messageFolder")
	public Collection<Message> getMessages() {
		return messages;
	}

	public void setMessages(Collection<Message> messages) {
		this.messages = messages;
	}

	// toString--------------------------------------
	@Override
	public String toString() {
		return "MessageFolder [name=" + name + ", messages=" + messages
				+ "]";
	}
}