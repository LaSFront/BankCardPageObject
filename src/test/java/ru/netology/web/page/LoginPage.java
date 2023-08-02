package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement loginInput = $("[data-test-id='login'] input");
    private final SelenideElement passwordInput = $("[data-test-id='password'] input ");
    private final SelenideElement buttonLogin = $("[data-test-id='action-login']");

    //ввод логина и пароля /валидные значения/ и переход на следующую страницу
    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginInput.sendKeys(info.getLogin());
        passwordInput.sendKeys(info.getPassword());
        buttonLogin.shouldHave(Condition.exactText("Продолжить")).click();
        return new VerificationPage();
    }
}
