package base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.time.Duration;
import java.util.Properties;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    Properties props;
    InputStream inputStream;

    public BasePage(WebDriver driver){
        this.driver = driver;
//        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        loadAllProperties();
    }

    private void loadAllProperties() {
        try{
            props = new Properties();
            String propFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(inputStream);

        }catch (Exception e){
            System.out.println("Error in Loading Properties");
            e.printStackTrace();
        }
    }


    public void load(String endPoint){
        driver.get(props.getProperty("BaseURL") + endPoint);
    }

    protected void selectElement(WebElement element, Boolean toSelect){
        if(element.isSelected() && !toSelect){
            element.click();
        }else if(toSelect && !element.isSelected()){
            element.click();
        }

    }

    protected void setText(WebElement element, String value, String logElement) {
        if (value != null) {

            try {
                System.out.println("");
                System.out.println("Setting Text as '"+ value +"' in "+logElement);
//                wait.until(ExpectedConditions.elementToBeClickable(element)).clear();
                element.sendKeys(value);
            } catch (ElementClickInterceptedException elementClickInterceptedException) {
                try {
                    Actions action = new Actions(driver);
                    action.moveToElement(element).sendKeys(element, value).build().perform();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected String getText(WebElement element, String logElement)  {
        String elementText;
        try {
            elementText = element.getText();
        } catch (Exception ex) {
            elementText="";
            ex.printStackTrace();
        }
        return elementText;
    }

    protected void selectDropDownByValue(WebElement element, String value, String logElement) {
        if (value != null) {
            try {
                System.out.println("");
                System.out.println("Selecting '"+logElement +"' dropdown with value ="+value);
                Select dropdown = new Select(element);
                dropdown.selectByValue(value);
            } catch (Exception ex) {
               ex.printStackTrace();
            }

        }

    }

    protected void selectDropDownByVisibleText(WebElement element, String value, String logElement) {
        if (value != null) {
            try {
                WebDriverWait ExplicitWait = new WebDriverWait(driver, 15);
                Select dropdown = new Select(ExplicitWait.until(ExpectedConditions.visibilityOf(element)));
                dropdown.selectByVisibleText(value);
               } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    protected void JSBasedClick(WebElement element, String logElement) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", element);
    }

    protected void click(WebElement element,String logElement){
        element.click();
    }

    protected void clickSubmit(WebElement element, String logElement)  {
        try {

            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (ElementClickInterceptedException elementClickInterceptedException) {
            try {
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                jse.executeScript("arguments[0].click();", element);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } catch (StaleElementReferenceException exception) {
            element.click();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    protected boolean isElementDisplayed(WebElement element,String elementDesc) {
        try {
            System.out.println("");
            System.out.println("Checking if '"+ elementDesc + "' element Exists");
            return element.isDisplayed();
        }catch (Exception e){
//            e.printStackTrace();
            System.out.println(elementDesc + "Does Not Exists");
            return false;
        }

    }

    protected void ThreadSleep10(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public String getAttribute(WebElement e, String attribute,String elementDesc) {
        System.out.println("Getting '"+attribute+"' Attribute of "+elementDesc);
        return e.getAttribute(attribute);
    }

}
