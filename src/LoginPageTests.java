package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTests extends tests.TestBase {

    @BeforeMethod
    public void initTests() {

        //--- Login to the system ----
        WebElement loginIcon = driver.findElement(By.id("idsignin"));
        loginIcon.click();
        //Thread.sleep(2000);
        waitUntilElementIsClickable(By.id("signinrequest"),20);
    }

    @Test
    public void loginScreenVerificationTest(){

        Assert.assertTrue(driver
                        .findElement(By.id("clickreg")).getText().contains("registration"),
                "It is not login screen or there is no 'registration' on login screen");
    }

    @Test
    public void loginNegativeTest() {

        //---- Enter incorrect login/psw ---
        driver.findElement(By.id("logininput")).sendKeys(LOGIN);
        driver.findElement(By.id("passwordinput")).sendKeys("123");
        WebElement signInButton = driver.findElement(By.id("signinrequest"));
        signInButton.click();
        //Thread.sleep(3000);
        waitUntilElementIsVisible(By.id("wrongloginorpassword"),10);

        //--- Error message 'wrong authorization is displayed' ----
        System.out.println("The system displays an error message: " + driver
                .findElement(By.id("wrongloginorpassword")).getText()
                .contains("Wrong Authorization"));

        //--- Close login window ---
        driver.findElement(By.id("closedsignin")).click();
        //Thread.sleep(3000);
        waitUntilElementIsClickable(By.id("idsignin"),20);
        waitUntilElementIsVisible(By
                .xpath("//span[@id='idtitletypesearchevents']"),20);
        // ---- Usr is on the HomePage for the unauthorized user
        System.out.println("User is on the HomePage unauthorized: " + driver
                .findElement(By.id("idsignin")).getText().equals("Login"));
        Assert.assertEquals(driver
                        .findElement(By.id("idsignin")).getText(),"Login",
                "Name of the login button is not 'Login'");
    }

    @Test
    public void loginExitByCancelTest()  {
        //--- Close login window ---
        driver.findElement(By.id("closedsignin")).click();
        //Thread.sleep(3000);
        waitUntilElementIsClickable(By.id("idsignin"),20);
        waitUntilElementIsVisible(By
                .xpath("//span[@id='idtitletypesearchevents']"),20);

        // ---- Usr is on the HomePage for the unauthorized user
        Assert.assertEquals(driver
                        .findElement(By.id("idsignin")).getText(),"Login",
                "Name of the login button is not 'Login'");


    }
    @Test
    public void loginPositiveTest()  {
        //----- Find login and password fields and fill them
        WebElement loginField = driver.findElement(By.id("logininput"));
        WebElement passwordField = driver.findElement(By.id("passwordinput"));
        loginField.click();
        loginField.sendKeys("sashasasha");
        passwordField.click();
        passwordField.sendKeys("Sasha111");

        //---- Find sign in button and press on it ----
        driver.findElement(By.id("signinrequest")).click();

        //---- Go to the HomePage Auth -------
        //Thread.sleep(7000);
        waitUntilElementIsClickable(By.id("profile"),30);
        WebElement profileIcon = driver.findElement(By.id("profile"));

        // ------ Check that we on the HomePage for authorized user---

        Assert.assertTrue(profileIcon.getAttribute("title").contains("sashasasha"),"ProfileIcon do not contains login");


    }

}
