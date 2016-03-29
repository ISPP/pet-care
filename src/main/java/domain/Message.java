package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity{
	//Constructor-------------------------------------------------
	public Message(){
		super();
	}
	//Attributes---------------------------------------------	
	private Date moment;
	private String subject;
	private String body;

	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return moment;
	}
	
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	@NotBlank
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@NotBlank
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
		
	//Relationships------------------------------------------	
	private Actor sender , recipient;
	private MessageFolder messageFolder;
	
	@ManyToOne(optional=false)
	public MessageFolder getMessageFolder() {
		return messageFolder;
	}

	public void setMessageFolder(MessageFolder messageFolder) {
		this.messageFolder = messageFolder;
	}
	
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Actor getSender() {
		return sender;
	}
	
	public void setSender(Actor sender) {
		this.sender = sender;
	}
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Actor getRecipient() {
		return recipient;
	}

	public void setRecipient(Actor recipient) {
		this.recipient = recipient;
	}
	//toString--------------------------------------
	@Override
	public String toString() {
		return "Message [moment=" + moment + ", subject=" + subject + ", body="
				+ body + "]";
	}

	
}