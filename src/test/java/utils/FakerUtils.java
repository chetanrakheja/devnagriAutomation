package utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public Long generateRandomNumber(){
        Faker faker = new Faker();
        return faker.number().randomNumber(10, true);
    }

    public String generateRandomEmail(){
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }
    public String generateCompanyName(){
        Faker faker = new Faker();
        return faker.company().name();
    }

    public String generateMobileNumber(){
        Faker faker = new Faker();
        return faker.phoneNumber().subscriberNumber(10);
    }

    public String generatePassword(int minLength,int maxLength){
        Faker faker = new Faker();
        return faker.internet().password(minLength,maxLength,true,true,true);
    }

    public String generatePassword(int minLength,int maxLength,Boolean includeUpper,Boolean includeSpecial,Boolean includeDigit){
        Faker faker = new Faker();
        return faker.internet().password(minLength,maxLength,includeUpper,includeSpecial,includeDigit);
    }

    public String generateFirstName(){
        Faker faker = new Faker();
        return faker.address().firstName();
    }
    public String generateLastName(){
        Faker faker = new Faker();
        return faker.address().lastName();
    }
    public String generateIndustry(){
        Faker faker = new Faker();
        return faker.company().industry();
    }






}
