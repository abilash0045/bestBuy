package pagesBestBuy;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseBestBuy.BaseClassBB;

public class HomePageBB extends BaseClassBB {

	@FindBy(xpath = "//a[text()='Top Deals']")
	WebElement topDealsMenu;

	@FindBy(xpath = "//a[text()='Deal of the Day']")
	WebElement dealsOfTheDayMenu;

	@FindBy(xpath = "//a[text()='Totaltech Membership']")
	WebElement totaltechMembershipMenu;

	@FindBy(xpath = "//span[text()='More']")
	WebElement moreMenu;

	@FindBy(xpath = "//li[@class='liDropdownList']//a[text()='Credit Cards']")
	WebElement creditCardsMenu;

	@FindBy(xpath = "//li[@class='liDropdownList']//a[text()='Gift Cards']")
	WebElement giftCardsMenu;

	@FindBy(xpath = "//li[@class='liDropdownList']//a[text()='Gift Ideas']")
	WebElement giftIdeasMenu;

	@FindBy(xpath = "//li[@class='liDropdownList']//a[text()='Health & Wellness']")
	WebElement healthWellnessMenu;

	@FindBy(xpath = "//li[@class='liDropdownList']//a[text()='Best Buy Outlet']")
	WebElement bestBuyOutletMenu;

	@FindBy(xpath = "//li[@class='liDropdownList']//a[text()='Best Buy Business']")
	WebElement bestBuyBusinessMenu;

	@FindBy(xpath = "//span[text()='Account']")
	WebElement account;

	@FindBy(xpath = "//a[text()='Sign In']")
	WebElement signIn;

	@FindBy(xpath = "//a[text()='Create Account']")
	WebElement createAccount;

	@FindBy(xpath = "//button[@aria-label='Menu']")
	WebElement menuButton;

	@FindBy(xpath = "//button[@data-lid='ubr_tv']")
	WebElement tvInMenu;

	@FindBy(xpath = "//button[text()='TVs by Brand']")
	WebElement tvsByBrandInnerMenu;

	@FindBy(xpath = "//a[text()='Sony TVs']")
	WebElement sonyTvsInnerMenu2;

	@FindBy(id = "gh-search-input")
	WebElement searchBox;

	@FindBy(xpath = "//button[@title='submit search']")
	WebElement searchButton;

	@FindBy(xpath = "//span[text()='Cart']")
	WebElement cartButton;

	@FindBy(xpath = "//button[text()='Brands']")
	WebElement brandsInMenu;

	@FindBy(xpath = "//a[text()='Lenovo']")
	WebElement lenovoInBrandsMenu;
	
	@FindBy(xpath="//a[text()='Terms & Conditions']")
	WebElement termsAndCondLink;

	public HomePageBB() {
		PageFactory.initElements(driver, this);
	}

	public void goToCreatAccount() {
		clickOn(account);
		clickOn(createAccount);
	}

	public void goToSignIn() {
		clickOn(account);
		clickOn(signIn);
	}

	public void openMenu() {
		clickOn(menuButton);
	}

	public List<String> brokenLinkFinder() throws Exception {
		List<WebElement> elements = driver.findElements(By.xpath("//footer//a"));
		int brokenLinkCount = 0;
		List<String> brokenLinks = new ArrayList<String>();
		for (int i = 0; i < elements.size(); i++) {
			String links = elements.get(i).getAttribute("href");
			int responseCode = getResponseCode(links);
			if (responseCode >= 400) {
				brokenLinkCount++;
				brokenLinks.add(links);
			}
		}
		System.out.println(brokenLinkCount);
		return brokenLinks;
	}

	public void searchProduct(String productName) {
		type(searchBox, productName);
		clickOn(searchButton);
	}

	public void selectTvMenuInHomePage() {
		clickOn(menuButton);
		clickOn(tvInMenu);
		clickOn(tvsByBrandInnerMenu);
		clickOn(sonyTvsInnerMenu2);
	}

	public void goToCartPage() {
		jsClickOn(cartButton);
	}

	public void selectLenovoBrand() {
		clickOn(menuButton);
		clickOn(brandsInMenu);
		clickOn(lenovoInBrandsMenu);
	}

	public String menuValidation(String menuName) {
		if (menuName.equalsIgnoreCase("Best Buy Business")) {
			clickOn(moreMenu);
			clickOn(driver.findElement(By.xpath("//li[@class='liDropdownList']//a[text()='" + menuName + "']")));
			String actTitle = getPageTitle();
			return actTitle;
		} else {
			clickOn(driver.findElement(By.xpath("//a[text()='" + menuName + "']")));
			String actTitle = getPageTitle();
			return actTitle;
		}
		
	}
	
	public String validateTermsAndCondtionLink() {
		jsScrollUntillElement(termsAndCondLink);
		clickOn(termsAndCondLink);
		String actTitle = getPageTitle();
		return actTitle;
	}
}
