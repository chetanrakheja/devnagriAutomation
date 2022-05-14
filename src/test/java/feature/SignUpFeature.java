package feature;

import constants.ErrorMsgsCons;
import org.openqa.selenium.WebDriver;
import pages.SignUpPage;

public class SignUpFeature {
    SignUpPage signUpPage;

    public SignUpFeature(WebDriver driver ){
        signUpPage = new SignUpPage(driver);
    }

    public String signUp(String fName,String lName,String company,String industry,String email,
                         String mobNum,String password,String confirmPass,Boolean wantToSubscribe){
        signUpPage
                .enterFName(fName)
                .enterLName(lName)
                .enterCompany(company)
                .selectIndustry(industry)
                .enterEmail(email)
                .enterMobileNum(mobNum)
                .enterPassword(password)
                .enterConfirmPassword(confirmPass)
                .subscribeCheckBoxShouldBe(wantToSubscribe)
                .clickOnSignUpBtn();
        if(signUpPage.isErrorMessageExists()){
            String errorMessage = signUpPage.getErrorMessage();
            return errorMessage;
        }
        if(signUpPage.isDevnagrilogoExists()){
            return ErrorMsgsCons.init().ErrorProp.getProperty("SuccessLoginIn");
        }
        return "";
    }





}
