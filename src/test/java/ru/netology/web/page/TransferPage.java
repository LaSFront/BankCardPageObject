package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;
import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement amount = $("[data-test-id='amount'] input");
    private final SelenideElement fromField = $("[data-test-id='from'] input");
    private final SelenideElement buttonTransfer = $("[data-test-id='action-transfer']");
    private final SelenideElement errorMsg = $("[data-test-id='error-notification']");

    private final SelenideElement headerTransferPage = $(byText("Пополнение карты"));

    public TransferPage() {
        headerTransferPage.shouldBe(visible);
    }

    //перевод
    public void transferOperation(String amountForTransfer, DataHelper.CardsInfo cardsInfo) {
        amount.setValue(amountForTransfer);
        fromField.setValue(cardsInfo.getCardNumber());
        buttonTransfer.click();
    }

    //успешный перевод и возврат в лк
    public PersonalAccount transferOperationValid(String amountForTransfer, DataHelper.CardsInfo cardsInfo) {
        transferOperation(amountForTransfer, cardsInfo);
        return new PersonalAccount();
    }

    //уведомление об ошибке
    public void errorMassage(String expectedText) {
        errorMsg.shouldHave(exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }
}
