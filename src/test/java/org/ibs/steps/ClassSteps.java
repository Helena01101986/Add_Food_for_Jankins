package org.ibs.steps;

import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ClassSteps {
    WebDriver driver = setupRemoteDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));


    public static RemoteWebDriver setupRemoteDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("browserName", "chrome");
        selenoidOptions.put("browserVersion", "109.0");
        selenoidOptions.put("enableVNC", true);
        selenoidOptions.put("enableVideo", false);
        capabilities.setCapability("selenoid:options", selenoidOptions);
        try{
            return new RemoteWebDriver(URI.create("http://149.154.71.152:4444/wd/hub").toURL(), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public ClassSteps()  {
        //System.setProperty("webdriver.chromedriver.driver", "src\\test\\resources\\chromedriver.exe");
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Когда("Перейти на {string}")
    public void перейти_на(String String) {
        driver.get("http://149.154.71.152:8080/");
    }

    @Если("Выполнено нажатие на {string}")
    public void выполнено_нажатие_на(String menu) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='navbarDropdown']")));
        WebElement btnSandbox = driver.findElement(By.xpath("//a[@id = 'navbarDropdown']"));
        btnSandbox.click();
    }

    @И("Выпадающее меню видимо")
    public void выпадающее_меню_видимо() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class= 'dropdown-menu show']")));
        WebElement menu = driver.findElement(By.xpath("//div[@class= 'dropdown-menu show']"));
        Assertions.assertTrue(menu.isDisplayed());
    }

    @Тогда("Выполняется нажатие на кнопку {string}")
    public void выполняется_нажатие_на_кнопку(String food) {
        WebElement btnFood = driver.findElement(By.xpath("//a[@href= '/food']"));
        btnFood.click();
    }

    @Если("Список товаров открыт")
    public void список_товаров_открыт() {
        WebElement table = driver.findElement(By.xpath("//table[@class = 'table']"));
        Assertions.assertTrue(table.isDisplayed());
    }

    @И("Кнопка {string} кликабельна")
    public void кнопка_кликабельна(String add) {
        WebElement btnAdd = driver.findElement(By.xpath("//*[text()='Добавить']"));
        Assertions.assertTrue(btnAdd.isEnabled());
    }

    @Тогда("Нажать кнопку {string}")
    public void нажать_кнопку(String add) {
        WebElement btnAdd = driver.findElement(By.xpath("//*[text()='Добавить']"));
        btnAdd.click();
    }

    @Если("Окно добавления нового товара видимо")
    public void окно_добавления_нового_товара_видимо() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Добавление товара']")));
        WebElement addProductForm = driver.findElement(By.xpath("//*[text()='Добавление товара']"));
        Assertions.assertTrue(addProductForm.isDisplayed());
    }

    @И("Поле {string} видимо")
    public void поле_видимо(String string) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder = 'Наименование']")));
        WebElement nameFood = driver.findElement(By.xpath("//input[@placeholder = 'Наименование']"));
        Assertions.assertTrue(nameFood.isDisplayed());
    }

    @Тогда("Поле {string} заполняется значением {string}")
    public void поле_заполняется_значением(String name, String value) {
        WebElement nameFood = driver.findElement(By.xpath("//input[@placeholder = 'Наименование']"));
        nameFood.sendKeys("Салака");
    }

    @Тогда("Поле {string} заполнить значением {string}")
    public void поле_заполнить_значением(String name, String value) {
        WebElement nameFood = driver.findElement(By.xpath("//input[@placeholder = 'Наименование']"));
        nameFood.sendKeys("Картофель");
    }

    @И("Поле {string} содержит значение {string}")
    public void поле_содержит_значение(String name, String value) {
        WebElement nameFood = driver.findElement(By.xpath("//input[@placeholder = 'Наименование']"));
        Assertions.assertEquals("Салака", "Салака");
    }
    @И("В поле {string} содержится значение {string}")
    public void в_поле_содержится_значение(String string, String string2) {
        WebElement nameFood = driver.findElement(By.xpath("//input[@placeholder = 'Наименование']"));
        Assertions.assertEquals("Картофель", "Картофель");
    }

    @Если("Поле {string} видимо и кликабельно")
    public void поле_видимо_и_кликабельно(String string) {
        WebElement typeFood = driver.findElement(By.xpath("//select"));
        typeFood.isEnabled();
    }

    @И("Выполнено нажатие на поле {string}")
    public void выполнено_нажатие_на_поле(String string) {
        WebElement typeFood = driver.findElement(By.xpath("//select"));
        typeFood.click();
    }

    @Тогда("В поле {string} выбрано значение {string}")
    public void в_поле_выбрано_значение(String type, String value) {
        WebElement choiceFr = driver.findElement(By.xpath("//option[@value = 'FRUIT']"));
        Assertions.assertTrue(choiceFr.isDisplayed());
        choiceFr.click();
    }
    @Тогда("В поле {string} выбрать значение {string}")
    public void в_поле_выбрать_значение(String type, String value) {
        WebElement choiceVeg = driver.findElement(By.xpath("//option[@value = 'VEGETABLE']"));
        Assertions.assertTrue(choiceVeg.isDisplayed());
        choiceVeg.click();
    }

    @Если("Чек-бокс {string} кликабелен")
    public void чек_бокс_кликабелен(String string) {
        WebElement checkbox = driver.findElement(By.xpath("//input[@type = 'checkbox']"));
        Assertions.assertTrue(checkbox.isEnabled());
    }

    @Тогда("Отметить чек-бокс {string}")
    public void отметить_чек_бокс(String string) {
        WebElement checkbox = driver.findElement(By.xpath("//input[@type = 'checkbox']"));
        checkbox.click();
    }

    @Если("Чек-бокс {string} отмечен")
    public void чек_бокс_отмечен(String string) {
        WebElement checkbox = driver.findElement(By.xpath("//input[@type = 'checkbox']"));
        Assertions.assertTrue(checkbox.isSelected());
    }
    @Допустим("Оставить чек-бокс не отмеченным")
    public void оставить_чек_бокс_не_отмеченным() {
        WebElement checkbox = driver.findElement(By.xpath("//input[@type = 'checkbox']"));
        Assertions.assertFalse(checkbox.isSelected());
    }

    @Тогда("Выполнить нажатие на кнопку {string}")
    public void выполнить_нажатие_на_кнопку(String save) {
        WebElement btnSave = driver.findElement(By.xpath("//button[@id = 'save']"));
        btnSave.click();
    }

    @И("Проверка нового товара в списке")
    public void проверка_нового_товара_в_списке() {
        WebElement addedFood = driver.findElement(By.xpath("//*[.='Салака']"));
        Assertions.assertTrue(addedFood.isEnabled());
    }

    @И("Проверка товара в списке")
    public void проверка_товара_в_списке() {
        WebElement addedFood = driver.findElement(By.xpath("//*[.='Картофель']"));
        Assertions.assertTrue(addedFood.isEnabled());
    }

    @И("Приложение закрыто")
    public void приложение_закрыто() {
        driver.close();
    }

    @Когда("Ввести в поле {string} имя пользователя")
    public void ввести_в_поле_имя_пользователя(String string) {
        WebElement userName = driver.findElement(By.xpath("//input[@name = 'user']"));
        userName.sendKeys("user");
    }

    @И("Ввести в поле {string} пароль")
    public void ввести_в_поле_пароль(String string) {
        WebElement pass = driver.findElement(By.xpath("//input[@name = 'password']"));
        pass.sendKeys("pass");
    }

    @То("Нажать на кнопку {string}")
    public void нажать_на_кнопку(String connect) {
    WebElement btnCon = driver.findElement(By.xpath("//input[@value = 'Connect']"));
    btnCon.click();
    }

    @Тогда("Выполнить запрос на добавление товара")
    public void выполнить_запрос_на_добавление_товара() {
        WebElement txtArea = driver.findElement(By.xpath("//textarea[@rows = '5']"));
        txtArea.sendKeys("INSERT INTO food(food_name, food_type, food_exotic)VALUES('Дуриан', 'FRUIT', 1);");
        WebElement btnRun = driver.findElement(By.xpath("//input[@value = 'Run']"));
        btnRun.click();
    }
    @Допустим("Выполнить запрос для получения добавленного в БД товара")
    public void выполнить_запрос_для_получения_добавленного_в_бд_товара() {
        WebElement txtArea = driver.findElement(By.xpath("//textarea[@rows = '5']"));
        txtArea.sendKeys("SELECT * FROM food WHERE food_name = 'Дуриан'");
        WebElement btnRun = driver.findElement(By.xpath("//input[@value = 'Run']"));
        btnRun.click();
    }
    @Допустим("Выполнить запрос для получения списка всех товаров")
    public void выполнить_запрос_для_получения_списка_всех_товаров() {
        WebElement txtArea = driver.findElement(By.xpath("//textarea[@rows = '5']"));
        txtArea.sendKeys("SELECT * FROM food");
        WebElement btnRun = driver.findElement(By.xpath("//input[@value = 'Run']"));
        btnRun.click();
    }
    @Допустим("Выполнить запрос на удаление товара из таблицы")
    public void выполнить_запрос_на_удаление_товара_из_таблицы() {
        WebElement txtArea = driver.findElement(By.xpath("//textarea[@rows = '5']"));
        txtArea.sendKeys("DELETE FROM food WHERE food_name = 'Дуриан'");
        WebElement btnRun = driver.findElement(By.xpath("//input[@value = 'Run']"));
        btnRun.click();
    }

    @Допустим("Страница добавления товара открыта")
    public void страница_добавления_товара_открыта() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@rows = '5']")));
        WebElement txtArea = driver.findElement(By.xpath("//textarea[@rows = '5']"));
        Assertions.assertTrue(txtArea.isDisplayed());

    }

    @Если("Перейти на страницу {string}")
    public void перейти_на_страницу(String string) {
        driver.get("http://149.154.71.152:8080/h2-console");
    }

}

