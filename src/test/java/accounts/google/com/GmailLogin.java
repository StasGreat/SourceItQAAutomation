package accounts.google.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

/**
 * Created by Stanislav on 20.04.2015.
 */
public class GmailLogin {
    WebDriver driver = new FirefoxDriver();
    @Test
    public void login(){
        driver.manage().window().maximize();
        driver.get("https://accounts.google.com");
        WebElement email = driver.findElement(By.cssSelector("#Email"));
        email.sendKeys("test.gmail@gmail.com");
        WebElement password = driver.findElement(By.cssSelector("#Passwd"));
        password.sendKeys("123456");
        WebElement signIn = driver.findElement(By.cssSelector("#signIn"));
        signIn.click();


        driver.quit();
    }

}
