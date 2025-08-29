package pagesBestBuy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

import baseBestBuy.BaseClassBB;
import utilsBestBuy.UtilsBB;

public class LocationPageBB extends BaseClassBB {
	
	@FindBy(className="us-link")
	WebElement countryUSA;
	
	@FindBy(className="canada-link")
	WebElement countryCanada;
	
	public LocationPageBB() {
		PageFactory.initElements(UtilsBB.driver, this);
	}
	
	public void deliveryAtUSA() {
		try {
			// Wait for page to load completely
			Thread.sleep(3000);
			
			// Try to find the United States link by text first (more reliable)
			List<WebElement> usLinks = UtilsBB.driver.findElements(By.linkText("United States"));
			if (!usLinks.isEmpty()) {
				System.out.println("Found United States link by text, clicking...");
				clickOn(usLinks.get(0));
				return;
			}
			
			// Fallback to class name selector
			System.out.println("Trying to click US link by class name...");
			WebDriverWait wait = new WebDriverWait(UtilsBB.driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(countryUSA));
			clickOn(countryUSA);
			
		} catch (InterruptedException ie) {
			System.err.println("Thread interrupted while waiting: " + ie.getMessage());
			Thread.currentThread().interrupt();
		} catch (Exception e) {
			System.err.println("Failed to click United States link: " + e.getMessage());
			// Try JavaScript click as last resort
			try {
				WebElement usLink = UtilsBB.driver.findElement(By.linkText("United States"));
				((org.openqa.selenium.JavascriptExecutor) UtilsBB.driver).executeScript("arguments[0].click();", usLink);
				System.out.println("Successfully clicked United States link via JavaScript");
			} catch (Exception jsException) {
				System.err.println("JavaScript click also failed: " + jsException.getMessage());
				throw e;
			}
		}
	}
	
	public void deliveryAtCanada() {
		try {
			// Wait for page to load completely
			Thread.sleep(3000);
			
			// Try to find the Canada link by text first (more reliable)
			List<WebElement> canadaLinks = UtilsBB.driver.findElements(By.linkText("Canada"));
			if (!canadaLinks.isEmpty()) {
				System.out.println("Found Canada link by text, clicking...");
				clickOn(canadaLinks.get(0));
				return;
			}
			
			// Fallback to class name selector
			System.out.println("Trying to click Canada link by class name...");
			WebDriverWait wait = new WebDriverWait(UtilsBB.driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(countryCanada));
			clickOn(countryCanada);
			
		} catch (InterruptedException ie) {
			System.err.println("Thread interrupted while waiting: " + ie.getMessage());
			Thread.currentThread().interrupt();
		} catch (Exception e) {
			System.err.println("Failed to click Canada link: " + e.getMessage());
			// Try JavaScript click as last resort
			try {
				WebElement canadaLink = UtilsBB.driver.findElement(By.linkText("Canada"));
				((org.openqa.selenium.JavascriptExecutor) UtilsBB.driver).executeScript("arguments[0].click();", canadaLink);
				System.out.println("Successfully clicked Canada link via JavaScript");
			} catch (Exception jsException) {
				System.err.println("JavaScript click also failed: " + jsException.getMessage());
				throw e;
			}
		}
	}
	
	public String bestBuyPageTitle() throws Exception {
		return getPageTitle();
	}
	
	public int urlResponseCode() throws Exception {
		return getResponseCode(readProperty("url"));
	}
}
