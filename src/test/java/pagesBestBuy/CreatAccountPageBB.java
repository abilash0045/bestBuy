package pagesBestBuy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseBestBuy.BaseClassBB;
import utilsBestBuy.UtilsBB;

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
	
	@FindBy(xpath="//*[text()='Sign Up with Google']")
	WebElement googleSignUp;
	
	public CreatAccountPageBB() {
		PageFactory.initElements(UtilsBB.driver, this);
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
		
		// Wait a bit for the page to respond
		try {
			Thread.sleep(3000);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
		
		// Try to find the expected error message, but handle case where it doesn't exist
		try {
			String actMsg = driver.findElement(By.xpath(""+textXpath+"")).getText();
			return actMsg;
		} catch (Exception e) {
			// If the error message is not found, check if account creation was successful
			try {
				// Look for success indicators
				if (driver.getCurrentUrl().contains("account") || 
					driver.getPageSource().toLowerCase().contains("welcome") ||
					driver.getPageSource().toLowerCase().contains("success")) {
					return "Account created successfully";
				}
			} catch (Exception ex) {
				// If we can't determine success, return a generic message
				return "Account creation completed - no error message found";
			}
			return "Account creation completed - no error message found";
		}
	}
}
