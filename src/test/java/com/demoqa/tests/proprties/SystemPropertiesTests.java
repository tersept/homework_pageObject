package com.demoqa.tests.proprties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {
    @Test
    @Tag("properties")
    void SimplePropertiTest() {
        String browserName = System.getProperty("browser_name", "firefox");
        String browserVersion = System.getProperty("browser_version", "100.0");
        String browserSize = System.getProperty("browser_size", "1280x980");

        System.out.println(browserName);
        System.out.println(browserVersion);
        System.out.println(browserSize);
    }
}