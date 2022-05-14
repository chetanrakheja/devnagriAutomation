package constants;

import java.io.InputStream;
import java.util.Properties;

public class ErrorMsgsCons {

    public Properties ErrorProp;
    private InputStream inputStream2;

    private ErrorMsgsCons() {
        try{
            ErrorProp = new Properties();
            String errorProperties = "ErrorMsgs.properties";
            inputStream2 = getClass().getClassLoader().getResourceAsStream(errorProperties);
            ErrorProp.load(inputStream2);
        }catch (Exception e){
            System.out.println("Error in Loading Properties");
            e.printStackTrace();
        }
    }

    public static ErrorMsgsCons init(){
        return new ErrorMsgsCons();
    }

}
