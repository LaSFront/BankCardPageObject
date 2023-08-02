package ru.netology.web.data;

import lombok.Value;

import java.util.Random;

public class DataHelper {

    private DataHelper() {
    }

    //переменные авторизация
    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    //авторизация
    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    //переменные код
    @Value
    public static class VerificationCode {
        private String code;
    }

    //метод get /код подтверждения/
    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }
    //переменные карты
    @Value
    public static class CardsInfo {
        private String cardNumber;
        private String cardId;
    }
    //карта 1
    public static CardsInfo getCardInfo1() {
        return new CardsInfo("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }
    //карта 2
    public static CardsInfo getCardInfo2() {
        return new CardsInfo("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }
    //валидный баланс
    public static int generateAmountValid(int balance) {
        return new Random().nextInt(Math.abs(balance)) + 1;
    }
    //невадидны1й баланс
    public static int generateAmountInvalid(int balance) {
        return Math.abs(balance) + new Random().nextInt(1000);
    }
}


