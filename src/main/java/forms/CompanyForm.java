package forms;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

public class CompanyForm {

	private int id;
	// Actor
	private String name;
	private String surname;
	private String email;

	// CreditCard
	private String holderName;
	private String brandName;
	private int expirationMonth;
	private int expirationYear;
	private int cvvCode;
	private String number;

	// Customer
	private String address, description, homePage, contactPhone;
	private String invitationCode;

	// UserAccount
	private String username;
	private String password;

	// Extras
	private String passwordConfirm;
	
	//Company
	private String tic;
	private int daysBeforeCancel;
	private Double pricePerDay;
	
//	private boolean acceptTermsAndConditions;
//
//	public boolean getAcceptTermsAndConditions() {
//		return acceptTermsAndConditions;
//	}
//
//	public void setAcceptTermsAndConditions(boolean acceptTermsAndConditions) {
//		this.acceptTermsAndConditions = acceptTermsAndConditions;
//	}
	
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getTic() {
		return tic;
	}

	public void setTic(String tic) {
		this.tic = tic;
	}

	public Double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
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

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@Range(min = 1, max = 12)
	public int getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	@Min(2015)
	public int getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}

	@Range(min = 100, max = 999)
	public int getCvvCode() {
		return cvvCode;
	}

	public void setCvvCode(int cvvCode) {
		this.cvvCode = cvvCode;
	}

	@NotBlank
	@CreditCardNumber
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@URL
	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	@NotBlank
	@Pattern(regexp="^\\d+$")
	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	@Size(min = 5, max = 32)
	@Column(unique = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Size(min = 5)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Size(min = 5, max = 32)
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
	
	@Min(1)
	public Integer getDaysBeforeCancel() {
		return daysBeforeCancel;
	}

	public void setDaysBeforeCancel(Integer daysBeforeCancel) {
		this.daysBeforeCancel = daysBeforeCancel;
	}

	
}
