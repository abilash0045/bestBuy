package pagesBestBuy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseBestBuy.BaseClassBB;

public class CheckOutPageBB extends BaseClassBB {
	
	@FindBy(id="fld-e")
	WebElement emailoSignInAtCheckOut;
	
	@FindBy(id="fld-p1")
	WebElement passToSignInAtCheckOut;
	
	@FindBy(xpath="//button[text()='Sign In']")
	WebElement submitSigInAtCheckOut;
	
	@FindBy(xpath="//button[text()='Continue as Guest']")
	WebElement continueAsGuest;
	
	@FindBy(id="user.emailAddress")
	WebElement emailAtCheckOut;
	
	@FindBy(id="user.phone")
	WebElement mobileNumAtCheckOut;
	
	@FindBy(xpath="//input[@id='text-updates']")
	WebElement textUpdateCheckBox;
	
	@FindBy(xpath="//span[text()='Continue to Payment Information']")
	WebElement continueToPayement;
	
	@FindBy(xpath="//input[@id='number']")
	WebElement debitCardNumber;
	
	@FindBy(id="expMonth")
	WebElement expMonth;
	
	@FindBy(id="expYear")
	WebElement expYear;
	
	@FindBy(id="cvv")
	WebElement cvv;
	
	@FindBy(id="save-to-profile")
	WebElement saveToProfile;
	
	@FindBy(id="first-name")
	WebElement firstName;
	
	@FindBy(id="last-name")
	WebElement lastName;
	
	@FindBy(xpath="//button[text()='Hide Suggestions']")
	WebElement hideSuggestion;
	
	@FindBy(id="address-input")
	WebElement address;
	
	@FindBy(id="//button[text()='Edit this address']")
	WebElement editThisAddress;
	
	@FindBy(id="//button[text()='Add a new address']")
	WebElement addNewAddress;
	
	@FindBy(id="city")
	WebElement city;
	
	@FindBy(id="state")
	WebElement state;
	
	@FindBy(id="postalCode")
	WebElement postalCode;
	
	@FindBy(xpath="//span[text()='Place Your Order']")
	WebElement placeYourOrder;
	
	@FindBy(xpath="//p[text()='Please enter a valid card number.']")
	WebElement inValidCardMsg;
	
	@FindBy(xpath="//p[text()='Please enter a valid card number.']")
	WebElement sryMsgAfterSignInB4CheckOut;
	
	@FindBy(xpath="//span[text()='Sorry, there was a problem. Please try that again.']")
	WebElement sryMsgWitOutSignInB4CheckOut;//when using continue sign up at checkout instead of login, also email and number given
	
	//p[text()='Please double-check the address you entered.']
	//button[text()='Keep Address as Entered']
	//span[contains(text(),'different card to continue')]
	//Please enter a valid card number.
	
	public CheckOutPageBB() {
		PageFactory.initElements(driver, this);
	}
	
	public void contactInfoAtCheckOut(String emailAtCheckOut,String mobileNumAtCheckOut) throws Exception {
		clickOn(continueAsGuest);
		type(this.emailAtCheckOut, emailAtCheckOut);
		type(this.mobileNumAtCheckOut, mobileNumAtCheckOut);
		clickOn(textUpdateCheckBox);
		clickOn(continueToPayement);
	}
	public void enterDetailsOnCheckOut(String debitCardNumber,String expMonthVisibleText,String expYearVisibleText,String cvv,String firstName,String lastName, 
			String address, String city, String stateVisibleText, String postalCode) throws Exception {
		type(this.debitCardNumber, debitCardNumber);
		selectFromDropDown(expMonth, expMonthVisibleText);
		selectFromDropDown(expYear, expYearVisibleText);
		type(this.cvv, cvv);
		type(this.firstName, firstName);
		type(this.lastName, lastName);
		type(this.address, address);
		type(this.city, city);
		selectFromDropDown(state, stateVisibleText);
		type(this.postalCode, postalCode);
		clickOn(placeYourOrder);
	}
	
	public void signInAtCheckOut() {
		type(emailoSignInAtCheckOut, "vino18493@gmail.com");
		type(passToSignInAtCheckOut, "testingtesting");
		clickOn(submitSigInAtCheckOut);
		type(this.emailAtCheckOut, "vino18493@gmail.com");
		type(this.mobileNumAtCheckOut, "9876543210");
		clickOn(textUpdateCheckBox);
		clickOn(continueToPayement);
	}
}
