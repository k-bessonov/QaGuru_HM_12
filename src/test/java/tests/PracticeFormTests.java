package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static io.qameta.allure.Allure.step;

import static data.TestData.*;

@Tag("demoqa")
public class PracticeFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();


    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

    }

    @Test
    void successfulRegistrationTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем сайт с формой", () -> {
            registrationPage.openPage()
                    .removeBanner();
        });
        step("Заполняем форму всеми данными", () -> {
            registrationPage.setFirstName(firstName)
                    .setLastName(lastName)
                    .setEmail(userEmail)
                    .setGender(gender)
                    .setUserNumber(mobilePhone)
                    .setDateOfBirth(date, month, year)
                    .setSubjects(subjects)
                    .setHobbies(hobbies)
                    .setPicture(picture)
                    .setCurrentAddress(streetAddress)
                    .setState(state)
                    .setCity(city);
        });

        step("Отравляем заполненную форму", () -> registrationPage.submitPracticeForm());

        step("Проверка результатов заполнения", () -> {
            registrationPage.verifyResultsAppears()
                    .checkResult("Student Name", firstName + " " + lastName)
                    .checkResult("Student Email", userEmail)
                    .checkResult("Gender", gender)
                    .checkResult("Mobile", mobilePhone)
                    .checkResult("Date of Birth", date + " " + month + "," + year)
                    .checkResult("Subjects", subjects)
                    .checkResult("Hobbies", hobbies)
                    .checkResult("Picture", picture)
                    .checkResult("Address", streetAddress)
                    .checkResult("State and City", state + " " + city);
        });
    }

    @Test
    void successMinFormTest() {

        step("Открываем сайт с формой", () -> {
            registrationPage.openPage()
                    .removeBanner();
        });

        step("Заполняем форму обязательными полями", () -> {
            registrationPage.setFirstName(firstName)
                    .setLastName(lastName)
                    .setGender(gender)
                    .setUserNumber(mobilePhone);
        });

        step("Отравляем заполненную форму", () -> registrationPage.submitPracticeForm());

        step("Проверка результаты заполнения обязательных полей", () -> {
            registrationPage.verifyResultsAppears()
                    .checkResult("Student Name", firstName + " " + lastName)
                    .checkResult("Gender", gender)
                    .checkResult("Mobile", mobilePhone);
        });
    }

    @Test
    void negativeMinFormTest() {

        step("Открываем сайт с формой", () -> {
            registrationPage.openPage()
                    .removeBanner();
        });

        step("Заполняем форму обязательными полями", () -> {
            registrationPage.setFirstName(firstName)
                    .setLastName(lastName)
                    .setGender(gender)
                    .setUserNumber("");
        });

        step("Отравляем заполненную форму", () -> registrationPage.submitPracticeForm());

        step("Проверка что появилась ошибка", () -> registrationPage.verifyNegativeResultsAppears());
    }

}
