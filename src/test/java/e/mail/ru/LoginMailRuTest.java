package e.mail.ru;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.fail;

/**
 * Created by Stanislav on 22.04.2015.
 */
public class LoginMailRuTest{
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeTest
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://mail.ru/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public  void testLoginMailRu() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("mailbox__login")).clear();
        driver.findElement(By.id("mailbox__login")).sendKeys("qa-t");
        new Select(driver.findElement(By.id("mailbox__login__domain"))).selectByVisibleText("@mail.ua");
        driver.findElement(By.id("mailbox__password")).clear();
        driver.findElement(By.id("mailbox__password")).sendKeys("test123456");
        driver.findElement(By.id("mailbox__auth__button")).click();

        // Log Out
        driver.findElement(By.cssSelector("span.b-nav__item__text.b-nav__item__text_unread")).click();
        driver.findElement(By.id("PH_logoutLink")).click();
    }

    @AfterTest
    public void tearDown ()throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
