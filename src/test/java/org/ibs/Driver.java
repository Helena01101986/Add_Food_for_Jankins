package org.ibs;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {
    public class WebDriverFactory {

        public WebDriver createDriver(String browser) throws MalformedURLException {
            WebDriver driver;

            if ("chrome".equalsIgnoreCase(browser)) {
                // Локальный Chrome
                driver = new ChromeDriver();
            } else if ("firefox".equalsIgnoreCase(browser)) {
                // Локальный Firefox
                driver = new FirefoxDriver();
            } else if ("remote".equalsIgnoreCase(browser)) {
                // Запуск через Selenoid
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("chrome"); // или другой браузер
                driver = new RemoteWebDriver(new URL("http://selenoid.example.com:4444/wd/hub"), capabilities);
            } else {
                throw new IllegalArgumentException("Unknown browser: " + browser);
            }

            return driver;
        }
    }

}
