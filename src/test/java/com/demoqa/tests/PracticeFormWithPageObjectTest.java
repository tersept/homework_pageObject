package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationFormPage;
import com.demoqa.pages.components.ResultsModalComponent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormWithPageObjectTest {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    ResultsModalComponent resultsModalComponent = new ResultsModalComponent();

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillForm() {

        registrationFormPage.openPage()
                .setFirstName("Irina")
                .setLastName("Testova")
                .setUserEmail("test@test.ru")
                .setGender("Female")
                .setUserNumber("9091234455")
                .setBirthDate("30", "July", "2000")
                .setSubject("Maths")
                .setHobbies()
                .uploadFile("test.jpg")
                .setcurrentAddress("424000, Russia, Moscow, Arbat street, 123")
                .selectState("Haryana")
                .selectCity("Karnal");
        $("#submit").click();

        registrationFormPage.checkModalVisible()
                .checkResult("Student Name", "Irina Testova")
                .checkResult("Student Email", "test@test.ru")
                .checkResult("Gender", "Female")
                .checkResult("Mobile", "9091234455")
                .checkResult("Date of Birth", "30 July,2000");

    }
}
