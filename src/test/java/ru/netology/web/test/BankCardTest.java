package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ru.netology.web.page.LoginPage;
import ru.netology.web.page.PersonalAccount;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.*;

public class BankCardTest {
    PersonalAccount personalAccount;

    @BeforeEach
    void setUp() {
        var loginPage = open("http://localhost:9999/", LoginPage.class);
        var authInfo = getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = getVerificationCode();
        personalAccount = verificationPage.validVerify(verificationCode);
    }

    @Test
    @DisplayName("Should success transfer amount from 1 to 2")
    void ShouldSuccessTransferAmountFrom1To2() {
        //инфо по картам
        var cardInfo1 = getCardInfo1();
        var cardInfo2 = getCardInfo2();
        //баланс
        var balanceCard1 = personalAccount.getCardBalance(cardInfo1);
        var balanceCard2 = personalAccount.getCardBalance(cardInfo2);
        //сумма перевода
        var amount = generateAmountValid(balanceCard1);
        //ожидаем
        var expectedBalanceCard1 = (balanceCard1 - amount);
        var expectedBalanceCard2 = (balanceCard2 + amount);
        //транзакция
        var transferPage = personalAccount.selectCardForTransfer(cardInfo2);
        personalAccount = transferPage.transferOperationValid(String.valueOf(amount), cardInfo1);
        //факт
        var actualBalanceCard1 = personalAccount.getCardBalance(cardInfo1);
        var actualBalanceCard2 = personalAccount.getCardBalance(cardInfo2);
        //проверка
        assertEquals(expectedBalanceCard1, actualBalanceCard1);
        assertEquals(expectedBalanceCard2, actualBalanceCard2);
    }

    @Test
    @DisplayName("Should be error because Amount is invalid")
    void ShouldBeErrorAmountIsInvalid1() {
        var cardInfo1 = getCardInfo1();
        var cardInfo2 = getCardInfo2();
        var balanceCard1 = personalAccount.getCardBalance(cardInfo1);
        var balanceCard2 = personalAccount.getCardBalance(cardInfo2);
        var amount = generateAmountInvalid(balanceCard1);
        var transferPage = personalAccount.selectCardForTransfer(cardInfo2);
        transferPage.transferOperation(String.valueOf(amount), cardInfo1);
        //transferPage.errorMassage("Ошибка");

        var actualBalanceCard1 = personalAccount.getCardBalance(cardInfo1);
        var actualBalanceCard2 = personalAccount.getCardBalance(cardInfo2);

        assertEquals(balanceCard1, actualBalanceCard1);
        assertEquals(balanceCard2, actualBalanceCard2);
    }
}

