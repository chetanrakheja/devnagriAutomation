package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage extends BasePage {

    @FindBy(id = "first_name")
    WebElement fName;

    @FindBy(id = "last_name")
    WebElement lName;

    @FindBy(id = "company")
    WebElement company;

    @FindBy(name = "industry")
    WebElement industryDropDown;

    @FindBy(id = "email2")
    WebElement email;

    @FindBy(id = "number")
    WebElement mobNum;

    @FindBy(id = "password2")
    WebElement passwordField;

    @FindBy(id = "password-confirm")
    WebElement passwordConfirmField;

    @FindBy(id = "subscriber")
    WebElement subscribeCheckBox;

    @FindBy(name = "signup")
    WebElement signUpBtn;

    @FindBy(xpath = "//*[@class='alert alert-danger m-5 p-5']")
    WebElement errorAlert;

    @FindBy(id="devnagri-logo")
    WebElement devnagriLogo;

    public SignUpPage(WebDriver driver) {
        super(driver);
        load();
        PageFactory.initElements(driver, this);
    }

    public SignUpPage load(){
        load("/register");
        return this;
    }

    public SignUpPage enterFName(String fNameStr){
        setText(fName,fNameStr,"First Name");
        return this;
    }

    public SignUpPage enterLName(String lNameStr){
        setText(lName,lNameStr,"Last Name");
        return this;
    }

    public SignUpPage enterCompany(String companyStr){
        setText(company,companyStr,"Company Name");
        return this;
    }

    public SignUpPage enterEmail(String emailStr){
        setText(email,emailStr,"Email");
        return this;
    }
    public SignUpPage enterMobileNum(String mobileNumStr){
        setText(mobNum,mobileNumStr,"Mobile Number");
        return this;
    }
    public SignUpPage enterPassword(String passwordStr){
        setText(passwordField,passwordStr,"Password");
        return this;
    }
    public SignUpPage enterConfirmPassword(String confirmPasswordStr){
        setText(passwordConfirmField,confirmPasswordStr,"Confirm Password");
        return this;
    }

    public SignUpPage selectIndustry(String industryStr){
        selectDropDownByValue(industryDropDown,industryStr,"Industry");
        return this;
    }

    public SignUpPage clickOnSignUpBtn(){
        click(signUpBtn,"Sign Up");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public  SignUpPage subscribeCheckBoxShouldBe(Boolean toBeSelected){
        selectElement(subscribeCheckBox,toBeSelected);
        return this;
    }

    public boolean isErrorMessageExists(){
//        WebElement tmp = driver.findElement(By.xpath("//*[@class='alert alert-danger m-5 p-5']"));
//            return isElementDisplayed(tmp,"Error Alert");
            return isElementDisplayed(errorAlert,"Error Alert");

    }

    public String getErrorMessage(){
//        WebElement tmp = driver.findElement(By.xpath("//*[@class='alert alert-danger m-5 p-5']"));
//        return getText(tmp,"Error Alert");
        return getText(errorAlert,"Error Message");
    }

    public boolean isDevnagrilogoExists(){
//        WebElement tmp = driver.findElement(By.id("devnagri-logo"));
//        return isElementDisplayed(tmp,"Devnagri Logo");
        return isElementDisplayed(devnagriLogo,"Devnagri Logo");
    }

    public String verifyFnameValidation(){
        return getAttribute(fName,"validationMessage","First Name");
    }

    public String verifyLNameValidation(){
        return getAttribute(lName,"validationMessage","Last Name");
    }
    public String verifyCompanyValidation(){
        return getAttribute(company,"validationMessage","Company");
    }
    public String verifyEmailValidation(){
        return getAttribute(email,"validationMessage","Email");
    }
    public String verifyMobileValidation(){
        return getAttribute(mobNum,"validationMessage","Mobile");
    }
    public String verifyPasswordValidation(){
        return getAttribute(passwordField,"validationMessage","Password");
    }
    public String verifyConfirmPasswordValidation(){
        return getAttribute(passwordConfirmField,"validationMessage","Confirm Password");
    }





}
