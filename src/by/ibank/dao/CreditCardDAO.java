package by.ibank.dao;

import by.ibank.entity.Account;
import by.ibank.entity.CreditCard;
import by.ibank.entity.User;

import java.util.List;

public interface CreditCardDAO {
    List<CreditCard> findAllCards(String user);

    void transferMoney(int fromCreditCard, int money, int toCreditCard);

    void addCard(Account account, CreditCard creditCard);

    void deleteCard(String cardNumber);
}
