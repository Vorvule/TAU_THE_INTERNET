package base;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import utils.EventReporter;
import utils.WindowManager;

import java.io.File;
import java.io.IOException;

public class BaseTestsNew {
    private EventFiringWebDriver driver;

    private String link = "http://the-internet.herokuapp.com/";
    protected HomePage homePageNew;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new EventReporter());


        driver.manage().window().maximize();
        goHome();
        homePageNew = new HomePage(driver);
        // System.out.println("Page title is " + driver.getTitle());
    }

    @BeforeMethod
    public void goHome() {
        driver.get(link);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public WindowManager getWindowManager() {
        return new WindowManager(driver);
    }

    public void takeScreenshot(ITestResult result) throws IOException {
        TakesScreenshot camera = (TakesScreenshot) driver;
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        // System.out.println(screenshot.getAbsolutePath());

        com.google.common.io.Files.move
                (screenshot, new File("src/test/resources/screenshots/screenshot_" + result.getName() + ".png"));
    }

    @AfterMethod
    public void recordFailure(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result);
        }
    }





}







        /*
        WebElement inputsLink = driver.findElement(By.linkText("Inputs"));
        String href = inputsLink.getAttribute("href");
        System.out.println(href);

        WebElement inputsLink = driver.findElement(By.linkText("Inputs"));
        inputsLink.click();
        driver.navigate().back();

        // homework task 1
        WebElement shiftingContentLink = driver.findElement(By.linkText("Shifting Content"));
        shiftingContentLink.click();

        WebElement example1Link = driver.findElement(By.linkText("Example 1: Menu Element"));
        example1Link.click();

        List<WebElement> menuItems = driver.findElements(By.tagName("li"));
        System.out.println("Menu items quantity is " + menuItems.size());

        */
