package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ProfilePageTests extends tests.TestBase {

    @BeforeMethod
    public void initTests() throws InterruptedException {
        //--- Login to the system ---
        WebElement loginIcon = driver.findElement(By.id("idsignin"));
        loginIcon.click();
        waitUntilElementIsClickable(By.xpath("//div[@id='signinrequest']"),20);

        WebElement loginField = driver.findElement(By.id("logininput"));
        WebElement passwordField = driver.findElement(By.id("passwordinput"));
        loginField.click();
        loginField.sendKeys("sashasasha");
        passwordField.click();
        passwordField.sendKeys("Sasha111");

        driver.findElement(By.id("signinrequest")).click();
        waitUntilElementIsClickable(By.xpath("//i[@id='profile']"),20);
//        Thread.sleep(5000);

        //--- Go to the Profile Page
        driver.findElement(By.id("profile")).click();
        Thread.sleep(3000);

    }

    @Test
    public void lastNameOfFamilyChanging() throws InterruptedException {

        // --- Open in edit mode ----
        driver.manage().window().fullscreen();
        driver.findElement(By.id("idbtneditprofile")).click();
        waitUntilElementIsClickable(By.xpath("//div[@id='idbtnsaveprofile']"),20);
        //Thread.sleep(7000);

        //--- Enter new last name ---
        WebElement lastNameField = driver
                .findElement(By.xpath("//span[@id='fieldobjfamilyName']//input"));
        lastNameField.click();
        lastNameField.clear();
        lastNameField.sendKeys("Shulga1");

        //--- Save profile ---
        driver.findElement(By.id("idbtnsaveprofile")).click();
        //Thread.sleep();
        waitUntilElementIsVisible(By.xpath("//div[@id='fieldobjrateGuest']"),10);

        // --- Go to the family page-----
        driver.findElement(By.id("family")).click();
        Thread.sleep(5000);
        System.out.println("Last name of the family verification: " + driver
                .findElement(By.id("titleprofile")).getText().contains("Shulga1"));

        // ---- Return to the profile
        driver.findElement(By.id("profile")).click();

        // --- Open in edit mode ----
        driver.manage().window().fullscreen();
        driver.findElement(By.id("idbtneditprofile")).click();
        Thread.sleep(7000);

        //--- Enter new last name ---
        lastNameField = driver
                .findElement(By.xpath("//span[@id='fieldobjfamilyName']//input"));
        lastNameField.click();
        lastNameField.clear();
        lastNameField.sendKeys("Shulga");

        //--- Save profile ---
        driver.findElement(By.id("idbtnsaveprofile")).click();
        Thread.sleep(5000);

        System.out.println("Last name was changed: " + driver.findElement(By.linkText("Shulga")).isDisplayed());

        //driver.quit();
        Assert.assertTrue(driver.findElement(By.linkText("Shulga")).isDisplayed(),
                "There is no an element which can be find by linkText('Shulga')");

    }
    @Test
    public void profileAndFamilyPageComparing() throws InterruptedException {

        String profileConfession = driver.findElement(By.xpath("//span[@id='fieldobjconfession']")).getText();
        System.out.println("Profile confession: " + profileConfession);

        String profileLanguage = driver.findElement(By.cssSelector("#fieldobjlanguages")).getText();
        System.out.println("Profile Language: " + profileLanguage);

        String profileFoodPreference = driver.findElement(By.xpath("//*[@id='fieldobjfoodPreferences']")).getText();
        System.out.println("Profile Food Preference: " + profileFoodPreference);


        //----------------- Go to the Family ------------------------
        driver.findElement(By.id("family")).click();
        Thread.sleep(5000);

        //----------------------Comparing--------------------------

        System.out.println("Confession values are the same for profile and family page: "
                + driver.findElement(By.xpath("//span[@id='fieldobjconfession']"))
                .getText().equals(profileConfession));

        System.out.println("Language values are the same for profile and family page: "
                + driver.findElement(By.xpath("//span[@id='fieldobjlanguages']"))
                .getText().equals(profileLanguage));

        System.out.println("Food preferences values are the same for profile and family page: "
                + driver.findElement(By.cssSelector(".itemprofilefit > #fieldobjfoodPreferences"))
                .getText().equals(profileFoodPreference));
        int counter = 0;
        if (driver.findElement(By.xpath("//span[@id='fieldobjconfession']"))
                .getText().equals(profileConfession)) counter++;
        if (driver.findElement(By.xpath("//span[@id='fieldobjlanguages']"))
                .getText().equals(profileLanguage)) counter++;
        if (driver.findElement(By.cssSelector(".itemprofilefit > #fieldobjfoodPreferences"))
                .getText().equals(profileFoodPreference)) counter++;

        Assert.assertEquals(counter,3);
        /*Assert.assertEquals(driver.findElement(By.xpath("//span[@id='fieldobjconfession']")).getText(),profileConfession,
                "Confession values are the same on profile and family pages");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='fieldobjlanguages']")).getText(), profileLanguage,
                "Language values are the same on profile and family pages");
        Assert.assertEquals(driver.findElement(By.cssSelector(".itemprofilefit > #fieldobjfoodPreferences")).getText(),
                profileFoodPreference,"Food preferences values are the same on profile and family pages");

        */
    }

}
