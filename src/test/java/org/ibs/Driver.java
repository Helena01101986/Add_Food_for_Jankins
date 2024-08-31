package org.ibs;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {

    public void getDriver() {

        String remoteUrl = "selenoid.url";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("type.browser");
        capabilities.setVersion("109.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);

        try {

            WebDriver driver = new RemoteWebDriver(new URL(remoteUrl), capabilities);

        } catch (MalformedURLException e) {
            throw  new RuntimeException(e);
        }
    }
}

