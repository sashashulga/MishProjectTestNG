package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

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
        waitUntilElementIsVisible(By.name("selectholidays"),10);
        WebElement holidaysFilter = driver
                .findElement(By.name("selectholidays"));
        Select selector = new Select(holidaysFilter);
        selector.selectByValue("Shabbat");
        Thread.sleep(10000);

    }



}
