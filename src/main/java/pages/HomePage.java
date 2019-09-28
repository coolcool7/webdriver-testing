package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.rmi.runtime.Log;

public class HomePage extends BasePage {

    @FindBy(cssSelector = "one-app-nav-bar-item-root one-app-nav-bar-item-dropdown[text='Opportunities Menu']")
    private WebElement OpportunitiesMenu;

    @FindBy(cssSelector = "div.menuItemsWrapper")
    private WebElement menuItemsWrapper;

    @FindBy(name = "Opportunity-search-input")
    private WebElement searchInputField;

    By menuDropDown = By.cssSelector("one-app-nav-bar-item-root one-app-nav-bar-item-dropdown");
    By ButtonsOnModal = By.cssSelector("div.oneRecordActionWrapper button span");
    By opportunitiesModal = By.cssSelector("div.oneRecordActionWrapper");
    By uiInput = By.cssSelector("div.uiInput");
    By option = By.cssSelector("one-app-nav-bar-menu-item span");
    By menuItem = By.cssSelector("div.slds-context-bar a span");


    public HomePage (WebDriver driver) {
        super(driver);
    }


    // Click Opportunities Menu Item
    public void clickOpportunitiesMenuDropdown(){
         super.clickElementWithMatchingText(menuDropDown, "Opportunities")

    }

    // Click Opportunities Menu Item
    public void clickTheDropdownOption(){
        super.waitVisibility(menuItemsWrapper);
        super.clickElementWithText(option, "New Opportunity")

    }

    // Wait for the Modal to appear and click on the next button with the default option

    public void continueNextForDefaultSelection(){
        super.waitVisibility(opportunitiesModal);
        super.clickElementWithText(ButtonsOnModal,"Next");

    }

    // Enter the values into the input fields as specified in the map

    public void enterDataInput(Map<String,String> inputvalues){

      for(Entry<String,String> E : inputvalues.EntrySet()){

           String key = E.getKey();
           String valueToEnter = E.getValue()
           Super.EnterText(uiInput,key,valueToEnter);
      }


    }

    public void clickSaveButton(){

        super.clickElementWithText(ButtonsOnModal,"Save");

    }

    public void clickOpportuniesMenu(){

        super.waitForNoVisibility(opportunitiesModal);
        super.clickElementWithText(menuItem, "Opportunities");
    }

    // Click on the Opportunities menu and wait for the search input to appear
    // Send the test to search
    // Get a count of all the row results and return the output

    public int searchOpportunityResult(String opportunityName){

        try {

            super.waitForNoVisibility(opportunitiesModal);
            super.clickElementWithText(menuItem, "Opportunities");

            super.writeText(searchInputField, opportunityName);
            super.writeText(searchInputField, Keys.ENTER);

            List<WebElements> rows = driver.findElements(by.cssSelector("table.slds-table tbody tr"))
            return rows.length();
        }
        catch(ElementNotFoundException E){
            System.out.println(E.stackTrace());
        }
    }



}

