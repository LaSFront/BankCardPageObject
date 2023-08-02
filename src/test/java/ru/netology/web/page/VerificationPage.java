package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement codeField = $("[data-test-id='code'] input");
    private final SelenideElement verifyButton = $("[data-test-id='action-verify']").shouldHave(Condition.exactText("Продолжить"));

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    //ввод кода-подтверждения /валидное значение/ и переход в личный кабинет
    public PersonalAccount validVerify(DataHelper.VerificationCode verificationCode) {
        codeField.sendKeys(verificationCode.getCode());
        verifyButton.click();
        return new PersonalAccount();
    }
}
