package baseBestBuy;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utilsBestBuy.UtilsBB;

public class BaseClassBB extends UtilsBB {

    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static WebDriverWait wait;
    public static SoftAssert softAssert;

    /**
     * This will run once before the suite starts - Only for ExtentReports setup
     */
    @BeforeSuite
    public void setUpSuite() {
        // Initialize ExtentReports
        initExtentReports();
    }

    /**
     * This will run before each test method - WebDriver initialization
     */
    @BeforeMethod
    public void setUp() {
        // Initialize WebDriver only if not already initialized
        if (driver == null) {
            initialization();
        }
        // Initialize SoftAssert for each test
        softAssert = new SoftAssert();
        // Create test in ExtentReports only if testName is set
        if (testName != null && !testName.isEmpty()) {
            test = extent.createTest(testName, testDescription)
                    .assignCategory(testCategory)
                    .assignAuthor(testAuthor);
        }
    }

    /**
     * This will run after each test method - WebDriver cleanup
     */
    @AfterMethod
    public void tearDown() {
        try {
            // Assert all soft assertions
            if (softAssert != null) {
                softAssert.assertAll();
            }
        } catch (Exception e) {
            System.err.println("Error in soft assertions: " + e.getMessage());
        }
        
        // Quit WebDriver after each test
        if (driver != null) {
            try {
                System.out.println("Closing WebDriver...");
                driver.quit();
                System.out.println("WebDriver closed successfully");
            } catch (Exception e) {
                System.err.println("Error closing WebDriver: " + e.getMessage());
            } finally {
                driver = null;
                UtilsBB.driver = null; // Also clear the UtilsBB static driver
            }
        }
    }

    /**
     * This will run once after the suite ends
     */
    @AfterSuite
    public void tearDownSuite() {
        // Flush ExtentReports
        if (extent != null) {
            extent.flush();
        }
        
        // Force cleanup any remaining WebDriver instances
        if (driver != null) {
            try {
                System.out.println("Force closing WebDriver in tearDownSuite...");
                driver.quit();
            } catch (Exception e) {
                System.err.println("Error force closing WebDriver: " + e.getMessage());
            }
        }
        
        // Clear static driver references
        driver = null;
        UtilsBB.driver = null;
        
        System.out.println("Suite teardown completed");
    }

    /**
     * Initialize WebDriver with WebDriverManager
     */
    public static void initialization() {
        try {
            System.out.println("Starting WebDriver initialization...");
            
            // Setup WebDriverManager
            WebDriverManager.chromedriver().setup();
            System.out.println("ChromeDriver setup completed");

            // Simple Chrome options
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-notifications");
            
            // Check if running in Jenkins or CI environment
            String jenkinsUrl = System.getenv("JENKINS_URL");
            String ci = System.getenv("CI");
            if (jenkinsUrl != null || "true".equals(ci)) {
                System.out.println("Running in CI environment - enabling headless mode");
                options.addArguments("--headless");
            }
            
            System.out.println("Creating ChromeDriver instance...");
            driver = new ChromeDriver(options);
            System.out.println("ChromeDriver created successfully");
            
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            driver.manage().window().maximize();
            
            // Initialize WebDriverWait with longer timeout
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            
            // Set the UtilsBB static driver to the same instance
            UtilsBB.driver = driver;
            System.out.println("Driver instances synchronized");

            System.out.println("Navigating to Best Buy...");
            driver.get("https://www.bestbuy.com/");
            System.out.println("Navigation completed");
            
            // Wait for page to load completely
            		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
			System.out.println("Page loaded successfully");
			
			// Handle survey modal if it appears
			try {
				WebElement surveyModal = driver.findElement(By.id("survey_window"));
				if (surveyModal.isDisplayed()) {
					System.out.println("Survey modal detected, closing it...");
					// Try to find and click the close button
					try {
						WebElement closeButton = driver.findElement(By.cssSelector("#survey_window .close, #survey_window [aria-label='Close'], #survey_window .modal-close"));
						closeButton.click();
						System.out.println("Survey modal closed successfully");
					} catch (Exception closeEx) {
						// If no close button found, try pressing Escape
						driver.findElement(By.tagName("body")).sendKeys(org.openqa.selenium.Keys.ESCAPE);
						System.out.println("Survey modal closed using Escape key");
					}
					Thread.sleep(1000); // Wait for modal to close
				}
			} catch (Exception surveyEx) {
				// No survey modal found, continue normally
				System.out.println("No survey modal detected");
			}
			
		} catch (Exception e) {
			System.err.println("Page load timeout, but continuing: " + e.getMessage());
		}
            
            // Add a small wait to ensure page loads
            Thread.sleep(3000);
            
            // Handle country selection if needed
            handleCountrySelection();
            
            System.out.println("WebDriver initialization completed successfully");
            
        } catch (Exception e) {
            System.err.println("Failed to initialize WebDriver: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("WebDriver initialization failed", e);
        }
    }

    /**
     * Initialize ExtentReports with Spark Reporter
     */
    public static void initExtentReports() {
        try {
            String reportPath = System.getProperty("user.dir") + "/reports/result.html";
            File reportDir = new File(System.getProperty("user.dir") + "/reports");
            if (!reportDir.exists()) {
                reportDir.mkdirs();
            }

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("BestBuy Automation Report");
            spark.config().setReportName("BestBuy Test Suite");
            spark.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Selenium Version", "4.11.0");
            
        } catch (Exception e) {
            System.err.println("Failed to initialize ExtentReports: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Utility method to click on an element
     */
    public void clickOn(WebElement element) {
        try {
            // Wait for element to be clickable
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            System.err.println("Failed to click element: " + e.getMessage());
            // Try JavaScript click as fallback
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } catch (Exception jsException) {
                System.err.println("JavaScript click also failed: " + jsException.getMessage());
                throw e;
            }
        }
    }

    /**
     * Utility method to extract text from an element
     */
    public String extractText(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.getText().trim();
        } catch (Exception e) {
            System.err.println("Failed to extract text: " + e.getMessage());
            // Try to get text even if element is not visible
            try {
                return element.getText().trim();
            } catch (Exception textException) {
                System.err.println("Failed to get text even without visibility check: " + textException.getMessage());
                return "";
            }
        }
    }

    /**
     * Soft assertion method for comparing actual and expected results
     */
    public void softAssert(String actualResult, String expectedResult) {
        if (softAssert != null) {
            softAssert.assertEquals(actualResult, expectedResult);
        }
    }

    /**
     * Report step method for ExtentReports
     */
    public void reportStep(String stepDetails, String stepStatus, String testName) throws Exception {
        try {
            int ranNum = screenShot(testName);
            String projectPath = System.getProperty("user.dir");
            if (stepStatus.equalsIgnoreCase("pass")) {
                test.pass(stepDetails,
                        MediaEntityBuilder.createScreenCaptureFromPath(projectPath + "/Screenshot/" + testName + ranNum + ".png").build());
            } else if (stepStatus.equalsIgnoreCase("fail")) {
                test.fail(stepDetails,
                        MediaEntityBuilder.createScreenCaptureFromPath(projectPath + "/Screenshot/" + testName + ranNum + ".png").build());
                throw new RuntimeException("See extent report for more details");
            }
        } catch (Exception e) {
            System.err.println("Failed to report step: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Handle country selection if the international page appears
     */
    private static void handleCountrySelection() {
        try {
            // Check if we're on the country selection page
            String currentTitle = driver.getTitle();
            if (currentTitle.contains("Select your Country") || currentTitle.contains("International")) {
                System.out.println("Country selection page detected, selecting United States...");
                
                // Try multiple approaches to find and click the United States link
                boolean countrySelected = false;
                
                // Approach 1: Find by link text
                try {
                    List<WebElement> usLinks = driver.findElements(By.linkText("United States"));
                    if (!usLinks.isEmpty()) {
                        usLinks.get(0).click();
                        countrySelected = true;
                        System.out.println("United States selected via link text");
                    }
                } catch (Exception e) {
                    System.out.println("Link text approach failed: " + e.getMessage());
                }
                
                // Approach 2: Find by class name
                if (!countrySelected) {
                    try {
                        List<WebElement> usClassLinks = driver.findElements(By.className("us-link"));
                        if (!usClassLinks.isEmpty()) {
                            usClassLinks.get(0).click();
                            countrySelected = true;
                            System.out.println("United States selected via class name");
                        }
                    } catch (Exception e) {
                        System.out.println("Class name approach failed: " + e.getMessage());
                    }
                }
                
                // Approach 3: JavaScript click
                if (!countrySelected) {
                    try {
                        WebElement usLink = driver.findElement(By.linkText("United States"));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", usLink);
                        countrySelected = true;
                        System.out.println("United States selected via JavaScript");
                    } catch (Exception e) {
                        System.out.println("JavaScript approach failed: " + e.getMessage());
                    }
                }
                
                if (countrySelected) {
                    Thread.sleep(3000); // Wait for navigation
                    System.out.println("Country selection completed");
                } else {
                    System.out.println("Could not select country, continuing anyway");
                }
            } else {
                System.out.println("No country selection needed");
            }
        } catch (Exception e) {
            System.err.println("Error handling country selection: " + e.getMessage());
        }
    }

    /**
     * Data provider method for reading test data from Excel
     */
    @DataProvider(name = "getFromExcel")
    public Object[][] getFromExcel() throws Exception {
        return dataReader(sheetName);
    }
}