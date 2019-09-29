package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;

    //Constructor
    public BasePage (WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,15);
    }

   // Method gets all the elements with the selector and then looks for the matching inner text attribute
   // Performs click only on the matching element. This is to handle elements that have the same id but differ in
    // attribute values

    private void clickElementWithMatchingText(By selector, String value){

        List<WebElements> menuDropDownItems = driver.findElements(selector);
        for(WebElement e: menuDropDownItems){
            if(e.getAttribute("innerText").contains(value)){
                    element.click();
                }
            }
        }

    // Method gets all the elements with the selector and then matches the text with the value passed
    // Performs click only on the matching element

    private void clickElementWithText(By selector, String value){

        List<WebElements> menuDropDownItems = driver.findElements(selector);
        for(WebElement e: menuDropDownItems){
            if(e.getText().equalIgnoreCase(value)){
                element.click();
            }
        }
    }

    private void EnterText(By selector, String value, String valueToEnter){

        List<WebElements> menuDropDownItems = driver.findElements(selector);
        for(WebElement e: menuDropDownItems){
            f(e.getAttribute("innerText").contains(value)){
                element.cleatText();
                element.sendKeys(valueToEnter);
            }
          }

    }

    //Wait Wrapper Method
    public void waitVisibility(By elementBy) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }

    public void waitForNoVisibility(By elementBy) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(elementBy));
    }


    //Click Method
    public void click (By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).click();
    }

    //Write Text
    public void writeText (By elementBy, String text) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).sendKeys(text);
    }

    //Read Text
    public String readText (By elementBy) {
        waitVisibility(elementBy);
        return driver.findElement(elementBy).getText();
    }

    //Assert
    public void assertEquals (By elementBy, String expectedText) {
        waitVisibility(elementBy);
        Assert.assertEquals(readText(elementBy), expectedText);

    }


}
