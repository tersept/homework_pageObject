package com.demoqa.pages.components;

import static com.codeborne.selenide.Selenide.$;

public class FileUploadComponent {

    public FileUploadComponent fileUpload(String value) {

        $("#uploadPicture").uploadFromClasspath(value);

        return this;
    }
}
