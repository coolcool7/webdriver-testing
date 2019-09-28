package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.rmi.runtime.Log;

public class OpportunitiesPage extends BasePage {

    @FindBy(cssSelector = "div.triggerLink")
    private WebElement triggerViewLink;

    @FindBy(cssSelector = "div.listContent ul li")
    private WebElement viewMenuLInks;

    @FindBy(name = "Opportunity-search-input")
    private WebElement searchInputField;


   // Menu to click next to a row on the grid view to see the edit option
    @FindBy(cssSelector = "tbody lightning-primitive-icon")
    private WebElement EditOptionsMenuIcon;

    // CSS selector to choose menu item from the dropdown for the edit and other options
    @FindBy(cssSelector = "div.branding-actions li a")
    private WebElement BrandingOption;


    public OpportunitiesPage (WebDriver driver) {
        super(driver);
    }


    // Click Opportunities view Menu Item
    public void setOppurtunitiesView(String type){
        super.click(triggerViewLink);
        super.clickElementWithMatchingText(viewMenuLInks,type);

    }

    // Choose branding optins and select to edit an item

    public void ClickEdit(){
        super.click(EditOptionsMenuIcon");
        super.clickElementWithMatchingText(BrandingOption,"Edit");

    }

    // Search the opportunities list based on a opportunity title
    public int searchForValue( String name){

       try {
           super.writeText(searchInputField, name);
           super.writeText(searchInputField, Keys.ENTER);

           List<WebElements> rows = driver.findElements(by.cssSelector("table.slds-table tbody tr"))
           return rows.length();
       }
       Catch( Exception e){
           Logger.log(e.stackTrace);
        }

    }


}

