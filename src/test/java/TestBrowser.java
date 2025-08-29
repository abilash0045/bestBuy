import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBrowser {
    public static void main(String[] args) {
        // Force WebDriverManager to resolve ChromeDriver correctly
        WebDriverManager.chromedriver()
                .clearResolutionCache()
                .forceDownload()
                .setup();

        // Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");

        // Start browser
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.google.com");

        // Print title for verification
        System.out.println("Page title is: " + driver.getTitle());

        // Quit
        driver.quit();
    }
}
