package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class PersonalAccount {
    private final SelenideElement header = $("[data-test-id='dashboard']");
    private final ElementsCollection card = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public PersonalAccount() {
        header.shouldBe(visible);
    }

    // баланс из строки
    public int getCardBalance(DataHelper.CardsInfo cardsInfo) {
        var text = card.findBy(text(cardsInfo.getCardNumber().substring(15))).getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    // выбор карты и переход на страницу операций с картами
    public TransferPage selectCardForTransfer(DataHelper.CardsInfo cardsInfo) {
        card.findBy(attribute("data-test-id", cardsInfo.getCardId())).$("button").click();
        return new TransferPage();
    }
}
