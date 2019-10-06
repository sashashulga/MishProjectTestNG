package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HomePageTests extends tests.TestBase {

    @Test
    public void homePageVerificationTest()  {

        // ------ Find list events element ----
        WebElement listEvent
                = driver.findElement(By.id("idtitletypesearchevents"));
        //--Verify that listEvent elements contains 'list events' text

        Assert.assertEquals(listEvent.getText(),"List events",
                "Name of the listEvent element is not 'List events'");

    }
    @Test
    public void singleFilterHolidays() throws InterruptedException {
        waitUntilElementIsVisible(By.name("selectholidays"),30);
        waitUntilAllElementsVisible(driver
                .findElements(By.xpath("//select[@name = 'selectholidays']/option")),30);
        WebElement holidaysFilter = driver
                .findElement(By.name("selectholidays"));
        System.out.println("is displayed: " + driver.findElement(
                By.xpath("//div[@id='idbtnclearfilter']")).isDisplayed());
        System.out.println("is enabled: " + driver.findElement(
                By.xpath("//div[@id='idbtnclearfilter']")).isEnabled());
        Select selector = new Select(holidaysFilter);
        selector.selectByValue("Shabbat");
        waitUntilElementIsClickable(By.xpath("//div[@id='idbtnclearfilter']"), 20);

    }



}
