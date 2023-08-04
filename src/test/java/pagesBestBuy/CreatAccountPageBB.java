package pagesBestBuy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseBestBuy.BaseClassBB;

public class CreatAccountPageBB extends BaseClassBB {
	
	@FindBy(id="firstName")
	WebElement firstName;
	
	@FindBy(id="lastName")
	WebElement lastName;
	
	@FindBy(id="email")
	WebElement email;
	
	@FindBy(id="fld-p1")
	WebElement password;
	
	@FindBy(id="reenterPassword")
	WebElement confirmPassword;
	
	@FindBy(id="phone")
	WebElement mobileNumber;
	
	@FindBy(id="is-recovery-phone")
	WebElement recoveryCheckBox;
	
	@FindBy(xpath="//button[text()='Create an Account']")
	WebElement creatAnAccount;
	
	@FindBy(id="//*[text()='Sign Up with Google']")
	WebElement googleSignUp;
	
	public CreatAccountPageBB() {
		PageFactory.initElements(driver, this);
	}
	
	public String creatAccountFunctionality(String fName, String lName, String email,
			String password, String confirmPassword,String mobileNumber, String textXpath) {
		type(firstName, fName);
		type(lastName, lName);
		type(this.email, email);
		type(this.password, password);
		type(this.confirmPassword, confirmPassword);
		type(this.mobileNumber, mobileNumber);
		clickOn(creatAnAccount);
		String actMsg=driver.findElement(By.xpath(""+textXpath+"")).getText();
		return actMsg;
	}
}
