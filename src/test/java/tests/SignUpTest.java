package tests;

import base.BaseTest;
import feature.SignUpFeature;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.SignUpPage;
import utils.FakerUtils;

import java.io.InputStream;


public class SignUpTest extends BaseTest {
    JSONObject usersJSON;
    SignUpPage signUpPage;
    SignUpFeature signUpFeature;
    FakerUtils fakerUtils;

    @BeforeClass
    public void beforeClass() throws Exception {
        fakerUtils=new FakerUtils();
//        signUpPage = new SignUpPage(driver);
//        signUpFeature = new SignUpFeature(driver);
        InputStream dataIS = null;
        try {
            String dataFileName = "loginUsers.json";
            dataIS = getClass().getClassLoader().getResourceAsStream(dataFileName);
            JSONTokener tokener = new JSONTokener(dataIS);
            usersJSON = new JSONObject(tokener);
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(dataIS != null) {
                dataIS.close();
            }
        }
    }

    @Test
    public  void FirstTest(){
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage
                .enterFName("firstName")
                .enterLName("lastName")
                .enterCompany("company")
                .selectIndustry("Agriculture")
                .enterEmail("testmail121@gmail.com")
                .enterMobileNum("9910451292")
                .enterPassword("TestPass@123")
                .enterConfirmPassword("TestPass@123")
                .subscribeCheckBoxShouldBe(false)
                .clickOnSignUpBtn();
        if(signUpPage.isErrorMessageExists()){
            String errorMessage = signUpPage.getErrorMessage();
            Assert.assertTrue(errorMessage.contains(ErrorProp.getProperty("RegisteredEmail")));
            Assert.assertTrue(errorMessage.contains(ErrorProp.getProperty("RegisteredNumber")));
        }
        if(signUpPage.isDevnagrilogoExists()){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void registredNumAndEmail(){
        signUpFeature = new SignUpFeature(driver);
        String response = signUpFeature.signUp(
                usersJSON.getJSONObject("registeredUser").getString("FirstName"),
                usersJSON.getJSONObject("registeredUser").getString("LastName"),
                usersJSON.getJSONObject("registeredUser").getString("Company"),
                usersJSON.getJSONObject("registeredUser").getString("Industry"),
                usersJSON.getJSONObject("registeredUser").getString("email"),
                usersJSON.getJSONObject("registeredUser").getString("phoneNumber"),
                usersJSON.getJSONObject("registeredUser").getString("password"),
                usersJSON.getJSONObject("registeredUser").getString("confirmPassword"),
                usersJSON.getJSONObject("registeredUser").getBoolean("wantToSubscribe"));
        assertContains(response,ErrorProp.getProperty("RegisteredEmail"));
        assertContains(response,ErrorProp.getProperty("RegisteredNumber"));
    }

    @Test
    public void randomData(){
        FakerUtils fakerUtils=new FakerUtils();
        String randomPass=fakerUtils.generatePassword(10,12);
        signUpFeature = new SignUpFeature(driver);

        String response = signUpFeature.signUp(
                fakerUtils.generateFirstName(),
                fakerUtils.generateLastName(),
                fakerUtils.generateCompanyName(),
                "Retail",
                fakerUtils.generateRandomEmail(),
                fakerUtils.generateMobileNumber(),
                randomPass,
                randomPass,
                usersJSON.getJSONObject("registeredUser").getBoolean("wantToSubscribe"));
        assertContains(response,ErrorProp.getProperty("SuccessLoginIn"));
    }

    @Test
    public void ValidateRequiredValidation(){
        signUpPage =new SignUpPage(driver).load();
        assertContains(signUpPage.verifyFnameValidation(),ErrorProp.getProperty("fieldValidation"));
        assertContains(signUpPage.verifyLNameValidation(),ErrorProp.getProperty("fieldValidation"));
        assertContains(signUpPage.verifyCompanyValidation(),ErrorProp.getProperty("fieldValidation"));
        assertContains(signUpPage.verifyEmailValidation(),ErrorProp.getProperty("fieldValidation"));
        assertContains(signUpPage.verifyMobileValidation(),ErrorProp.getProperty("fieldValidation"));
        assertContains(signUpPage.verifyPasswordValidation(),ErrorProp.getProperty("fieldValidation"));
        assertContains(signUpPage.verifyConfirmPasswordValidation(),ErrorProp.getProperty("fieldValidation"));
    }

    @Test
    public void VerifyPasswordPolicy(){
        signUpPage =new SignUpPage(driver).load();

        signUpPage
                .enterFName(fakerUtils.generateFirstName())
                .enterLName(fakerUtils.generateLastName())
                .enterCompany(fakerUtils.generateCompanyName())
                .selectIndustry("Agriculture")
                .enterEmail(fakerUtils.generateRandomEmail())
                .enterMobileNum(fakerUtils.generateMobileNumber())
                .enterPassword(".")
                .enterConfirmPassword(".")
                .subscribeCheckBoxShouldBe(false)
                .clickOnSignUpBtn();
        ThreadSleep10();
        if(signUpPage.isErrorMessageExists()) {
            String errorStringResponse = signUpPage.getErrorMessage();
            assertContains(errorStringResponse,ErrorProp.getProperty("PasswordLength"));
            assertContains(errorStringResponse,ErrorProp.getProperty("PasswordUpperLower"));
            assertContains(errorStringResponse,ErrorProp.getProperty("PasswordOneLetter"));
            assertContains(errorStringResponse,ErrorProp.getProperty("PasswordOneNumber"));
        }
        signUpPage.enterPassword(fakerUtils.generatePassword(8,10,true,false,true))
                .enterConfirmPassword(fakerUtils.generatePassword(8,10,true,false,true))
                .clickOnSignUpBtn();
        ThreadSleep10();
        if(signUpPage.isErrorMessageExists()) {
            String errorStringResponse = signUpPage.getErrorMessage();
            assertContains(errorStringResponse,ErrorProp.getProperty("PasswordOneSymbol"));
        }


    }

}
