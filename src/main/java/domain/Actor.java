package domain;

import java.util.Collection;

import javax.persistence.Entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import javax.persistence.CascadeType;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class Actor extends DomainEntity {
	private String name;
	private String surname;
	private String email;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// Relationships------------------------------------------
	private Collection<MessageFolder> messageFolders;
	private UserAccount user;
	private Collection<Comment> comments;

	@OneToMany(mappedBy = "owner")
	public Collection<MessageFolder> getMessageFolders() {
		return messageFolders;
	}

	public void setMessageFolders(Collection<MessageFolder> messageFolders) {
		this.messageFolders = messageFolders;
	}

	@Valid
	@NotNull
	@OneToOne(optional = false, cascade = CascadeType.ALL)
	public UserAccount getUser() {
		return user;
	}

	public void setUser(UserAccount user) {
		this.user = user;
	}
	@OneToMany(mappedBy="actor")
	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	// toString--------------------------------------------------------------
	/*
	 * @Override public String toString() { return "Actor [name=" + name +
	 * ", surname=" + surname + ", email=" + email + "]"; }
	 */

}
