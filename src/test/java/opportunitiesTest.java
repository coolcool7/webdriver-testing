import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.OpportunitiesPage;
import pages.HomePage;
import utils.DBHelper;

/*****************************************************************************
 * Author:      Ratna Janjanam
 * Description:  Test assignment to create a new Opportunity from the UI
 *
 *******************************************************************************/

public class opportunitiesTest {

    //-----------------------------------Global Variables-----------------------------------
    //Declare a Webdriver variable


    public WebDriver driver;
    private OpportunitiesPage opportunityPage;
    private String uniqueOpportunityName;
    private DBHelper dbhelper = new DBHelper();


    //Declare a test URL variable
    public String testURL = "https://login.salesforce.com/";


    //-----------------------------------Test Setup-----------------------------------
    @BeforeMethod
    public void setupTest (){
        //Create a new ChromeDriver
        driver = new ChromeDriver();
        LoginPage loginpage = new LoginPage(driver);

        dbhelper.setUp();  // establish Db connection
        dbhelper.insertToDB(); // insertdata into the required tables

        //Go to www.swtestacademy.com
        driver.navigate().to(testURL);
        HomePage home = loginPage.loginWithValidCredentials("testuser_1", "test123!!")
    }


    private static String queryDBForResultSet(){

        String result;
        try{

            String query = "select opportunityStage from opportunities where oppotunityName =” + uniqueOpportunityName + “‘”;
            // Get the contents of userinfo table from DB
            ResultSet res = stmt.executeQuery(query);
            // Print the result untill all the records are printe
            // res.next() returns true if there is any next record else returns false
            while (res.next())
            {
                result = res.getString(1));

            }
        }
        catch(Exception e)
        {

            e.printStackTrace();
        }

        return result;
    }


    //-----------------------------------Tests-----------------------------------
    /*
      The idea of this test to create a new opportunity that has a unique name with datestamp
      Upon successful create click on opportunities menu and search for the newly created
      opportunity name. As the entry is unique, there will be only 1 record returned.
      Assert the count is 1,

     */
    // Note: For this scenari, I am using a HashMap to save the test input values and passing it to the test
    // However Another way to do is reading from Excel file and passing it to TestNG Dataprovider.

    @Test
    public void createNewOpportunityTest () {

        Map<String,String> inputValues = new HashMap();

  // Generate unique Opportunity Name for easy search and finding
        String uniqueTimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        uniqueOpportunityName = "TestAutomation" + uniqueTimeStamp;

        inputValues.put("stage" , "prospecting");
        inputValues.put("opportunity name" , uniqueOpportunityName);
        inputValues.put("account name", "testautomation");
        inputValues.put("amount", "100");
        inputValues.put("close date", new SimpleDateFormat("MM.dd.yyyy").format(new Date()));

   // Call the methods from HomePage to create a new opportunity
        home.clickOpportunitiesMenuDropdown();
        home.clickTheDropdownOption();
        home.continueNextForDefaultSelection();
        home.enterDataInput(inputValues); // Enter the values from the map into the modal input fields
        home.clickSaveButton();
        home.clickOpportuniesMenu();

        // From the Opportunities view menu set the view to All Opportunities
        // Search for the newly created opportunity

        opportunityPage.setOppurtunitiesView("All Opportunities");
        Int resultCount = opportunityPage.searchOpportunityResult(uniqueOpportunityName)

        //Assertion

        Assert.assertEquals(resultCount, 1);
    }

   /*
      The purpose of this test is to query the database and fetch the current opportunityStage values prior to editing
      Then perfom edit and change the value.
      Retrieve data again from DB and compare that an actual update has been done

     */

    @Test

    public void DBTestAfterEdit() throws Exception{
        String valueBeforeUpdate;
        String valueAfterUpdate;
        Map<String,String> valuesToUpdate = new HashMap();

        valuesToUpdate.put("stage" , "closed won ");

        valueBeforeUpdate = queryDBForResultSet();


        // edit the item

        opportunityPage.ClickEdit();
        home.enterDataInput(valuesToUpdate);
        home.clickSaveButton();

        // Database query after the update

        valueAfterUpdate = queryDBForResultSet();

        Assert.assertNotEquals(vlueAfterUpdate, valueBeforeUpdate);
        Assert.assertEquals(vlueAfterUpdate, "closed won");

    }


    //-----------------------------------Test TearDown-----------------------------------
    @AfterMethod
    public void teardownTest (){
        //Close browser and end the session
        dbHelper.closeDBConnection();  //close the DB connection
        driver.quit();

    }
}
