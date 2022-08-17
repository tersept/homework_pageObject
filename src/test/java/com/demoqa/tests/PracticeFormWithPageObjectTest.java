package com.demoqa.tests;

import com.demoqa.pages.RegistrationFormPage;
import com.demoqa.pages.TestBase;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

public class PracticeFormWithPageObjectTest extends TestBase {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    Faker faker = new Faker(new Locale("ru"));

    String firstName,
            lastName,
            userEmail,
            userNumber,
            userGender,
            dayS,
            month,
            year,
            userSubject,
            userHobbies,
            userFileName,
            zip,
            country,
            city,
            street,
            house,
            userState,
            userCity;
    int day;

    @BeforeEach
    void prepareTestData() {
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        userEmail = faker.internet().emailAddress("en");
        userGender = "Female";
        userNumber = faker.phoneNumber().subscriberNumber(10);
        day = faker.number().numberBetween(1, 30);
        if (day < 10) {
            dayS = "0" + day;
        } else dayS = day + "";
        month = "July";
        year = faker.number().numberBetween(1940, 2020) + "";
        userSubject = "Maths";
        userHobbies = "Reading";
        userFileName = "test.jpg";
        userState = "Haryana";
        userCity = "Karnal";
        zip = faker.address().zipCode();
        country = faker.address().country();
        city = faker.address().city();
        street = faker.address().streetAddress();
        house = faker.address().streetAddressNumber();
    }


    @Test
    void fillForm() {

        registrationFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setGender(userGender)
                .setUserNumber(userNumber)
                .setBirthDate(dayS, month, year)
                .setSubject(userSubject)
                .setHobbies(userHobbies)
                .uploadFile(userFileName)
                .setcurrentAddress(zip + ", " + country + ", " + city + ", " + street + ", " + house)
                .selectState(userState)
                .selectCity(userCity)
                .clickSubmit();

        registrationFormPage.checkModalVisible()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", userGender)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", day + " " + month + "," + year)
                .checkResult("Subjects", userSubject)
                .checkResult("Hobbies", userHobbies)
                .checkResult("Picture", userFileName)
                .checkResult("Address", zip + ", " + country + ", " + city + ", " + street + ", " + house)
                .checkResult("State and City", userState + " " + userCity);

    }
}
