package e.mail.ru;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.fail;

/**
 * Created by Stanislav on 22.04.2015.
 */
public class NewMessagesTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeTest
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://e.mail.ru/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void newMessages() throws Exception {
        driver.get(baseUrl + "/messages/inbox/?rf=e.mail.ru");
        driver.findElement(By.cssSelector("span.b-toolbar__btn__text.b-toolbar__btn__text_pad")).click();
        driver.findElement(By.cssSelector("textarea.js-input.compose__labels__input")).click();
        driver.findElement(By.cssSelector("textarea.js-input.compose__labels__input")).clear();
        driver.findElement(By.cssSelector("textarea.js-input.compose__labels__input")).sendKeys("qa-t@mail.ua");
        driver.findElement(By.id("compose_397_ab_compose_subj")).click();
        driver.findElement(By.id("compose_397_ab_compose_subj")).clear();
        driver.findElement(By.id("compose_397_ab_compose_subj")).sendKeys("Hello");
        driver.findElement(By.xpath("//div[@id='b-toolbar__right']/div[3]/div/div[2]/div/div/span")).click();
        driver.findElement(By.xpath("//div[@id='b-nav_folders']/div")).click();
        driver.findElement(By.cssSelector("span.b-nav__item__text.b-nav__item__text_unread")).click();
    }

    @AfterTest
    public void tearDown() throws Exception {
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