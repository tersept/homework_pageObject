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
            dayS,
            month,
            year,
            zip,
            country,
            city,
            street,
            house;
    int day;

    @BeforeEach
    void prepareTestData() {
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        userEmail = faker.internet().emailAddress("en");
        userNumber = faker.phoneNumber().subscriberNumber(10);
        day = faker.number().numberBetween(1, 30);
        if (day < 10) {
            dayS = "0" + day;
        } else dayS = day + "";
        year = faker.number().numberBetween(1940, 2020) + "";
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
                .setGender("Female")
                .setUserNumber(userNumber)
                .setBirthDate(dayS, "July", year)
                .setSubject("Maths")
                .setHobbies("Reading")
                .uploadFile("test.jpg")
                .setcurrentAddress(zip + "," + country + "," + city + "," + street + ", " + house)
                .selectState("Haryana")
                .selectCity("Karnal")
                .clickSubmit();

        registrationFormPage.checkModalVisible()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", "Female")
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", day + " July," + year)
                .checkResult("Subjects", "Maths")
                .checkResult("Hobbies", "Reading")
                .checkResult("Picture", "test.jpg")
                .checkResult("Address", zip + "," + country + "," + city + "," + street + ", " + house)
                .checkResult("State and City", "Haryana Karnal");

    }
}
