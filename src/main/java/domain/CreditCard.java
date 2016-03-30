package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;


@Access(AccessType.PROPERTY)
@Embeddable
public class CreditCard {
	//Constructor-------------------------------------------------
			public CreditCard(){
				super();
			}
			
			//Attributes---------------------------------------------
			private String holderName, brandName, number;
			private int expirationMonth, expirationYear, CVV;

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
			@NotBlank
			@CreditCardNumber
			@SafeHtml(whitelistType = WhiteListType.NONE)
			public String getNumber() {
				return number;
			}

			public void setNumber(String number) {
				this.number = number;
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
			public int getCVV() {
				return CVV;
			}

			public void setCVV(int cVV) {
				CVV = cVV;
			}
			
			
			//toString---------------------------------------
			@Override
			public String toString() {
				return "CreditCard [holderName=" + holderName + ", brandName="
						+ brandName + ", number=" + number + ", expirationMonth="
						+ expirationMonth + ", expirationYear=" + expirationYear
						+ ", CVV=" + CVV + "]";
			}
}
