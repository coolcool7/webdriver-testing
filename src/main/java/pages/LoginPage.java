package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.rmi.runtime.Log;

public class LoginPage extends BasePage {

    @FindBy(id="username")
    private WebElement userName;

    @FindBy(id="password")
    private WebElement passWord;

    @FindBy(id="login")
    private WebElement loginButton;


    //*********Constructor*********
    public LoginPage (WebDriver driver) {
        super(driver);
    }


    //*********Web Elements*********
    By signInButtonBy = By.className("btnSignIn");



    public void enterUserName(String username){
        this.userName.clear();
        this.userName.sendKeys(username);
    }

    public void enterPassword(String password){
        this.passWord.clear();
        this.passWord.sendKeys(password);
    }

    public HomePage login(){
        LoginButton.click();
        return new HomePage(driver);
    }

    public HomePage loginWithValidCredentials(uname,pword)
    {
        this.enterUserName(uname);
        this.enterPassword(pword);

        return this.login();
    }
}

