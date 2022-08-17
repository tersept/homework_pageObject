package com.demoqa.pages.components;

import com.demoqa.pages.RegistrationFormPage;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class CalendarComponent {

    public CalendarComponent setDate(String day, String month, String year) {

        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
       // $(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)").click();
        $(format(".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", day)).click();

        return this;
    }
}
