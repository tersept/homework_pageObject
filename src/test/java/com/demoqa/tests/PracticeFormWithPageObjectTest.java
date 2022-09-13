package com.demoqa.tests;

import com.codeborne.selenide.WebDriverRunner;
import com.demoqa.pages.RegistrationFormPage;
import com.github.javafaker.Faker;
import helpers.Attach;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.Locale;

import static io.qameta.allure.Allure.step;

public class PracticeFormWithPageObjectTest extends TestBase {

    //SelenideLogger.addListener("allure",new AllureSelenide);

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

    @Attachment(value = "Скриншот", type = "image/png", fileExtension = "png")
    public byte[] attachScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Test
    void fillForm() {
        step("Заполняем форму", () -> {
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
        });
        step("Проверяем правильность заполнения", () -> {
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
            attachScreenshot();
        });
    }

    @AfterEach
    void AddAttachments() {
        Attach.screenshotAs("Last screenshots");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}